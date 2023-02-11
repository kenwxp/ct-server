package com.cloudtimes.partner.weixin.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.HolidayUtil;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.XmlUtils;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.common.utils.sign.Md5Utils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoReq;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class CtWeixinApiServiceImpl implements ICtWeixinApiService {
    static Logger log = LoggerFactory.getLogger(CtWeixinApiServiceImpl.class);
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

    public WxpayfaceAuthInfoResp getWxpayfaceAuthInfo(String storeId, String storeName, String deviceId, String rawData) {
        String url = "https://payapp.weixin.qq.com/face/get_wxpayface_authinfo";
        WxpayfaceAuthInfoReq reqParams = new WxpayfaceAuthInfoReq();
        reqParams.setStoreId(storeId);
        reqParams.setStoreName(storeName);
        reqParams.setDeviceId(deviceId);
        reqParams.setRawdata(rawData);
        reqParams.setAppid(config.getWxAppId());
        reqParams.setMchId(config.getWxMchId());
        reqParams.setNow((int) (new Date().getTime() / 1000));
        reqParams.setVersion("1");
        reqParams.setSignType("MD5");
        reqParams.setNonceStr(NumberUtils.getRandomString(16));
        String sign = getSign(reqParams);
        reqParams.setSign(sign);
        String xmlParams = XmlUtils.formatXml(reqParams);

        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/xml");
        header.put("Content-Type", "application/xml");
        try {
            String responseStr = HttpUtils.sendJsonPost(url, xmlParams, header);
            WxpayfaceAuthInfoResp wxpayfaceAuthInfoResp = XmlUtils.parseXml(responseStr, WxpayfaceAuthInfoResp.class);
            return wxpayfaceAuthInfoResp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getSign(Object params) {
        Field[] declaredFields = params.getClass().getDeclaredFields();
        TreeMap<String, Object> map = new TreeMap<>();
        for (Field f :
                declaredFields) {
            try {
                f.setAccessible(true);
                map.put(StringUtils.toUnderScoreCase(f.getName()), f.get(params));
            } catch (IllegalAccessException e) {
                log.error("生成微信签名错误", e);
                return "";
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        map.forEach((key, value) -> stringBuffer.append(key).append("=").append(value).append("&"));
        String raw = stringBuffer.deleteCharAt(stringBuffer.length() - 1).append("&key=").append(config.getWxMchKey()).toString();
        String sign = Md5Utils.hash(raw).toUpperCase();
        return sign;
    }

}
