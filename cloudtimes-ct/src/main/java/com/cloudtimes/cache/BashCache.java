package com.cloudtimes.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BashCache {
    private String CACHE_NAME;

    public BashCache() {
    }

    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();

    private String getCacheKey(String key) {
        return CACHE_NAME + key;
    }
}
