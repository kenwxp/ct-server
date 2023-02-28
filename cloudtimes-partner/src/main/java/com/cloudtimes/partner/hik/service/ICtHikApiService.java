package com.cloudtimes.partner.hik.service;

import com.cloudtimes.partner.hik.domain.DeviceInfoData;

import java.util.Map;

/**
 *
 */
public interface ICtHikApiService {
    /**
     * 获取萤石云access token
     *
     * @return
     */
    public String getAccessToken();

    /**
     * 添加设备
     *
     * @param deviceSerial
     * @param validateCode
     * @return
     */
    public String addDevice(String deviceSerial, String validateCode);

    /**
     * 删除设备
     *
     * @param deviceSerial
     * @return
     */
    public String deleteDevice(String deviceSerial);

    /**
     * 获取设备信息
     *
     * @param deviceSerial
     * @return
     */
    public DeviceInfoData getDeviceInfo(String deviceSerial);

    /**
     * 获取直播地址
     *
     * @param deviceSerial 设备序列号
     * @param protocol     流播放协议，1-ezopen、2-hls、3-rtmp、4-flv
     * @param quality      视频清晰度，1-高清（主码流）、2-流畅（子码流）
     * @return map
     * url 播放地址
     * token 播放token
     */
    public Map<String, String> getLiveAddress(String deviceSerial, String protocol, String quality);

    /**
     * 获取回放地址
     *
     * @param deviceSerial 设备序列号
     * @param quality      视频清晰度，1-高清（主码流）、2-流畅（子码流）
     * @param startTime    本地录像/云存储录像回放开始时间,示例：2019-12-01 00:00:00
     * @param stopTime     本地录像/云存储录像回放开始时间,示例：2019-12-01 00:00:00
     * @return map
     * url 播放地址
     * token 播放token
     */
    public Map<String, String> getPlaybackAddress(String deviceSerial, String quality, String startTime, String stopTime);

    /**
     * 设备加密
     *
     * @param deviceSerial 设备序列号
     * @param validateCode 设备验证码
     * @param enable       是否加密 0-不加密 1-加密
     * @return
     */
    public String setDeviceEncrypt(String deviceSerial, String validateCode, boolean enable);

    /**
     * 抓取设备截图
     *
     * @param deviceSerial
     * @return url 图片链接
     */
    public String getDeviceCapture(String deviceSerial);
}
