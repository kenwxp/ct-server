package com.cloudtimes.partner.weixin.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CtWeixinApiServiceImpl implements ICtWeixinApiService {
    private static String accessToken;
    private static Date expireTime;
    @Autowired
    private PartnerConfig config;

    /**
     * 获取微信access token
     *
     * @return
     */
    @Override
    public String getAccessToken() {
        if (expireTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(expireTime);
            // token 未过期 直接返回
            if (new Date().before(c.getTime())) {
                return accessToken;
            }
        }
        return fetchAccessToken();
    }

    private synchronized String fetchAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + config.getWxAppId() + "&secret=" + config.getWxAppSecret();
        String responseStr = HttpUtils.sendGet(url);
        Map<String, Object> map = JSON.parseObject(responseStr, Map.class);
        String newToken = (String) map.get("access_token");
        int expireIn = (int) map.get("expires_in");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, expireIn);
        accessToken = newToken;
        expireTime = c.getTime();
        return newToken;
    }

    /**
     * 获取用户openid
     *
     * @param jsCode 微信登录组件生成
     * @return map
     * errcode     int64  // 错误码
     * errmsg      string // 错误信息
     * openid      string // 用户唯一标识
     * session_key string // 会话密钥
     * unionid     string // 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
     */
    @Override
    public Map<String, String> getUserSession(String jsCode) {
        String getUserSessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + config.getWxAppId() +
                "&secret=" + config.getWxAppSecret() + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String responseStr = HttpUtils.sendGet(getUserSessionUrl);
        Map<String, String> map = JSON.parseObject(responseStr, Map.class);
        return map;
    }

    /**
     * 获取用户手机号
     *
     * @param jsCode 微信获取手机号组件生成
     * @return map
     * errcode     int64  // 错误码
     * errmsg      string // 错误信息
     * phone_info  map
     * * phoneNumber     string  // 用户绑定的手机号（国外手机号会有区号）
     * * purePhoneNumber string  // 没有区号的手机号
     * * countryCode     string  // 区号
     */
    @Override
    public Map<String, String> getUserPhoneInfo(String jsCode) {
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
        JSONObject reqObj = new JSONObject();
        reqObj.put("code", jsCode);
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json");
        try {
            String responseStr = HttpUtils.sendJsonPost(url, reqObj.toString(), header);
            Map<String, Object> respObj = JSON.parseObject(responseStr, Map.class);
            if ((int) respObj.get("errcode") == 40001) {
                //token超时
                fetchAccessToken();
                return getUserPhoneInfo(jsCode);
            }
            Map<String, Object> respMap = JSON.parseObject(responseStr, Map.class);
            return (Map<String, String>) respMap.get("phone_info");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
