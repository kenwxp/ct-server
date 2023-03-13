package com.cloudtimes.app.polling;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.WsTaskData;
import com.cloudtimes.app.models.WsTaskListData;
import com.cloudtimes.cache.CtCustomerServiceCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.system.service.ISysConfigService;
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
    private final String OPTION_NAME = "TASK_DATA";
    //读写锁
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private static final Lock wLock = rwLock.writeLock();
    //获取读锁
    private static final Lock rLock = rwLock.readLock();
    private final long INTERVAL_SECOND = 5;
    @Autowired
    private SuperviseWsSessionManager sessionManager;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtCustomerServiceCache customerServiceCache;
    @Autowired
    private ISysConfigService configService;

    @PostConstruct
    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            handle();
                        } catch (Exception ex) {
                            log.error("发生异常：", ex);
                        }
                        try {
                            Thread.sleep(INTERVAL_SECOND * 1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

    }

    private void handle() {
        log.info("任务订阅列表：{}",JSON.toJSONString(subscribers));
//        log.info("轮询任务列表开始");
        if (subscribers != null && StringUtils.isNotEmpty(subscribers)) {
            for (Map.Entry<String, Set<String>> userEntry :
                    subscribers.entrySet()) {
                String userId = userEntry.getKey();
                Set<String> sessionSet = userEntry.getValue();
                List<WsTaskListData> taskList = new ArrayList<>();
                Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(userId);
                int currentTaskCount = 0;
                int overflowTaskCount = 0;
                int overdueTaskCount = 0;
                int currentOrderCount = 0;
                if (taskMap != null && StringUtils.isNotEmpty(taskMap)) {
                    currentTaskCount = taskMap.size();
                    // 统计超额任务量
                    Long maxAcceptTaskCount = customerServiceCache.getMaxAcceptTask(userId);
                    if (currentTaskCount > maxAcceptTaskCount) {
                        overflowTaskCount = currentTaskCount - maxAcceptTaskCount.intValue();
                    }
                    for (CtTask rawTask :
                            taskMap.values()) {
                        // 统计过期订单量
                        String overduePeriod = configService.selectConfigByKey("supervise_task_overdue_period");
                        long diffSec = DateUtil.between(rawTask.getStartTime(), DateUtils.getNowDate(), DateUnit.SECOND);
                        if (diffSec > Long.parseLong(overduePeriod)) {
                            overdueTaskCount++;
                        }
                        WsTaskListData data = new WsTaskListData();
                        data.setTaskId(rawTask.getId());
                        data.setStoreId(rawTask.getStoreId());
                        data.setStoreName(rawTask.getStoreName());
                        data.setContactName(rawTask.getContactName());
                        data.setContactPhone(rawTask.getContactPhone());
                        data.setStaffCode(rawTask.getStaffCode());
                        data.setSuperviseArea(rawTask.getSuperviseArea());
                        data.setState(rawTask.getState());
                        data.setOpenLock(rawTask.isOpenLock());
                        Map<String, CtOrder> ordersMap = taskCache.getOrdersByTask(rawTask.getId());
                        if (StringUtils.isNotEmpty(ordersMap)) {
                            currentOrderCount = currentOrderCount + ordersMap.size();
                        }
                        taskList.add(data);
                    }
                }
                WsTaskData wsTaskData = new WsTaskData();
                wsTaskData.setCurrentTaskCount(String.valueOf(currentTaskCount));
                wsTaskData.setOverflowTaskCount(String.valueOf(overflowTaskCount));
                wsTaskData.setOverdueTaskCount(String.valueOf(overdueTaskCount));
                wsTaskData.setCurrentOrderCount(String.valueOf(currentOrderCount));
                ;
                wsTaskData.setAcceptState(customerServiceCache.getAcceptState(userId));
                wsTaskData.setTaskList(taskList);
                for (String sessionId : sessionSet) {
                    sessionManager.sendSuccess(userId, sessionId, OPTION_NAME, wsTaskData);
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


