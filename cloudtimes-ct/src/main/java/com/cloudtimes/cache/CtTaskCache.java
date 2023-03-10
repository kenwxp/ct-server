package com.cloudtimes.cache;

import com.alibaba.fastjson2.JSON;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtOrderDetailMapper;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Slf4j
public class CtTaskCache {
    private static final String STAFF_TASK_REL_CACHE = "staff_task:";//客服任务关联
    private static final String STORE_TASK_REL_CACHE = "store_task:";//门店任务关联
    private static final String TASK_SHOPPING_REL_CACHE = "task_shopping:";//任务购物关联
    private static final String TASK_ORDER_REL_CACHE = "task_order:";//任务订单关联
    private static final String ORDER_DETAIL_CACHE = "order_detail:";//订单详情关联
    private static final String OUT_SUPERVISE_TASK_ID = "out_supervise_task_orders"; //无任务ID
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtTaskMapper taskMapper;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtOrderDetailMapper orderDetailMapper;

    @PostConstruct
    public void initTask() {
        log.info("初始化加载进行中的任务....");
        CtTask query = new CtTask();
        query.setState("0");
        List<CtTask> taskList = taskMapper.selectCtTaskList(query);
        log.info("taskList：" + JSON.toJSONString(taskList));
        for (CtTask task : taskList) {
            setCacheTask(task);
        }
        //初始化加载进行中的任务的订单
        log.info("初始化加载进行中的任务的订单....");
        List<CtOrder> ctOrders = orderMapper.selectCtOrderListByTask("", "0");
        for (CtOrder order : ctOrders) {
            setCacheOrder(order);
        }
        //初始化加载进行中的任务的购物
        log.info("初始化加载进行中的任务的购物....");
        List<CtShopping> ctShoppingList = shoppingMapper.selectCtShoppingListByTask("", "0");
        for (CtShopping shopping : ctShoppingList) {
            setCacheShopping(shopping);
        }
        //初始化加载进行中的任务的物品清单
        log.info("初始化加载进行中的物品清单....");
        List<CtOrderDetail> ctOrderDetails = orderDetailMapper.selectCtOrderDetailListByTaskOrOrder("", "0");
        for (CtOrderDetail orderDetail : ctOrderDetails) {
            setCacheOrderDetail(orderDetail);
        }
    }

