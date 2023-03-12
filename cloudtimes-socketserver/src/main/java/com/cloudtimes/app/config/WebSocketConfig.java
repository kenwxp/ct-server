package com.cloudtimes.app.config;

import com.cloudtimes.app.handler.CashWebSocketHandler;
import com.cloudtimes.app.handler.SuperviseWebSocketHandler;
import com.cloudtimes.app.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * websocket 配置
 *
 * @author tank
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Resource
    private CashWebSocketHandler cashWebSocketHandler;

    @Resource
    private SuperviseWebSocketHandler superviseWebSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(cashWebSocketHandler, "/cash")
                .addHandler(superviseWebSocketHandler, "/supervise")
                .addInterceptors(tokenInterceptor)
                .setAllowedOrigins("*");
    }
}
