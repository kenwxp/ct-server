package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class CtDoorStateCache {
    private static final String CACHE_NAME = "door_state";//门禁状态
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;

    public String get(int deviceSerial) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(CACHE_NAME, String.valueOf(deviceSerial));
        } finally {
            rLock.unlock();
        }
    }

    public void put(int deviceSerial, Date refreshTime) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(CACHE_NAME, String.valueOf(deviceSerial), refreshTime);
        } finally {
            wLock.unlock();
        }
    }

    public boolean delete(int deviceSerial) {
        wLock.lock();
        try {
            return redisCache.deleteCacheMapValue(CACHE_NAME, String.valueOf(deviceSerial));
        } finally {
            wLock.unlock();
        }
    }
}
