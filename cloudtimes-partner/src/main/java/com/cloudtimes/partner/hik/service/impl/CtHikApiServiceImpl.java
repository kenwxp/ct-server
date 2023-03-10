package com.cloudtimes.partner.hik.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cloudtimes.common.constant.Constants;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.domain.*;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CtHikApiServiceImpl implements ICtHikApiService {
    @Autowired
    private PartnerConfig config;
    @Autowired
    private RedisCache redisCache;
    private final String HIK_ACCESS_TOKEN = "HIK_ACCESS_TOKEN";

    @Override
    public String getAccessToken() {
        Map<String, Object> cacheMap = redisCache.getCacheMap(HIK_ACCESS_TOKEN);
        if (cacheMap != null) {
            Date expireTime = (Date) cacheMap.get("expireTime");
            // token 未过期 直接返回
            if (DateUtil.compare(DateUtils.getNowDate(), expireTime) < 0) {
                return (String) cacheMap.get("accessToken");
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
        HikCommonResp commonResp = JacksonUtils.parseObject(result, HikCommonResp.class);
        if (commonResp != null && StringUtils.equals(commonResp.getCode(), HikCodeEnum.CODE200.getCode()) && commonResp.getData() != null) {
            AccessTokenData accessTokenData = JacksonUtils.convertObject(commonResp.getData(), AccessTokenData.class);
            String newToken = accessTokenData.getAccessToken();
            long expireTs = accessTokenData.getExpireTime();
            Map<String, Object> cacheMap = new HashMap<>();
            cacheMap.put("accessToken", newToken);
            cacheMap.put("expireTime", new Date(expireTs));
            redisCache.setCacheMap(HIK_ACCESS_TOKEN, cacheMap);
            return newToken;
        }
        return "";
    }

    @Override
    public HikCommonResp addDevice(String deviceSerial, String validateCode) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("validateCode", validateCode);
        return sendHikHttp(HikConstant.addDeviceUri, params);
    }

    @Override
    public HikCommonResp deleteDevice(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        return sendHikHttp(HikConstant.deleteDeviceUri, params);
    }

    @Override
    public DeviceInfoData getDeviceInfo(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        HikCommonResp commonResp = sendHikHttp(HikConstant.getDeviceInfoUri, params);
        if (commonResp != null && StringUtils.equals(commonResp.getCode(), HikCodeEnum.CODE200.getCode()) && commonResp.getData() != null) {
            return JacksonUtils.convertObject(commonResp.getData(), DeviceInfoData.class);
        }
        return null;
    }

    @Override
    public VideoData getLiveAddress(String deviceSerial, String protocol, String quality, String expireSec) {
        return getLiveAddress(deviceSerial, 1, protocol, quality, expireSec);
    }

    @Override
    public VideoData getLiveAddress(String deviceSerial, int deviceChannel, String protocol, String quality, String expireSec) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", String.valueOf(deviceChannel));
        params.put("expireTime", expireSec);
        params.put("protocol", protocol);
        params.put("quality", quality);
        params.put("supportH265", "1");
        params.put("gbchannel", "");
        params.put("type", "1");
        return getAddressUrl(params);
    }

    @Override
    public VideoData getPlaybackAddress(String deviceSerial, String quality, String startTime, String stopTime) {
        return getPlaybackAddress(deviceSerial, 1, quality, startTime, stopTime);
    }

    @Override
    public VideoData getPlaybackAddress(String deviceSerial, int deviceChannel, String quality, String startTime, String stopTime) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", String.valueOf(deviceChannel));
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
    public HikCommonResp setDeviceEncrypt(String deviceSerial, String validateCode, boolean enable) {
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
    public HikCommonResp getDeviceCapture(String deviceSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", "1");
        return sendHikHttp(HikConstant.getDeviceCaptureUri, params);
    }

    private HikCommonResp sendHikHttp(String path, Map<String, String> params) {
        params.put("accessToken", getAccessToken());
        String result = HttpUtils.sendFormPost("https://" + HikConstant.hikHost + path, params, getHikHeader());
        HikCommonResp commonResp = JacksonUtils.parseObject(result, HikCommonResp.class);
        if (commonResp != null) {
            if (StringUtils.equals(commonResp.getCode(), HikCodeEnum.CODE10002.getCode())) {
                //token 过时，则刷新token后，重新请求
                String newToken = fetchAccessToken();
                params.put("accessToken", newToken);
                result = HttpUtils.sendFormPost("https://" + HikConstant.hikHost + path, params, getHikHeader());
                commonResp = JacksonUtils.parseObject(result, HikCommonResp.class);
            }
        }

        return commonResp;
    }

    private VideoData getAddressUrl(Map<String, String> params) {
        HikCommonResp commonResp = sendHikHttp(HikConstant.getLiveAddressUri, params);

        if (commonResp != null && StringUtils.equals(commonResp.getCode(), HikCodeEnum.CODE200.getCode())
                && commonResp.getData() != null) {
            VideoData videoData = new VideoData();
            videoData = JacksonUtils.convertObject(commonResp.getData(), VideoData.class);
            videoData.setToken(getAccessToken());
            return videoData;
        }
        return null;
    }

    private Map<String, String> getHikHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Host", "open.ys7.com");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        return header;
    }

    public NvrDeviceInfoData getNvrChannelStatus(String nvrSerial) {
        Map<String, String> hikHeader = getHikHeader();
        hikHeader.put("accessToken", getAccessToken());
        hikHeader.put("deviceSerial", nvrSerial);
        String result = HttpUtils.sendGet("https://" + HikConstant.hikHost + HikConstant.getNvrChannelStatus, null, Constants.UTF8, hikHeader);
        Map resultMap = JacksonUtils.parseObject(result, Map.class);
        if (resultMap != null) {
            Object resultObj = resultMap.get("result");
            if (resultObj != null) {
                HikCommonResp hikCommonResp = JacksonUtils.convertObject(resultObj, HikCommonResp.class);
                if (StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE10002.getCode())) {
                    //token 过时，则刷新token后，重新请求
                    fetchAccessToken();
                    return getNvrChannelStatus(nvrSerial);
                }
                if (StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE200.getCode())) {
                    return JacksonUtils.convertObject(hikCommonResp.getData(), NvrDeviceInfoData.class);
                }
            }
        }
        return null;
    }
}
