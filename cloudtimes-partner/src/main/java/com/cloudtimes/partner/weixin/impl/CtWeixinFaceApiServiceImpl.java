package com.cloudtimes.partner.weixin.impl;

import com.cloudtimes.common.constant.Constants;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.XmlUtils;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.common.utils.sign.Base64;
import com.cloudtimes.common.utils.sign.Md5Utils;
import com.cloudtimes.common.utils.sign.RSAUtil;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.weixin.ICtWeixinFaceApiService;
import com.cloudtimes.partner.weixin.domain.WxUnionIdData;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoReq;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * 微信小程序接口调用service服务
 */
@Service
public class CtWeixinFaceApiServiceImpl implements ICtWeixinFaceApiService {
    static Logger log = LoggerFactory.getLogger(CtWeixinFaceApiServiceImpl.class);
    @Autowired
    private PartnerConfig config;


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
        reqParams.setNonceStr(NumberUtils.getRandomENString(32));
        String sign = getFaceSign(reqParams);
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


    public String getFaceSign(Object params) {
        Field[] declaredFields = params.getClass().getDeclaredFields();
        // 通过rowdata 进行字段排序
        TreeMap<String, Object> map = new TreeMap<>();
        for (Field f :
                declaredFields) {
            try {
                f.setAccessible(true);
                if (f.get(params) != null) {
                    map.put(StringUtils.toUnderScoreCase(f.getName()), f.get(params));
                }
            } catch (IllegalAccessException e) {
                log.error("生成微信签名错误", e);
                return "";
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        map.forEach((key, value) -> stringBuffer.append(key).append("=").append(value).append("&"));
        String raw = stringBuffer.deleteCharAt(stringBuffer.length() - 1).append("&key=").append(config.getWxApiV2Secret()).toString();
        log.info("微信签名字符串：" + raw);
        String sign = Md5Utils.hash(raw).toUpperCase();
        return sign;
    }

    /**
     * 根据刷脸token 获取unionId
     *
     * @param token
     * @return
     */
    @Override
    public String getWxpayfaceUserUnionId(String token) {
        String domain = "https://api.mch.weixin.qq.com";
        String url = "/v3/facemch/users/" + token + "?info_type=ASK_UNIONID&appid=" + config.getWxAppId();
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json; charset=utf-8");
        header.put("Accept", "application/json; charset=utf-8");
        String authToken = getAuthToken("GET", url, "");
        log.info("authToken:{}", authToken);
        header.put("Authorization", "WECHATPAY2-SHA256-RSA2048" + " " + authToken);
        String resultStr = HttpUtils.sendGet(domain + url, "", Constants.UTF8, header);
        if (StringUtils.isNotBlank(resultStr)) {
            WxUnionIdData wxUnionIdData = JacksonUtils.parseObject(resultStr, WxUnionIdData.class);
            if (wxUnionIdData != null) {
                return wxUnionIdData.getUnionId();
            }
        }
        return "";
    }

    private String getAuthToken(String method, String url, String body) {
        String nonceStr = NumberUtils.getRandomENString(32);
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = getSign(message);
        log.info("signature:{}", signature);
        return "mchid=\"" + config.getWxMchId() + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + config.getWxCertSerial() + "\","
                + "signature=\"" + signature + "\"";
    }

    private String getSign(String message) {
        PrivateKey certPrivate = RSAUtil.getPrivateKeyFromPath(config.getWxCertPrivatePemPath());
        byte[] sha256withRSAS = RSAUtil.sign(certPrivate, message, "SHA256withRSA");
        return Base64.encode(sha256withRSAS);
    }

    private String buildMessage(String method, String url, long timestamp, String nonceStr, String body) {
        return method + "\n"
                + url + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
    }

}
