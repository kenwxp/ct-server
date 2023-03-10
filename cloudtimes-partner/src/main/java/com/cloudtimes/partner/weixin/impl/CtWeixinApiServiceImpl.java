package com.cloudtimes.partner.weixin.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序接口调用service服务
 */
@Service
@Slf4j
public class CtWeixinApiServiceImpl implements ICtWeixinApiService {
    @Autowired
    private PartnerConfig config;
    @Autowired
    private RedisCache redisCache;
    private final String WEIXIN_ACCESS_TOKEN = "WEIXIN_ACCESS_TOKEN";

    /**
     * 获取微信access token
     *
     * @return
     */
    @Override
    public String getAccessToken() {
        Map<String, Object> cacheMap = redisCache.getCacheMap(WEIXIN_ACCESS_TOKEN);
        log.info("getAccessToken:" + cacheMap.toString());
        if (cacheMap != null) {
            Date expireTime = (Date) cacheMap.get("expireTime");
            log.info("expireTime:" + expireTime.toString());
            log.info("nowDate:" + DateUtils.getNowDate());
            log.info("compare:" + DateUtil.compare(DateUtils.getNowDate(), expireTime));
            // token 未过期 直接返回
            if (DateUtil.compare(DateUtils.getNowDate(), expireTime) < 0) {
                return (String) cacheMap.get("accessToken");
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
        Map<String, Object> cacheMap = new HashMap<>();
        cacheMap.put("accessToken", newToken);
        cacheMap.put("expireTime", DateUtil.offset(DateUtils.getNowDate(), DateField.SECOND, expireIn));
        redisCache.setCacheMap(WEIXIN_ACCESS_TOKEN, cacheMap);
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
    public Map<String, Object> getUserSession(String jsCode) {
        String getUserSessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + config.getWxAppId() +
                "&secret=" + config.getWxAppSecret() + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String responseStr = HttpUtils.sendGet(getUserSessionUrl);
        Map<String, Object> map = JSON.parseObject(responseStr, Map.class);
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
    public Map<String, Object> getUserPhoneInfo(String jsCode) {
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
            if (respObj.get("errcode") != null && (int) respObj.get("errcode") == 40001) {
                //token超时
                fetchAccessToken();
                return getUserPhoneInfo(jsCode);
            }
            return respObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
