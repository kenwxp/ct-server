package com.cloudtimes.cache;

import cn.hutool.core.date.DateUtil;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.partner.hik.domain.VideoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info("=========》初始化加载直播视频网址《=========");
        CtDevice query = new CtDevice();
        query.setDeviceType(DeviceType.CAMERA.getCode());
        query.setDelFlag("0");
        List<CtDevice> deviceList = deviceMapper.selectCtDeviceList(query);
        for (CtDevice device : deviceList) {
            if (!StringUtils.isEmpty(device.getStoreId())) {
                CacheVideoData cacheVideoData = new CacheVideoData();
                cacheVideoData.setStoreId(device.getStoreId());
                cacheVideoData.setDeviceId(device.getId());
                cacheVideoData.setDeviceSerial(device.getDeviceSerial());
                VideoData liveAddress = hikApiService.getLiveAddress(device.getDeviceSerial(), "1", "2", String.valueOf(videoTimeoutSec));
                if (liveAddress != null) {
                    cacheVideoData.setUrl(liveAddress.getUrl());
                    cacheVideoData.setExpireTime(DateUtil.parseDateTime(liveAddress.getExpireTime()));
                    cacheVideoData.setToken(liveAddress.getToken());
                }
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
            // 先释放读锁
            rLock.unlock();
            return setCacheVideo(deviceId);
        } finally {
            rLock.unlock();
        }
    }

    public CacheVideoData getCacheVideo(String deviceId) {
        rLock.lock();
        try {
            String cacheKey = getCacheKey(STORE_VIDEO_REL_CACHE, deviceId);
            if (!StringUtils.isEmpty(cacheKey)) {
                CacheVideoData videoData = redisCache.getCacheMapValue(cacheKey, deviceId);
                if (videoData != null) {
                    //前比后，负数，前面小于后面，0相等，正数 前面大于后面
                    // 未过期直接返回
                    if (DateUtil.compare(videoData.getExpireTime(), DateUtils.getNowDate()) > 0) {
                        return videoData;
                    }
                }
            }
            // 先释放读锁
            rLock.unlock();
            return setCacheVideo(deviceId);
        } finally {
            rLock.unlock();
        }
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
        VideoData liveAddress = hikApiService.getLiveAddress(dbDevice.getDeviceSerial(), "1", "2", String.valueOf(videoTimeoutSec));
        CacheVideoData newDevice = new CacheVideoData();
        if (liveAddress != null) {
            newDevice.setStoreId(dbDevice.getStoreId());
            newDevice.setDeviceId(dbDevice.getId());
            newDevice.setDeviceSerial(dbDevice.getDeviceSerial());
            newDevice.setUrl(liveAddress.getUrl());
            newDevice.setExpireTime(DateUtil.parseDateTime(liveAddress.getExpireTime()));
            newDevice.setToken(liveAddress.getToken());
        }
        setCacheVideo(newDevice);
        return newDevice;
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