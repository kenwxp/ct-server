package com.cloudtimes.app.polling;

import com.alibaba.fastjson2.JSON;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.WsTaskData;
import com.cloudtimes.app.models.WsTaskListData;
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
public class SuperviseTaskPolling {
    private static Map<String, Set<String>> subscribers;
    private static Thread thread;
    private final String TASK_OPTION = "TASK_DATA";
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
//        log.info("轮询任务列表开始");
        if (subscribers != null && !StringUtils.isEmpty(subscribers)) {
            for (Map.Entry<String, Set<String>> userEntry :
                    subscribers.entrySet()) {
                String userId = userEntry.getKey();
                Set<String> sessionSet = userEntry.getValue();
                List<WsTaskListData> taskList = new ArrayList<>();
                Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(userId);
                int taskCount = 0;
                int orderCount = 0;
                if (taskMap != null && !StringUtils.isEmpty(taskMap)) {
                    taskCount = taskMap.size();
                    for (CtTask rawTask :
                            taskMap.values()) {
                        WsTaskListData data = new WsTaskListData();
                        data.setTaskId(rawTask.getId());
                        data.setStoreId(rawTask.getStoreId());
                        data.setStoreName(rawTask.getStoreName());
                        data.setContactName(rawTask.getContactName());
                        data.setContactPhone(rawTask.getContactPhone());
                        data.setStaffCode(rawTask.getStaffCode());
                        data.setSuperviseArea(rawTask.getSuperviseArea());
                        data.setState(rawTask.getState());
                        Map<String, CtOrder> ordersMap = taskCache.getOrdersByTask(rawTask.getId());
                        if (!StringUtils.isEmpty(ordersMap)) {
                            orderCount = orderCount + ordersMap.size();
                        }
                        taskList.add(data);
                    }
                }
                WsTaskData wsTaskData = new WsTaskData();
                wsTaskData.setTaskCount(String.valueOf(taskCount));
                wsTaskData.setOrderCount(String.valueOf(orderCount));
                wsTaskData.setAcceptStatus(staffAcceptCache.get(userId));
                wsTaskData.setTaskList(taskList);
                for (String sessionId : sessionSet) {
                    sessionManager.sendSuccess(userId, sessionId, TASK_OPTION, wsTaskData);
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


