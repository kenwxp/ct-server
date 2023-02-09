package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.mapper.CtOrderDetailMapper;
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
public class OrderDetailCache {
    private static final String CACHE_NAME = "order_detail:";
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtOrderDetailMapper orderDetailMapper;

    @PostConstruct
    public void init() {
        //初始化加载进行中的任务的订单
        List<CtOrderDetail> ctOrderDetails = orderDetailMapper.selectCtOrderDetailListByTaskOrOrder("", "0");
        for (CtOrderDetail orderDetail : ctOrderDetails) {
            setCacheOrderDetail(orderDetail.getOrderId(), orderDetail.getId(), orderDetail);
        }
    }

    public void setCacheOrderDetail(String orderId, String orderDetailId, CtOrderDetail orderDetail) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(getCacheKey(orderId), orderDetailId, orderDetail);
        } finally {
            wLock.unlock();
        }
    }

    public void setCacheOrderDetails(String orderId, Map<String, CtOrderDetail> orderDetails) {
        wLock.lock();
        try {
            redisCache.setCacheMap(getCacheKey(orderId), orderDetails);
        } finally {
            wLock.unlock();
        }
    }

    public CtOrderDetail getCacheOrderDetail(String orderId, String orderDetailId) {
        rLock.lock();
        try {
            CtOrderDetail cacheOrder = redisCache.getCacheMapValue(getCacheKey(orderId), orderDetailId);
            if (cacheOrder != null) {
                return cacheOrder;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        CtOrderDetail order = orderDetailMapper.selectCtOrderDetailById(orderDetailId);
        if (order != null) {
            setCacheOrderDetail(order.getOrderId(), order.getId(), order);
            return order;
        }
        return null;
    }

    public Map<String, CtOrderDetail> getCacheOrderDetails(String orderId) {
        rLock.lock();
        try {
            Map<String, CtOrderDetail> cacheMap = redisCache.getCacheMap(orderId);
            if (cacheMap != null) {
                return cacheMap;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        List<CtOrderDetail> ctOrderDetails = orderDetailMapper.selectCtOrderDetailListByTaskOrOrder(orderId, "0");
        if (ctOrderDetails != null && ctOrderDetails.size() > 0) {
            Map<String, CtOrderDetail> retMap = new HashMap<>();
            for (CtOrderDetail orderDetail :
                    ctOrderDetails) {
                retMap.put(orderDetail.getId(), orderDetail);
            }
            setCacheOrderDetails(orderId, retMap);
            return retMap;
        }
        return null;
    }

    private String getCacheKey(String key) {
        return CACHE_NAME + key;
    }
}
