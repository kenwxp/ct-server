package com.cloudtimes.app.polling;

import com.alibaba.fastjson2.JSON;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.WsStaffListData;
import com.cloudtimes.cache.CacheVideoData;
import com.cloudtimes.cache.CtStaffAcceptCache;
import com.cloudtimes.cache.CtStoreVideoCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Slf4j
public class SuperviseServicePolling {
    private static Map<String, Set<String>> subscribers;
    private static Thread thread;
    private final String OPTION_NAME = "SERVICE_DATA";
    //读写锁
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private static final Lock wLock = rwLock.writeLock();
    //获取读锁
    private static final Lock rLock = rwLock.readLock();

    @Autowired
    private SuperviseWsSessionManager sessionManager;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtStaffAcceptCache staffAcceptCache;
    @Autowired
    private CtStoreVideoCache videoCache;

    @PostConstruct
    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            handle();
                            Thread.sleep(5000);
                        } catch (Exception ex) {

                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

    }

    private void handle() {
        log.info(JSON.toJSONString(subscribers));
        if (subscribers != null && !StringUtils.isEmpty(subscribers)) {
            for (Map.Entry<String, Set<String>> userEntry :
                    subscribers.entrySet()) {
                String userId = userEntry.getKey();
                Set<String> sessionSet = userEntry.getValue();
                // todo 根据客服负责人获取客服列表
                List<String> staffList = new ArrayList<>();
                staffList.add("1");
                List<WsStaffListData> retStaffList = new ArrayList<>();
                for (String staffId : staffList) {
                    String staffName = "";
                    int currentTaskCount = 0;
                    int overflowTaskCount = 0;
                    int overdueTaskCount = 0;
                    int storeCount = 0;
                    int videoCount = 0;
                    int currentOrderCount = 0;
                    int inProgressOrderCount = 0;
                    int unHandleOrderCount = 0;
                    Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(staffId);
                    if (taskMap != null && !StringUtils.isEmpty(taskMap)) {
                        currentTaskCount = taskMap.size();
                        storeCount = taskMap.size(); //todo 一个门店一个任务的情况
                        for (CtTask rawTask :
                                taskMap.values()) {
                            staffName = rawTask.getStaffName();
                            // todo 统计超额订单量
                            // todo 统计过期订单量
                            Map<String, CacheVideoData> videoMap = videoCache.getCacheVideosOfStore(rawTask.getStoreId());
                            if (videoMap != null) {
                                videoCount = videoMap.size();
                            }
                            Map<String, CtOrder> ordersMap = taskCache.getOrdersByTask(rawTask.getId());
                            if (!StringUtils.isEmpty(ordersMap)) {
                                currentOrderCount = currentOrderCount + ordersMap.size();
                                for (CtOrder order :
                                        ordersMap.values()) {
                                    //todo 统计进行中订单

                                    //todo 统计未处理订单

                                }
                            }
                        }
                    }
                    WsStaffListData wsStaffData = new WsStaffListData();
                    wsStaffData.setStaffId(staffId);
                    wsStaffData.setStaffName(staffName);
                    wsStaffData.setCurrentTaskCount(String.valueOf(currentTaskCount));
                    wsStaffData.setOverflowTaskCount(String.valueOf(overflowTaskCount));
                    wsStaffData.setOverdueTaskCount(String.valueOf(overdueTaskCount));
                    wsStaffData.setStoreCount(String.valueOf(storeCount));
                    wsStaffData.setVideoCount(String.valueOf(videoCount));
                    wsStaffData.setCurrentOrderCount(String.valueOf(currentOrderCount));
                    wsStaffData.setInProgressOrderCount(String.valueOf(inProgressOrderCount));
                    wsStaffData.setUnHandleOrderCount(String.valueOf(unHandleOrderCount));
                    retStaffList.add(wsStaffData);
                }
                for (String sessionId : sessionSet) {
                    sessionManager.sendSuccess(userId, sessionId, OPTION_NAME, retStaffList);
                }
            }
        }
//        log.info("轮询任务列表结束");
    }

    public static void add(String userId, String sessionId) {
        log.info("{} 订阅获取客服员工统计列表", userId);
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
        log.info("{} 取消订阅客服员工统计列表", userId);
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


