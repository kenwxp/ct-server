package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
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
public class TaskOrderCache {
    private static final String CACHE_NAME = "task_order:";
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtOrderMapper orderMapper;

    @PostConstruct
    public void init() {
        //初始化加载进行中的任务的订单
        List<CtOrder> ctOrders = orderMapper.selectCtOrderListByTask("", "0");
        for (CtOrder order : ctOrders) {
            setCacheOrder(order.getTaskId(), order.getId(), order);
        }
    }

    public void setCacheOrder(String taskId, String orderId, CtOrder order) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(getCacheKey(taskId), orderId, order);
        } finally {
            wLock.unlock();
        }
    }

    public void setCacheOrders(String taskId, Map<String, CtOrder> orders) {
        wLock.lock();
        try {
            redisCache.setCacheMap(getCacheKey(taskId), orders);
        } finally {
            wLock.unlock();
        }
    }

    public CtOrder getCacheOrder(String taskId, String orderId) {
        rLock.lock();
        try {
            CtOrder cacheOrder = redisCache.getCacheMapValue(getCacheKey(taskId), orderId);
            if (cacheOrder != null) {
                return cacheOrder;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        CtOrder order = orderMapper.selectCtOrderById(orderId);
        if (order != null) {
            setCacheOrder(order.getTaskId(), order.getId(), order);
            return order;
        }
        return null;
    }

    public Map<String, CtOrder> getCacheOrders(String taskId) {
        rLock.lock();
        try {
            Map<String, CtOrder> cacheMap = redisCache.getCacheMap(taskId);
            if (cacheMap != null) {
                return cacheMap;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        List<CtOrder> ctOrders = orderMapper.selectCtOrderListByTask(taskId, "0");
        if (ctOrders != null && ctOrders.size() > 0) {
            Map<String, CtOrder> orderMap = new HashMap<>();
            for (CtOrder order :
                    ctOrders) {
                orderMap.put(order.getId(), order);
            }
            setCacheOrders(taskId, orderMap);
            return orderMap;
        }
        return null;
    }

    private String getCacheKey(String key) {
        return CACHE_NAME + key;
    }

}
