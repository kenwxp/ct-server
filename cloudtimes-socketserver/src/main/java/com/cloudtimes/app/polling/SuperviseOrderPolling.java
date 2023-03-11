package com.cloudtimes.app.polling;

import com.alibaba.fastjson2.JSON;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.WsOrderData;
import com.cloudtimes.app.models.WsOrderDetailData;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtOrderDetail;
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
public class SuperviseOrderPolling {
    private static Map<String, Map<String, Set<String>>> subscribers;
    private static Thread thread;
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
    private final String OPTION_NAME = "ORDER_DATA";


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
//        log.info("轮询订单列表开始");
        if (subscribers != null && !StringUtils.isEmpty(subscribers)) {
            for (Map.Entry<String, Map<String, Set<String>>> userEntry :
                    subscribers.entrySet()) {
                String userId = userEntry.getKey();
                Map<String, Set<String>> taskMap = userEntry.getValue();
                for (Map.Entry<String, Set<String>> taskEntry :
                        taskMap.entrySet()) {
                    String taskId = taskEntry.getKey();
                    Set<String> sessionSet = taskEntry.getValue();
                    List<WsOrderData> orderList = new ArrayList<>();
                    Map<String, CtOrder> orders = taskCache.getOrdersByTask(taskId);
                    if (orders != null && !StringUtils.isEmpty(orders)) {
                        for (CtOrder rawOrder :
                                orders.values()) {
                            WsOrderData data = new WsOrderData();
                            data.setOrderId(rawOrder.getId());
                            data.setStoreId(rawOrder.getStoreId());
                            data.setStoreName(rawOrder.getStoreName());
                            data.setShoppingId(rawOrder.getShoppingId());
                            data.setUserId(rawOrder.getUserId());
                            data.setUserPhone(rawOrder.getUserPhone());
                            data.setActualAmount(NumberUtils.centToYuan(rawOrder.getActualAmount()));
                            data.setTotalAmount(NumberUtils.centToYuan(rawOrder.getTotalAmount()));
                            data.setTotalCount(String.valueOf(rawOrder.getItemCount()));
                            data.setPaymentMode(rawOrder.getPaymentMode());
                            data.setPaymentId(rawOrder.getPaymentId());
                            data.setState(rawOrder.getState());
                            data.setCreateDate(DateUtils.formatDateTime(rawOrder.getCreateDate()));
                            Map<String, CtOrderDetail> orderDetailsMap = taskCache.getCacheOrderDetails(rawOrder.getId());
                            List<WsOrderDetailData> orderDetailList = new ArrayList<>();
                            if (orderDetailsMap != null) {
                                for (CtOrderDetail orderDetail :
                                        orderDetailsMap.values()) {
                                    WsOrderDetailData detailData = new WsOrderDetailData();
                                    detailData.setItemId(orderDetail.getItemId());
                                    detailData.setItemName(orderDetail.getItemName());
                                    detailData.setItemTypeId(orderDetail.getItemTypeId());
                                    detailData.setItemTypeName(orderDetail.getItemTypeName());
                                    detailData.setItemCount(NumberUtils.formatIntValue(orderDetail.getItemCount()));
                                    detailData.setItemPrice(NumberUtils.centToYuan(orderDetail.getItemPrice()));
                                    detailData.setItemPrimePrice(NumberUtils.centToYuan(orderDetail.getItemPrimePrice()));
                                    detailData.setItemSum(NumberUtils.centToYuan(orderDetail.getItemSum()));
                                    orderDetailList.add(detailData);
                                }
                            }
                            data.setDetail(orderDetailList);
                            orderList.add(data);
                        }
                    }
                    for (String sessionId :
                            sessionSet) {
                        sessionManager.sendSuccess(userId, sessionId, OPTION_NAME, orderList);
                    }

                }
            }
        }
//        log.info("轮询订单列表结束");
    }

    public static void add(String userId, String taskId, String sessionId) {
        if (StringUtils.isEmpty(userId) ||
                StringUtils.isEmpty(taskId) ||
                StringUtils.isEmpty(sessionId)) {
            return;
        }
        log.info("{} 订阅获取订单列表,任务id", userId, taskId);
        wLock.lock();
        try {
            if (subscribers == null) {
                subscribers = new HashMap<>();
            }
            Map<String, Set<String>> taskMap = subscribers.get(userId);
            if (taskMap == null) {
                taskMap = new HashMap<>();
                subscribers.put(userId, taskMap);
            }
            Set<String> sessionSet = taskMap.get(taskId);
            if (sessionSet == null) {
                sessionSet = new HashSet<>();
                taskMap.put(taskId, sessionSet);
            }
            sessionSet.add(sessionId);
        } finally {
            wLock.unlock();
        }
    }

    public static void remove(String userId, String taskId, String sessionId) {
        log.info("{} 取消订阅订单列表", userId);
        if (StringUtils.isEmpty(userId) ||
                StringUtils.isEmpty(taskId) ||
                StringUtils.isEmpty(sessionId)) {
            return;
        }
        wLock.lock();
        try {
            if (subscribers != null) {
                Map<String, Set<String>> taskMap = subscribers.get(userId);
                if (taskMap != null) {
                    Set<String> sessionSet = taskMap.get(taskId);
                    if (sessionSet != null) {
                        sessionSet.remove(sessionId);
                        if (sessionSet.size() == 0) {
                            taskMap.remove(taskId);
                        }
                    }
                }
            }
        } finally {
            wLock.unlock();
        }
    }

    public static void remove(String userId, String sessionId) {
        log.info("{} 取消订阅订单列表", userId);
        if (StringUtils.isEmpty(userId) ||
                StringUtils.isEmpty(sessionId)) {
            return;
        }
        wLock.lock();
        try {
            if (subscribers != null) {
                Map<String, Set<String>> taskMap = subscribers.get(userId);
                if (taskMap != null) {
                    for (Map.Entry<String, Set<String>> sessionSet :
                            taskMap.entrySet()) {
                        sessionSet.getValue().remove(sessionId);
                        if (sessionSet.getValue().size() == 0) {
                            taskMap.remove(sessionSet.getKey());
                        }
                        return;
                    }
                }
            }
        } finally {
            wLock.unlock();
        }
    }
}
