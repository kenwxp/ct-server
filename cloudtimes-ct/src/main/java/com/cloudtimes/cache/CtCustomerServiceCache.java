package com.cloudtimes.cache;

import cn.hutool.core.bean.BeanUtil;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtCustomerService;
import com.cloudtimes.supervise.mapper.CtCustomerServiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Slf4j
public class CtCustomerServiceCache {
    private static final String CACHE_NAME = "CUSTOMER_SERVICE:";//
    @Value("${cache_switch.customer_service}")
    private boolean enabled;
    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtCustomerServiceMapper customerServiceMapper;
    private final long SERVICE_DEPT_ID = 201;

    @PostConstruct
    public void init() {
        if (!enabled) {
            return;
        }
        log.info("===========> 初始化客服参数加载 <===============");
        //初始化加载进行中的任务
        refresh(true);
    }

    public void refresh() {
        refresh(false);
    }

    /**
     * 刷新内存
     */
    public void refresh(boolean init) {
        //初始化加载进行中的任务
        CtCustomerService query = new CtCustomerService();
        query.setDelFlag("0");
        List<CtCustomerService> customerServiceList = customerServiceMapper.selectCtCustomerServiceList(query);
        for (CtCustomerService customerService : customerServiceList) {
            Map<String, Object> map = BeanUtil.beanToMap(customerService);
            putMap(String.valueOf(customerService.getServiceId()), map);
        }
    }

    public Map<String, Object> refreshOne(String serviceId) {
        CtCustomerService customerService = customerServiceMapper.selectCtCustomerServiceByServiceId(Long.parseLong(serviceId));
        if (customerService != null) {
            Map<String, Object> map = BeanUtil.beanToMap(customerService);
            putMap(String.valueOf(customerService.getServiceId()), map);
        }
        return null;
    }


    public List<CtCustomerService> getAllCtCustomerServiceList() {
        // 获取全部前缀匹配的key 一个key一个客服
        Set<String> keys = (Set<String>) redisCache.keys(CACHE_NAME + "*");
        List<CtCustomerService> list = new ArrayList<>();
        for (String key :
                keys) {
            Map<String, Object> cacheMap = redisCache.getCacheMap(key);
            list.add(BeanUtil.mapToBean(cacheMap, CtCustomerService.class, true));
        }
        return list;
    }

    public List<CtCustomerService> getCtCustomerServiceListBySuperior(Long superiorId) {
        // 获取全部前缀匹配的key 一个key一个客服
        Set<String> keys = (Set<String>) redisCache.keys(CACHE_NAME + "*");
        List<CtCustomerService> list = new ArrayList<>();
        for (String key :
                keys) {
            Map<String, Object> cacheMap = redisCache.getCacheMap(key);
            CtCustomerService customerService = BeanUtil.mapToBean(cacheMap, CtCustomerService.class, true);
            if (customerService.getSuperiorId() == superiorId) {
                list.add(customerService);
            }
        }
        return list;
    }

    public String getServiceName(String serviceId) {
        return getMapValue(serviceId, "serviceName");
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
            Map<String, Object> cacheMap = redisCache.getCacheMap(getCacheName(serviceId));
            if (StringUtils.isEmpty(cacheMap)) {
                return cacheMap;
            }
            rLock.unlock();
            return refreshOne(serviceId);
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
