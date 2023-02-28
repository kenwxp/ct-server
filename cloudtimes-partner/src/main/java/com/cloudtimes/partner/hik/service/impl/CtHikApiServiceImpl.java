package com.cloudtimes.partner.hik.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.domain.CommonResp;
import com.cloudtimes.partner.hik.domain.DeviceInfoData;
import com.cloudtimes.partner.hik.domain.HikConstant;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PartnerConfig config;

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
        params.put("appKey", config.getHikAppKey());
        params.put("appSecret", config.getHikAppSecret());
        String result = HttpUtils.sendFormPost("https://" + HikConstant.hikHost + HikConstant.getAccessTokenUri, params, getHikHeader());
        JSONObject retObj = JSON.parseObject(result);
        if (retObj != null) {
            String code = retObj.getString("code");
            if (HikConstant.CODE200.equals(code)) {
                JSONObject dataObj = retObj.getJSONObject("data");
                String newToken = dataObj.getString("accessToken");
                Long expireTs = dataObj.getLong("expireTime");
                accessToken = newToken;
                expireTime = new Date(expireTs);
                return newToken;
            }
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
    public DeviceInfoData getDeviceInfo(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        String result = sendHikHttp(HikConstant.getDeviceInfoUri, params);
        CommonResp commonResp = JacksonUtils.parseObject(result, CommonResp.class);
        if (commonResp != null && commonResp.getCode() == HikConstant.CODE200 && commonResp.getData() != null) {
            return JacksonUtils.convertObject(commonResp.getData(), DeviceInfoData.class);
        }
        return null;
    }

    @Override
    public Map<String, String> getLiveAddress(String deviceSerial, String protocol, String quality) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", "1");
        params.put("expireTime", "3600");
        params.put("protocol", protocol);
        params.put("quality", quality);
        params.put("supportH265", "1");
        params.put("gbchannel", "");
        params.put("type", "1");
        return getAddressUrl(params);
    }

    @Override
    public Map<String, String> getPlaybackAddress(String deviceSerial, String quality, String startTime, String stopTime) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", "1");
        params.put("expireTime", "3600");
        params.put("protocol", "1");
        params.put("quality", quality);
        params.put("supportH265", "1");
        params.put("gbchannel", "");
        params.put("type", "2");
        params.put("startTime", startTime);
        params.put("stopTime", stopTime);
        return getAddressUrl(params);
    }

    /**
     * 设备加密
     *
     * @param deviceSerial 设备序列号
     * @param validateCode 设备验证码
     * @param enable       是否加密 0-不加密 1-加密
     * @return
     */
    @Override
    public String setDeviceEncrypt(String deviceSerial, String validateCode, boolean enable) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("validateCode", validateCode);
        if (enable) {
            return sendHikHttp(HikConstant.setDeviceEncryptOnUri, params);
        } else {
            return sendHikHttp(HikConstant.setDeviceEncryptOffUri, params);
        }
    }

    @Override
    public String getDeviceCapture(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", "1");
        return sendHikHttp(HikConstant.getDeviceCaptureUri, params);
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

    private Map<String, String> getAddressUrl(Map<String, String> params) {
        String retStr = sendHikHttp(HikConstant.getLiveAddressUri, params);
        Map<String, String> retMap = new HashMap<>();
        retMap.put("accessToken", getAccessToken());
        retMap.put("url", "");
        JSONObject retObj = JSON.parseObject(retStr);
        if (HikConstant.CODE200.equals(retObj.getString("code"))) {
            JSONObject dataObj = retObj.getJSONObject("data");
            if (dataObj != null) {
                retMap.put("url", dataObj.getString("url"));
            }
        }
        return retMap;
    }

    private Map<String, String> getHikHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Host", "open.ys7.com");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        return header;
    }
}
