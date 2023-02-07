package com.cloudtimes.app.interceptor;

import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.app.manager.WsSessionManager;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Component
@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static final String HEADER = "Authorization";

    @Resource
    private WsSessionManager wsSessionManager;

    /**
     * socket 建立成功事件
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getAttributes().get(JWTManager.AUTH_USER);
        if (token != null) {
            AuthUser authUser = (AuthUser) token;
            // 用户连接成功，放入在线用户缓存
            wsSessionManager.add(authUser.getId(), session);
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得客户端传来的消息
        String payload = message.getPayload();
        System.out.println("server 接收到发送的 " + payload);
        session.sendMessage(new TextMessage("server 发送给  消息 " + payload + " " + LocalDateTime.now().toString()));
    }

    /**
     * socket 断开连接时
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get(JWTManager.AUTH_USER);
        if (token != null) {
            AuthUser authUser = (AuthUser) token;
            // 用户退出，移除缓存
            wsSessionManager.remove(authUser.getId());
        }
    }

}
