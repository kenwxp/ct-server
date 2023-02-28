package com.cloudtimes.app.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.app.models.SuperviseBusiData;
import com.cloudtimes.app.models.SuperviseWsData;
import com.cloudtimes.app.polling.SuperviseOrderPolling;
import com.cloudtimes.app.polling.SuperviseTaskPolling;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class SuperviseWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private SuperviseWsSessionManager sessionManager;
    private static final String FETCH_TASK = "FETCH_TASK";
    private static final String FETCH_ORDER = "FETCH_ORDER";

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
            if (!StringUtils.equals(authUser.getChannelType(), ChannelType.WEB.getCode())) {
                throw new RuntimeException("非管理端用户，连接失败！");
            }
            log.info("websocket链接成功！！:{}", authUser.getId());
            // 用户连接成功，放入在线用户缓存
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
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        AuthUser authUser = (AuthUser) session.getAttributes().get(JWTManager.AUTH_USER);
        try {
            // 获得客户端传来的消息
            String payload = message.getPayload();
            SuperviseWsData receive = JSON.parseObject(payload, SuperviseWsData.class);
            if (receive == null) {
                throw new ServiceException("接受数据有误");
            }
            String option = receive.getOption();
            log.info("收到指令 CMD:[" + option + "] UserId:[" + authUser.getId() + "],");
            // 处理指令
            if (StringUtils.equals(option, FETCH_TASK)) {
                if (receive.getData() == null) {
                    throw new ServiceException("无法获取业务数据");
                }
                SuperviseBusiData busiData = JacksonUtils.convertObject(receive.getData(), SuperviseBusiData.class);
                if (busiData == null) {
                    throw new ServiceException("业务数据格式有误");
                }
                if (busiData.getSubscribe() == 0) {
                    SuperviseTaskPolling.remove(authUser.getId(), session.getId());
                } else {
                    SuperviseTaskPolling.add(authUser.getId(), session.getId());
                }
            } else if (StringUtils.equals(option, FETCH_ORDER)) {
                if (receive.getData() == null) {
                    throw new ServiceException("无法获取业务数据");
                }
                SuperviseBusiData busiData = JacksonUtils.convertObject(receive.getData(), SuperviseBusiData.class);
                if (busiData == null) {
                    throw new ServiceException("业务数据格式有误");
                }
                if (busiData.getSubscribe() == 0) {
                    SuperviseOrderPolling.remove(authUser.getId(), busiData.getTaskId(), session.getId());
                } else {
                    SuperviseOrderPolling.add(authUser.getId(), busiData.getTaskId(), session.getId());
                }
            } else {
                throw new ServiceException("无效指令：【" + option + "】");
            }
            SuperviseWsData result = new SuperviseWsData().success(option);
            session.sendMessage(new TextMessage(JSONObject.toJSONString(result)));
        } catch (
                Exception e) {
            SuperviseWsData result = new SuperviseWsData().error(e.getMessage());
            session.sendMessage(new TextMessage(JSONObject.toJSONString(result)));
            log.error("处理文本消息异常：", e);
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
            log.info("websocket断开链接！！:{}", authUser.getId());
            // 用户退出，移除缓存
            sessionManager.remove(authUser.getId(), session.getId());
            //移除任务列表订阅信息
            SuperviseTaskPolling.remove(authUser.getId(), session.getId());
            //移除任务列表订阅信息
            SuperviseOrderPolling.remove(authUser.getId(), session.getId());
        }
    }

}
