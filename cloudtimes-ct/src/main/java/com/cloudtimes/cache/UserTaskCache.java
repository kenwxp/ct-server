package com.cloudtimes.cache;

import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
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
public class UserTaskCache {
    private static final String CACHE_NAME = "user_task:";
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

    @PostConstruct
    public void init() {
        //初始化加载进行中的任务
        CtTask query = new CtTask();
        query.setState("0");
        List<CtTask> taskList = taskMapper.selectCtTaskList(query);
        for (CtTask task : taskList) {
            setCacheTask(task.getStaffCode(), task.getId(), task);
        }
    }

    public void setCacheTask(String userId, String taskId, CtTask taskInfo) {
        wLock.lock();
        try {
            redisCache.setCacheMapValue(getCacheKey(userId), taskId, taskInfo);
        } finally {
            wLock.unlock();
        }
    }

    public void setCacheTasks(String userId, Map<String, CtTask> tasks) {
        wLock.lock();
        try {
            redisCache.setCacheMap(getCacheKey(userId), tasks);
        } finally {
            wLock.unlock();
        }
    }

    public CtTask getCacheTask(String userId, String taskId) {
        rLock.lock();
        try {
            CtTask cacheTask = redisCache.getCacheMapValue(getCacheKey(userId), taskId);
            if (cacheTask != null) {
                return cacheTask;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        CtTask dbTask = taskMapper.selectCtTaskById(taskId);
        if (dbTask != null) {
            setCacheTask(dbTask.getStaffCode(), dbTask.getId(), dbTask);
            return dbTask;
        }
        return null;
    }

    public Map<String, CtTask> getCacheTasks(String userId) {
        rLock.lock();
        try {
            Map<String, CtTask> ctTaskMap = redisCache.getCacheMap(userId);
            if (ctTaskMap != null) {
                return ctTaskMap;
            }
        } finally {
            rLock.unlock();
        }
        // 内存中未获取，则读数据库刷新缓存
        CtTask query = new CtTask();
        query.setState("0");
        query.setStaffCode(userId);
        List<CtTask> ctTasks = taskMapper.selectCtTaskList(query);
        if (ctTasks != null && ctTasks.size() > 0) {
            Map<String, CtTask> retMap = new HashMap<>();
            for (CtTask task :
                    ctTasks) {
                retMap.put(task.getId(), task);
            }
            setCacheTasks(userId, retMap);
            return retMap;
        }
        return null;
    }

    private String getCacheKey(String key) {
        return CACHE_NAME + key;
    }

}
