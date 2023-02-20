package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class CtDeviceCache {
    private static final String CACHE_NAME = "cash_device_dynamic_code";//收银机动态码
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;

    public String get(String deviceId) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(CACHE_NAME, deviceId);
        } finally {
            rLock.unlock();
        }
    }

    public void put(String deviceId, String code) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(CACHE_NAME, deviceId, code);
        } finally {
            wLock.unlock();
        }
    }

    public boolean delete(String deviceId) {
        wLock.lock();
        try {
            return redisCache.deleteCacheMapValue(CACHE_NAME, deviceId);
        } finally {
            wLock.unlock();
        }
    }
}
