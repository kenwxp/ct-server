package com.cloudtimes.cache;

import lombok.Data;

import java.util.Date;

@Data
public class CacheVideoData {
    private String storeId;
    private String deviceId;
    private String deviceSerial;
    private String deviceType;
    private String url;
    private Date expireTime;
    private String token;
}
