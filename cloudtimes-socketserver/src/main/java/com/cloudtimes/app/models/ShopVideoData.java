package com.cloudtimes.app.models;

import lombok.Data;

@Data
public class ShopVideoData {
    /**
     * 设备编号
     */
    private String deviceId;
    /**
     * 设备序列号
     */
    private String serial;
    /**
     * 播放链接
     */
    private String url;
    /**
     * 播放token
     */
    private String token;

}
