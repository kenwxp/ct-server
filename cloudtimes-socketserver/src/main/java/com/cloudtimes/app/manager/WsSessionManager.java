package com.cloudtimes.app.manager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/*
Session管理器，用于管理session，并注册层spring的一个Bean
*/
@Component
@Slf4j
public class WsSessionManager {
    // 使用ConcurrentHashMap在多线程写时保证线程安全
    private static final ConcurrentHashMap<String, WebSocketSession>
            SESSION_POOL = new ConcurrentHashMap<>();
    ;

    // 托管连接
    public boolean add(String id, WebSocketSession session) {
        final WebSocketSession oldSession = get(id);
        if (oldSession != null) {
            if (oldSession.isOpen()) {
                // 同频道的连接存在并且活跃的状态的话，托管失败
                return false;
            }
            //  移除失效的的老连接
            remove(id);
        }
        // 加入Map容器进行托管
        SESSION_POOL.put(id, session);
        return true;
    }

    // 获取连接
    public WebSocketSession get(String id) {
        return SESSION_POOL.get(id);
    }

    // 移除连接
    public WebSocketSession remove(String id) {
        // 从Map容器中移除
        final WebSocketSession session = SESSION_POOL.remove(id);
        if (session != null && session.isOpen()) {
            try {
                // 移除后关闭连接
                session.close();
            } catch (IOException e) {
                log.error(String.format("Session关闭异常, channelId=%s", id), e);
            }
        }
        return session;
    }
}


