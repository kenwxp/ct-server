package com.cloudtimes.app.manager;


import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.common.core.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
Session管理器，用于管理session，并注册层spring的一个Bean
*/
@Component
@Slf4j
public class MultitonWsSessionManager {
    private static final int maxSessions = 20;
    // 使用ConcurrentHashMap在多线程写时保证线程安全
    private static final ConcurrentHashMap<String, Map<String, WebSocketSession>>
            SESSION_POOL = new ConcurrentHashMap<>();
    ;

    // 托管连接
    public boolean add(String id, WebSocketSession session) {
        Map<String, WebSocketSession> sessionMap = getSessionMap(id);
        if (sessionMap != null) {
            if (sessionMap.keySet().size() <= maxSessions) {
                sessionMap.put(session.getId(), session);
                return true;
            }
        } else {
            sessionMap = new HashMap<>();
            sessionMap.put(session.getId(), session);
        }
        // 加入Map容器进行托管
        SESSION_POOL.put(id, sessionMap);
        return true;
    }

    // 获取连接
    public WebSocketSession get(String id, String sessionId) {
        return SESSION_POOL.get(id).get(sessionId);
    }

    // 获取连接
    public Map<String, WebSocketSession> getSessionMap(String id) {
        return SESSION_POOL.get(id);
    }

    // 移除连接
    public WebSocketSession remove(String id, String sessionId) {
        // 从Map容器中移除
        Map<String, WebSocketSession> sessionMap = getSessionMap(id);
        if (sessionMap != null) {
            WebSocketSession session = sessionMap.remove(sessionId);
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
        return null;
    }

    public void sendSuccess(String id, Object data) {
        Map<String, WebSocketSession> sessionMap = getSessionMap(id);
        if (sessionMap != null) {
            for (WebSocketSession session :
                    sessionMap.values()) {
                if (session != null && session.isOpen()) {
                    AjaxResult success = AjaxResult.success(data);
                    TextMessage textMessage = new TextMessage(JSONObject.toJSONString(success));
                    try {
                        session.sendMessage(textMessage);
                    } catch (IOException e) {
                        log.error(String.format("发送消息失败, id=%s", id), e);
                    }
                }
            }
        }
    }

    public void sendError(String id, Object data) {
        Map<String, WebSocketSession> sessionMap = getSessionMap(id);
        if (sessionMap != null) {
            for (WebSocketSession session :
                    sessionMap.values()) {
                if (session != null && session.isOpen()) {
                    AjaxResult error = AjaxResult.error(data.toString());
                    TextMessage textMessage = new TextMessage(JSONObject.toJSONString(error));
                    try {
                        session.sendMessage(textMessage);
                    } catch (IOException e) {
                        log.error(String.format("发送消息失败, id=%s", id), e);
                    }
                }
            }
        }
    }
}


