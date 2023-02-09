package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class TaskShoppingCache {
    private static final String CACHE_NAME = "shopping_order:";
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtShoppingMapper shoppingMapper;

    @PostConstruct
    public void init() {
        //初始化加载进行中的任务的订单
        List<CtShopping> ctShoppings = shoppingMapper.selectCtShoppingListByTask("", "0");
        for (CtShopping shopping : ctShoppings) {
            setCacheShopping(shopping.getTaskId(), shopping.getId(), shopping);
        }
    }

    public void setCacheShopping(String taskId, String shoppingId, CtShopping order) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(getCacheKey(taskId), shoppingId, order);
        } finally {
            wLock.unlock();
        }
    }

    public void setCacheShoppings(String taskId, Map<String, CtShopping> orders) {
        wLock.lock();
        try {
            redisCache.setCacheMap(getCacheKey(taskId), orders);
        } finally {
            wLock.unlock();
        }
    }

    public CtShopping getCacheShopping(String taskId, String shoppingId) {
        rLock.lock();
        try {
            CtShopping cacheOrder = redisCache.getCacheMapValue(getCacheKey(taskId), shoppingId);
            if (cacheOrder != null) {
                return cacheOrder;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        CtShopping order = shoppingMapper.selectCtShoppingById(shoppingId);
        if (order != null) {
            setCacheShopping(order.getTaskId(), order.getId(), order);
            return order;
        }
        return null;
    }

    public Map<String, CtShopping> getCacheShoppings(String taskId) {
        rLock.lock();
        try {
            Map<String, CtShopping> cacheMap = redisCache.getCacheMap(taskId);
            if (cacheMap != null) {
                return cacheMap;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        List<CtShopping> ctShoppings = shoppingMapper.selectCtShoppingListByTask(taskId, "0");
        if (ctShoppings != null && ctShoppings.size() > 0) {
            Map<String, CtShopping> retMap = new HashMap<>();
            for (CtShopping shopping :
                    ctShoppings) {
                retMap.put(shopping.getId(), shopping);
            }
            setCacheShoppings(taskId, retMap);
            return retMap;
        }
        return null;
    }

    private String getCacheKey(String key) {
        return CACHE_NAME + key;
    }
}
