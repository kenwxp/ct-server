package com.cloudtimes.cache;

import cn.hutool.core.date.DateUtil;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.DeviceState;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.partner.hik.domain.VideoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Slf4j
public class CtStoreVideoCache {
    private static final String STORE_VIDEO_REL_CACHE = "store_video:";//门店视频
    @Value("${cache_switch.store_video}")
    private boolean enabled;
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private ICtHikApiService hikApiService;
    private final Long videoTimeoutSec = 24 * 60 * 60L;

    @PostConstruct
    public void initVideo() {
        if (!enabled) {
            return;
        }
        log.info("=========》初始化加载直播视频网址《=========");
        CtDevice query = new CtDevice();
        query.setDelFlag("0");
        query.setDeviceType(DeviceType.CAMERA.getCode());
        List<CtDevice> deviceList = deviceMapper.selectCtDeviceList(query);
        query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
        List<CtDevice> nvrCameraList = deviceMapper.selectCtDeviceList(query);
        deviceList.addAll(nvrCameraList);
        for (CtDevice device : deviceList) {
            if (!StringUtils.isEmpty(device.getStoreId()) && !StringUtils.equals(device.getState(), DeviceState.Forbidden.getCode())) {
                CacheVideoData cacheVideoData = new CacheVideoData();
                cacheVideoData.setStoreId(device.getStoreId());
                cacheVideoData.setDeviceId(device.getId());
                cacheVideoData.setDeviceSerial(device.getDeviceSerial());
                cacheVideoData.setDeviceType(device.getDeviceType());
//                 获取url
//                setAddressUrl(device, cacheVideoData);
                setCacheVideo(cacheVideoData);
            }
        }
    }


    public CacheVideoData getCacheVideo(String storeId, String deviceId) {
        rLock.lock();
        try {
            CacheVideoData videoData = redisCache.getCacheMapValue(STORE_VIDEO_REL_CACHE + storeId, deviceId);
            if (videoData != null) {
                //前比后，负数，前面小于后面，0相等，正数 前面大于后面
                // 未过期直接返回
                if (DateUtil.compare(videoData.getExpireTime(), DateUtils.getNowDate()) > 0) {
                    return videoData;
                }
            }
        } finally {
            rLock.unlock();
        }
        return setCacheVideo(deviceId);
    }

    public CacheVideoData getCacheVideo(String deviceId) {
        rLock.lock();
        try {
            String cacheKey = getCacheKey(STORE_VIDEO_REL_CACHE, deviceId);
            if (!StringUtils.isEmpty(cacheKey)) {
                CacheVideoData videoData = redisCache.getCacheMapValue(cacheKey, deviceId);
                if (videoData != null && videoData.getExpireTime() != null) {
                    // 前比后，负数，前面小于后面，0相等，正数 前面大于后面
                    // 未过期直接返回
                    if (DateUtil.compare(videoData.getExpireTime(), DateUtils.getNowDate()) > 0) {
                        return videoData;
                    }
                }
            }
        } finally {
            rLock.unlock();
        }
        return setCacheVideo(deviceId);
    }


    public Map<String, CacheVideoData> getCacheVideosOfStore(String storeId) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(STORE_VIDEO_REL_CACHE + storeId);
        } finally {
            rLock.unlock();
        }
    }

    public void setCacheVideo(CacheVideoData video) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(STORE_VIDEO_REL_CACHE + video.getStoreId(), video.getDeviceId(), video);
        } finally {
            wLock.unlock();
        }
    }

    private CacheVideoData setCacheVideo(String deviceId) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (dbDevice == null) {
            return null;
        }
        CacheVideoData newDevice = new CacheVideoData();
        newDevice.setStoreId(dbDevice.getStoreId());
        newDevice.setDeviceId(dbDevice.getId());
        newDevice.setDeviceSerial(dbDevice.getDeviceSerial());
        newDevice.setDeviceType(dbDevice.getDeviceType());
        setAddressUrl(dbDevice, newDevice);
        setCacheVideo(newDevice);
        return newDevice;
    }

    private void setAddressUrl(CtDevice device, CacheVideoData cacheVideoData) {
        int channelNo = 0;
        if (StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
            // nvr摄像头需要带通道号
            channelNo = device.getDeviceChannel();
        } else if (StringUtils.equals(device.getDeviceType(), DeviceType.CAMERA.getCode())) {
            channelNo = 1;
        }
        VideoData liveAddress = hikApiService.getLiveAddress(device.getDeviceSerial(), channelNo, "1", "2", String.valueOf(videoTimeoutSec));
        if (liveAddress != null) {
            cacheVideoData.setUrl(liveAddress.getUrl());
            cacheVideoData.setExpireTime(DateUtils.parseDateTime(liveAddress.getExpireTime()));
            cacheVideoData.setToken(liveAddress.getToken());
        }
    }

    public boolean deleteCacheVideo(String deviceId) {
        wLock.lock();
        try {
            String cacheKey = getCacheKey(STORE_VIDEO_REL_CACHE, deviceId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.deleteCacheMapValue(cacheKey, deviceId);
            }
        } finally {
            wLock.unlock();
        }
        return true;
    }

    public String getCacheKey(String prefix, String orderId) {
        // 获取全部前缀匹配的key
        Set<String> keys = (Set<String>) redisCache.keys(prefix + "*");
        //遍历查找相关hkey是否存在
        for (String key :
                keys) {
            if (redisCache.hasHashKey(key, orderId)) {
                return key;
            }
        }
        return "";
    }

}
