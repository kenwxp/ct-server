package com.cloudtimes.app.polling;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.WsStaffListData;
import com.cloudtimes.cache.CacheVideoData;
import com.cloudtimes.cache.CtCustomerServiceCache;
import com.cloudtimes.cache.CtStoreVideoCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.domain.CtCustomerService;
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
    private CtCustomerServiceCache customerServiceCache;
    @Autowired
    private CtStoreVideoCache videoCache;
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
                            log.error("发生异常：",ex);
                        }
                        try {
                            Thread.sleep(5000);
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
        log.info(JSON.toJSONString(subscribers));
        if (subscribers != null && !StringUtils.isEmpty(subscribers)) {
            for (Map.Entry<String, Set<String>> userEntry :
                    subscribers.entrySet()) {
                String superiorId = userEntry.getKey();
                Set<String> sessionSet = userEntry.getValue();
                // 根据客服负责人获取客服列表
                List<CtCustomerService> customerServiceList = customerServiceCache.getCtCustomerServiceListBySuperior(Long.parseLong(superiorId));
                List<WsStaffListData> retStaffList = new ArrayList<>();
                for (CtCustomerService customerService : customerServiceList) {
                    int currentTaskCount = 0;
                    int overflowTaskCount = 0;
                    int overdueTaskCount = 0;
                    int storeCount = 0;
                    int videoCount = 0;
                    int currentOrderCount = 0;
                    int inProgressOrderCount = 0;
                    int unHandleOrderCount = 0;
                    Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(String.valueOf(customerService.getServiceId()));
                    if (taskMap != null && !StringUtils.isEmpty(taskMap)) {
                        currentTaskCount = taskMap.size();
                        // 统计超额任务量
                        Long maxAcceptTaskCount = customerServiceCache.getMaxAcceptTask(String.valueOf(customerService.getServiceId()));
                        if (currentTaskCount > maxAcceptTaskCount) {
                            overflowTaskCount = currentTaskCount - maxAcceptTaskCount.intValue();
                        }
                        storeCount = taskMap.size(); //todo 一个门店一个任务的情况
                        for (CtTask rawTask :
                                taskMap.values()) {
                            // 统计过期订单量
                            String overduePeriod = configService.selectConfigByKey("supervise_task_overdue_period");
                            long diffSec = DateUtil.between(rawTask.getStartTime(), DateUtils.getNowDate(), DateUnit.SECOND);
                            if (diffSec > Long.parseLong(overduePeriod)) {
                                overdueTaskCount++;
                            }
                            Map<String, CacheVideoData> videoMap = videoCache.getCacheVideosOfStore(rawTask.getStoreId());
                            if (videoMap != null) {
                                videoCount = videoCount + videoMap.size();
                            }
                            Map<String, CtOrder> ordersMap = taskCache.getOrdersByTask(rawTask.getId());
                            if (!StringUtils.isEmpty(ordersMap)) {
                                currentOrderCount = currentOrderCount + ordersMap.size();
                                for (CtOrder order :
                                        ordersMap.values()) {
                                    if (StringUtils.equals(order.getState(), PayState.READY_TO_PAY.getCode())) {
                                        // 统计进行中订单
                                        inProgressOrderCount++;
                                    } else {
                                        // 统计未处理订单
                                        unHandleOrderCount++;
                                    }
                                }
                            }
                        }
                    }
                    WsStaffListData wsStaffData = new WsStaffListData();
                    wsStaffData.setStaffId(String.valueOf(customerService.getServiceId()));
                    wsStaffData.setStaffName(customerService.getServiceName());
                    wsStaffData.setCurrentTaskCount(String.valueOf(currentTaskCount));
                    wsStaffData.setOverflowTaskCount(String.valueOf(overflowTaskCount));
                    wsStaffData.setOverdueTaskCount(String.valueOf(overdueTaskCount));
                    wsStaffData.setStoreCount(String.valueOf(storeCount));
                    wsStaffData.setVideoCount(String.valueOf(videoCount));
                    wsStaffData.setCurrentOrderCount(String.valueOf(currentOrderCount));
                    wsStaffData.setInProgressOrderCount(String.valueOf(inProgressOrderCount));
                    wsStaffData.setUnHandleOrderCount(String.valueOf(unHandleOrderCount));
                    wsStaffData.setAcceptState(customerService.getAcceptState());   // 获取接单开关
                    retStaffList.add(wsStaffData);
                }
                for (String sessionId : sessionSet) {
                    sessionManager.sendSuccess(superiorId, sessionId, OPTION_NAME, retStaffList);
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


