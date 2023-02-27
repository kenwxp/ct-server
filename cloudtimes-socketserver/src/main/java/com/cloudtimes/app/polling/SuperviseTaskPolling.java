package com.cloudtimes.app.polling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Slf4j
public class SuperviseTaskPolling {
    private static Map<String, Map<String, String>> subscribers;
    private static Thread sendThread;
    //读写锁
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private static final Lock wLock = rwLock.writeLock();
    //获取读锁
    private static final Lock rLock = rwLock.readLock();

    @PostConstruct
    public void start() {
        if (sendThread == null || !sendThread.isAlive()) {
            sendThread = new Thread(new Runnable() {
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
            });
            sendThread.setDaemon(true);
            sendThread.start();
        }
    }

    private void handle() {
        rLock.lock();
        try {
            log.info("轮询了一次");
        } finally {
            rLock.unlock();
        }
    }

    public static void add(String userId, String sessionId) {
        wLock.lock();
        try {
            if (subscribers == null) {
                subscribers = new HashMap<>();
            }
            Map<String, String> sessionMap = subscribers.get(userId);
            if (sessionMap == null) {
                sessionMap = new HashMap<>();
            }
            sessionMap.put(sessionId, "");
        } finally {
            wLock.unlock();
        }
    }

    public static void remove(String userId, String sessionId) {
        wLock.lock();
        try {
            Map<String, String> sessionMap = subscribers.get(userId);
            sessionMap.remove(sessionId);
        } finally {
            wLock.unlock();
        }
    }
}


