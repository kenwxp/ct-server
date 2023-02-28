package com.cloudtimes.app.polling;

import com.alibaba.fastjson2.JSON;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.WsTaskData;
import com.cloudtimes.app.models.WsVideoData;
import com.cloudtimes.cache.CacheVideoData;
import com.cloudtimes.cache.CtStoreVideoCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Slf4j
public class SuperviseTaskPolling {
    private static Map<String, Set<String>> subscribers;
    ScheduledExecutorService executorService;
    @Autowired
    private SuperviseWsSessionManager sessionManager;
    //读写锁
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private static final Lock wLock = rwLock.writeLock();
    //获取读锁
    private static final Lock rLock = rwLock.readLock();
    @Autowired
    private CtTaskCache taskCache;
    private CtStoreVideoCache storeVideoCache;
    private final String TASK_OPTION = "TASK_DATA";

    @PostConstruct
    public void start() {
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newScheduledThreadPool(5);
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            handle();
                            Thread.sleep(3000);
                        } catch (Exception ex) {
                            log.error(ex.getMessage(), ex);
                        }
                    }
                }
            }, 0, 3, TimeUnit.SECONDS);
        }

    }

    private void handle() {
        log.info(JSON.toJSONString(subscribers));
//        log.info("轮询任务列表开始");
        if (subscribers != null && !StringUtils.isEmpty(subscribers)) {
            for (Map.Entry<String, Set<String>> userEntry :
                    subscribers.entrySet()) {
                String userId = userEntry.getKey();
                Set<String> sessionSet = userEntry.getValue();
                List<WsTaskData> taskList = new ArrayList<>();
                Map<String, CtTask> tasks = taskCache.getAllTasksOfStaff(userId);
                if (tasks != null && !StringUtils.isEmpty(tasks)) {
                    for (CtTask rawTask :
                            tasks.values()) {
                        WsTaskData data = new WsTaskData();
                        data.setTaskId(rawTask.getId());
                        data.setStoreId(rawTask.getStoreId());
                        data.setStoreName(rawTask.getStoreName());
                        data.setContactName(rawTask.getContactName());
                        data.setContactPhone(rawTask.getContactPhone());
                        data.setStaffCode(rawTask.getStaffCode());
                        data.setSuperviseArea(rawTask.getSuperviseArea());
                        data.setState(rawTask.getState());
                        Map<String, CacheVideoData> videoDataMap = storeVideoCache.getCacheVideosOfStore(rawTask.getStoreId());
                        List<WsVideoData> videoList = new ArrayList<>();
                        if (videoDataMap != null) {
                            for (CacheVideoData cacheVideo :
                                    videoDataMap.values()) {
                                WsVideoData shopVideoData = new WsVideoData();
                                shopVideoData.setDeviceId(cacheVideo.getDeviceId());
                                shopVideoData.setSerial(cacheVideo.getDeviceSerial());
                                shopVideoData.setUrl(cacheVideo.getUrl());
                                shopVideoData.setToken(cacheVideo.getToken());
                                videoList.add(shopVideoData);
                            }
                        }
                        data.setVideoList(videoList);
                        taskList.add(data);
                    }
                }
                for (String sessionId : sessionSet) {
                    sessionManager.sendSuccess(userId, sessionId, TASK_OPTION, taskList);
                }
            }
        }
//        log.info("轮询任务列表结束");
    }

    public static void add(String userId, String sessionId) {
        log.info("{} 订阅获取任务列表", userId);
        if (StringUtils.isEmpty(userId) ||
                StringUtils.isEmpty(sessionId)) {
            return;
        }
        wLock.lock();
        try {
            if (subscribers == null) {
                subscribers = new HashMap<>();
            }
            Set<String> sessionSet = subscribers.get(userId);
            if (sessionSet == null) {
                sessionSet = new HashSet<>();
                subscribers.put(userId, sessionSet);
            }
            sessionSet.add(sessionId);
        } finally {
            wLock.unlock();
        }
    }

    public static void remove(String userId, String sessionId) {
        log.info("{} 取消订阅任务列表", userId);
        if (StringUtils.isEmpty(userId) ||
                StringUtils.isEmpty(sessionId)) {
            return;
        }
        wLock.lock();
        try {
            if (subscribers != null) {
                Set<String> sessionSet = subscribers.get(userId);
                if (sessionSet != null) {
                    sessionSet.remove(sessionId);
                    if (sessionSet.size() == 0) {
                        subscribers.remove(userId);
                    }
                }
            }
        } finally {
            wLock.unlock();
        }
    }
}


