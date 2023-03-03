package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.system.domain.SysCustomerService;
import com.cloudtimes.system.mapper.SysCustomerServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class CtCustomerServiceCache {
    private static final String CACHE_NAME = "CUSTOMER_SERVICE:";//
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysCustomerServiceMapper customerServiceMapper;

    @PostConstruct
    public void init() {
        //初始化加载进行中的任务
        wLock.lock();
        try {
            SysCustomerService query = new SysCustomerService();
            query.setDelFlag("0");
            List<SysCustomerService> customerServiceList = customerServiceMapper.selectSysCustomerServiceList(query);
            for (SysCustomerService customerService : customerServiceList) {
                Map<String, Object> map = new HashMap<>();
                map.put("serviceId", customerService.getServiceId());
                map.put("serviceName", customerService.getServiceName());
                map.put("superiorId", customerService.getServiceId());
                map.put("superiorName", customerService.getSuperiorName());
                map.put("level", customerService.getLevel());
                map.put("maxAcceptTask", customerService.getMaxAcceptTask());
                map.put("maxAcceptOrder", customerService.getMaxAcceptOrder());
                map.put("acceptState", "1");
                putMap(String.valueOf(customerService.getServiceId()), map);
            }
        } finally {
            wLock.unlock();
        }

    }

    public void refreshByServiceId(String serviceId) {
        rLock.lock();
        try {
            SysCustomerService customerService = customerServiceMapper.selectSysCustomerServiceById(serviceId);
            Map<String, Object> map = new HashMap<>();
            map.put("serviceId", customerService.getServiceId());
            map.put("serviceName", customerService.getServiceName());
            map.put("superiorId", customerService.getServiceId());
            map.put("superiorName", customerService.getSuperiorName());
            map.put("level", customerService.getLevel());
            map.put("maxAcceptTask", customerService.getMaxAcceptTask());
            map.put("maxAcceptOrder", customerService.getMaxAcceptOrder());
            map.put("acceptState", "1");
            putMap(String.valueOf(customerService.getServiceId()), map);
        } finally {
            rLock.unlock();
        }
    }

    public String getAcceptState(String serviceId) {
        return getMapValue(serviceId, "acceptState");
    }

    public void setAcceptState(String serviceId, String acceptState) {
        putMapValue(serviceId, "acceptState", acceptState);
    }

    public Long getMaxAcceptTask(String serviceId) {
        return getMapValue(serviceId, "maxAcceptTask");
    }

    public void setMaxAcceptTask(String serviceId, String acceptState) {
        putMapValue(serviceId, "maxAcceptTask", acceptState);
    }

    public String getMaxAcceptOrder(String serviceId) {
        return getMapValue(serviceId, "maxAcceptOrder");
    }

    public void setMaxAcceptOrder(String serviceId, String acceptState) {
        putMapValue(serviceId, "maxAcceptOrder", acceptState);
    }


    public <T> T getMapValue(String serviceId, String hKey) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(getCacheName(serviceId), hKey);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, Object> getMap(String serviceId) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(getCacheName(serviceId));
        } finally {
            rLock.unlock();
        }
    }

    public <T> void putMap(String serviceId, Map<String, Object> map) {
        wLock.lock();
        try {
            redisCache.setCacheMap(getCacheName(serviceId), map);
        } finally {
            wLock.unlock();
        }
    }

    public <T> void putMapValue(String serviceId, String hKey, T hValue) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(getCacheName(serviceId), hKey, hValue);
        } finally {
            wLock.unlock();
        }
    }

    public boolean deleteMapValue(String serviceId, String hKey) {
        wLock.lock();
        try {
            return redisCache.deleteCacheMapValue(getCacheName(serviceId), hKey);
        } finally {
            wLock.unlock();
        }
    }

    public boolean deleteMap(String serviceId) {
        wLock.lock();
        try {
            return redisCache.deleteObject(getCacheName(serviceId));
        } finally {
            wLock.unlock();
        }
    }

    private String getCacheName(String serviceId) {
        return new StringBuffer().append(CACHE_NAME).append(serviceId).toString();
    }
}
