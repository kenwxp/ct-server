package com.cloudtimes.partner.hik.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.domain.HikConstant;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CtHikApiServiceImpl implements ICtHikApiService {
    private static String accessToken;
    private static Date expireTime;
    static Logger log = LoggerFactory.getLogger(CtHikApiServiceImpl.class);

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
        // 重新http获取新的token
        Map<String, String> params = new HashMap<>();
        params.put("appKey", PartnerConfig.getHikConfig().get("key"));
        params.put("appSecret", PartnerConfig.getHikConfig().get("secret"));
        String result = HttpUtils.sendFormPost("https://" + HikConstant.hikHost + HikConstant.getAccessTokenUri, params, getHikHeader());
        JSONObject retObj = JSON.parseObject(result);
        String code = retObj.getString("code");
        if (HikConstant.CODE200.equals(code)) {
            JSONObject dataObj = retObj.getJSONObject("data");
            String newToken = dataObj.getString("accessToken");
            Long expireTs = dataObj.getLong("expireTime");
            accessToken = newToken;
            expireTime = new Date(expireTs);
            return newToken;
        }
        return "";
    }
    @Override
    public String addDevice(String deviceSerial, String validateCode) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("validateCode", validateCode);
        return sendHikHttp(HikConstant.addDeviceUri, params);
    }
    @Override
    public String deleteDevice(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        return sendHikHttp(HikConstant.deleteDeviceUri, params);
    }
    @Override
    public String getDeviceInfo(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        return sendHikHttp(HikConstant.getDeviceInfoUri, params);
    }

    private String sendHikHttp(String path, Map<String, String> params) {
        String at = getAccessToken();
        if ("".equals(at)) {
            return "";
        }
        params.put("accessToken", at);
        String result = HttpUtils.sendFormPost("https://" + HikConstant.hikHost + path, params, getHikHeader());
        JSONObject retObj = JSON.parseObject(result);
        if (HikConstant.CODE10002.equals(retObj.getString("code"))) {
            //token 过时，则刷新token后，重新请求
            String newToken = fetchAccessToken();
            params.put("accessToken", newToken);
            result = HttpUtils.sendFormPost("https://" + HikConstant.hikHost + path, params, getHikHeader());
        }
        System.out.println("sendHikHttp response " + result);
        return result;
    }

    private Map<String, String> getHikHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Host", "open.ys7.com");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        return header;
    }
}
