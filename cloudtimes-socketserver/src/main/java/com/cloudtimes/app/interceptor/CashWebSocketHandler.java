package com.cloudtimes.app.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.app.manager.CashWsSessionManager;
import com.cloudtimes.app.models.CashWsData;
import com.cloudtimes.app.process.BaseEventProcess;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.spring.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@Api(tags = "收银设备长链接")
public class CashWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private CashWsSessionManager sessionManager;

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
            if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
                throw new RuntimeException("非收银渠道，连接失败！");
            }
            // 用户连接成功，放入在线用户缓存
            log.info("收银websocket链接成功！！:{}",authUser.getId());
            sessionManager.add(authUser.getId(), session);
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
    @ApiOperation("接受消息处理")
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        AuthUser authUser = (AuthUser) session.getAttributes().get(JWTManager.AUTH_USER);
        // 获得客户端传来的消息
        String payload = message.getPayload();
        CashWsData receive = JSON.parseObject(payload, CashWsData.class);
        String options = receive.getOptions();
        log.info("CashWebSocketHandler receive CMD:[" + options + "] DeviceId:[" + authUser.getId() + "],");
        BaseEventProcess process = SpringUtils.getBean(options);
        if (process == null) {
            AjaxResult ajaxResult = AjaxResult.error("无效指令：[" + payload + "]");
            session.sendMessage(new TextMessage(JSONObject.toJSONString(ajaxResult)));
            return;
        }
        try {
            Object obj = process.process(authUser, receive.getData());
            if (obj != null) {
                session.sendMessage(new TextMessage(JSONObject.toJSONString(obj)));
            }
        } catch (Exception ex) {
            AjaxResult ajaxResult = AjaxResult.error("执行指令异常：[" + payload + "]");
            session.sendMessage(new TextMessage(JSONObject.toJSONString(ajaxResult)));
        }
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
            log.info("websocket断开链接！！:{}",authUser.getId());
            sessionManager.remove(authUser.getId());
        }
    }

}