    public CtOrder getCacheTaskOfStaff(String staffCode, String taskId) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(STAFF_TASK_REL_CACHE + staffCode, taskId);
        } finally {
            rLock.unlock();
        }
    }

    public CtOrder getCacheTaskOfStore(String storeId, String taskId) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(STORE_TASK_REL_CACHE + storeId, taskId);
        } finally {
            rLock.unlock();
        }
    }

    public CtTask getCacheTask(String taskId) {
        rLock.lock();
        try {
            String cacheKey = getCacheKey(STAFF_TASK_REL_CACHE, taskId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.getCacheMapValue(cacheKey, taskId);
            }
        } finally {
            rLock.unlock();
        }
        return null;
    }

    public void setCacheTask(CtTask task) {
        wLock.lock();
        try {
            if (!StringUtils.isEmpty(task.getStaffCode())) {
                redisCache.setCacheMapValue(STAFF_TASK_REL_CACHE + task.getStaffCode(), task.getId(), task);

            }
            if (!StringUtils.isEmpty(task.getStoreId())) {
                redisCache.setCacheMapValue(STORE_TASK_REL_CACHE + task.getStoreId(), task.getId(), task);
            }
        } finally {
            wLock.unlock();
        }
    }

    public boolean deleteCacheTask(String taskId) {
        wLock.lock();
        try {
            String cacheKey = getCacheKey(STAFF_TASK_REL_CACHE, taskId);
            if (!StringUtils.isEmpty(cacheKey)) {
                redisCache.deleteCacheMapValue(cacheKey, taskId);
            }
            String cacheKey2 = getCacheKey(STORE_TASK_REL_CACHE, taskId);
            if (!StringUtils.isEmpty(cacheKey2)) {
                redisCache.deleteCacheMapValue(cacheKey2, taskId);
            }
            // 删除关联交易
            redisCache.deleteObject(TASK_SHOPPING_REL_CACHE + taskId);
            // 删除关联订单
            redisCache.deleteObject(TASK_ORDER_REL_CACHE + taskId);
        } finally {
            wLock.unlock();
        }
        return true;
    }

    public CtOrder getCacheOrder(String taskId, String orderId) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(TASK_ORDER_REL_CACHE + taskId, orderId);
        } finally {
            rLock.unlock();
        }
    }

    public CtOrder getCacheOrder(String orderId) {
        rLock.lock();
        try {
            String cacheKey = getCacheKey(TASK_ORDER_REL_CACHE, orderId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.getCacheMapValue(cacheKey, orderId);
            }
        } finally {
            rLock.unlock();
        }
        return null;
    }

    public void setCacheOrder(CtOrder order) {
        wLock.lock();
        try {
            if (StringUtils.isEmpty(order.getTaskId())) {
                // 非值守订单
                redisCache.setCacheMapValue(TASK_ORDER_REL_CACHE + OUT_SUPERVISE_TASK_ID, order.getId(), order);
            } else {
                // 值守订单
                redisCache.setCacheMapValue(TASK_ORDER_REL_CACHE + order.getTaskId(), order.getId(), order);
            }

        } finally {
            wLock.unlock();
        }
    }

    public boolean deleteCacheOrder(String orderId) {
        wLock.lock();
        try {
            //从任务订单关联表中删除订单
            String cacheKey = getCacheKey(TASK_ORDER_REL_CACHE, orderId);
            if (!StringUtils.isEmpty(cacheKey)) {
                if (redisCache.deleteCacheMapValue(cacheKey, orderId)) {
                    //删除物品清单
                    return redisCache.deleteObject(ORDER_DETAIL_CACHE + orderId);
                } else {
                    return false;
                }
            }
        } finally {
            wLock.unlock();
        }
        return true;
    }

    public CtOrder getCacheShopping(String taskId, String shoppingId) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(TASK_SHOPPING_REL_CACHE + taskId, shoppingId);
        } finally {
            rLock.unlock();
        }
    }

    public CtShopping getCacheShopping(String shoppingId) {
        rLock.lock();
        try {
            String cacheKey = getCacheKey(TASK_SHOPPING_REL_CACHE, shoppingId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.getCacheMapValue(cacheKey, shoppingId);
            }
        } finally {
            rLock.unlock();
        }
        return null;
    }

    public void setCacheShopping(CtShopping shopping) {
        wLock.lock();
        try {
            if (StringUtils.isEmpty(shopping.getTaskId())) {
                // 非值守订单
                redisCache.setCacheMapValue(TASK_SHOPPING_REL_CACHE + OUT_SUPERVISE_TASK_ID, shopping.getId(), shopping);
            } else {
                //值守订单
                redisCache.setCacheMapValue(TASK_SHOPPING_REL_CACHE + shopping.getTaskId(), shopping.getId(), shopping);
            }
        } finally {
            wLock.unlock();
        }
    }

    public boolean deleteCacheShopping(String shoppingId) {
        wLock.lock();
        try {
            String cacheKey = getCacheKey(TASK_SHOPPING_REL_CACHE, shoppingId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.deleteCacheMapValue(cacheKey, shoppingId);
            }
        } finally {
            wLock.unlock();
        }
        return true;
    }

    public CtOrderDetail getCacheOrderDetail(String itemId) {
        rLock.lock();
        try {
            String cacheKey = getCacheKey(ORDER_DETAIL_CACHE, itemId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.getCacheMapValue(cacheKey, itemId);
            }
        } finally {
            rLock.unlock();
        }
        return null;
    }

    public CtOrderDetail getCacheOrderDetail(String orderId, String itemId) {
        rLock.lock();
        try {
            return redisCache.getCacheMapValue(ORDER_DETAIL_CACHE + orderId, itemId);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, CtOrderDetail> getCacheOrderDetails(String orderId) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(ORDER_DETAIL_CACHE + orderId);
        } finally {
            rLock.unlock();
        }
    }

    public void setCacheOrderDetail(CtOrderDetail orderDetail) {
        wLock.lock();
        try {
            if (!StringUtils.isEmpty(orderDetail.getOrderId())) {
                // 非值守订单
                redisCache.setCacheMapValue(ORDER_DETAIL_CACHE + orderDetail.getOrderId(), orderDetail.getItemId(), orderDetail);
            }
        } finally {
            wLock.unlock();
        }
    }

    public boolean deleteCacheOrderDetail(String itemId) {
        wLock.lock();
        try {
            String cacheKey = getCacheKey(ORDER_DETAIL_CACHE, itemId);
            if (!StringUtils.isEmpty(cacheKey)) {
                return redisCache.deleteCacheMapValue(cacheKey, itemId);
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

    public Map<String, CtTask> getAllTasksOfStaff(String staffCode) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(STAFF_TASK_REL_CACHE + staffCode);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, CtTask> getAllTasksOfStore(String storeId) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(STORE_TASK_REL_CACHE + storeId);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, CtOrder> getOutSuperviseOrders() {
        rLock.lock();
        try {
            return redisCache.getCacheMap(TASK_ORDER_REL_CACHE + OUT_SUPERVISE_TASK_ID);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, CtOrder> getOrdersByTask(String taskId) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(TASK_ORDER_REL_CACHE + taskId);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, CtShopping> getOutSuperviseShopping() {
        rLock.lock();
        try {
            return redisCache.getCacheMap(TASK_SHOPPING_REL_CACHE + OUT_SUPERVISE_TASK_ID);
        } finally {
            rLock.unlock();
        }
    }

    public Map<String, CtShopping> getShoppingsByTask(String taskId) {
        rLock.lock();
        try {
            return redisCache.getCacheMap(TASK_SHOPPING_REL_CACHE + taskId);
        } finally {
            rLock.unlock();
        }
    }


}
