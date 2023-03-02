package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class CtStaffAcceptCache {
    private static final String CACHE_NAME = "STAFF_ACCEPT_TASK_SWITCH";//收银机动态码
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;

    public String get(String userId) {
        rLock.lock();
        try {
            String accept = redisCache.getCacheMapValue(CACHE_NAME, userId);
            if (StringUtils.isEmpty(accept)) {
                rLock.unlock();
                put(userId, "0");//默认开始接单
                return "0";
            }
            return accept;
        } finally {
            rLock.unlock();
        }
    }

    public void put(String userId, String option) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(CACHE_NAME, userId, option);
        } finally {
            wLock.unlock();
        }
    }

    public boolean delete(String userId) {
        wLock.lock();
        try {
            return redisCache.deleteCacheMapValue(CACHE_NAME, userId);
        } finally {
            wLock.unlock();
        }
    }
}
