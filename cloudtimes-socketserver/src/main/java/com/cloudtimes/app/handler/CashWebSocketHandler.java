package com.cloudtimes.app.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.app.manager.CashWsSessionManager;
import com.cloudtimes.app.models.AfterConnectedData;
import com.cloudtimes.app.models.CashWsData;
import com.cloudtimes.app.process.BaseEventProcess;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.DeviceState;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.spring.SpringUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@Tag(name = "收银设备长链接")
public class CashWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private CashWsSessionManager sessionManager;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtStoreMapper storeMapper;

    private final String AFTER_CONNECT_DATA = "AFTER_CONNECT_DATA";

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
            if (!StringUtils.equals(authUser.getChannelType().getCode(), ChannelType.CASH.getCode())) {
                throw new RuntimeException("非收银渠道，连接失败！");
            }
            // 用户连接成功，放入在线用户缓存
            log.info("收银websocket链接成功！！:{}", authUser.getId());
            sessionManager.add(authUser.getId(), session);
            CtDevice device = deviceMapper.selectCtDeviceById(authUser.getId());
            if (device == null) {
                throw new ServiceException("收银机不存在");
            }
            if (StringUtils.isEmpty(device.getStoreId())) {
                throw new ServiceException("收银机未绑定门店");
            }
            CtStore ctStore = storeMapper.selectCtStoreById(device.getStoreId());
            if (ctStore == null) {
                throw new ServiceException("收银机绑定门店不存在");
            }
            CashWsData cashWsData = new CashWsData();
            cashWsData.setOption(AFTER_CONNECT_DATA);
            AfterConnectedData afterConnectedData = new AfterConnectedData(ctStore.getIsSupervise());
            cashWsData.setData(afterConnectedData);
            session.sendMessage(new TextMessage(JSONObject.toJSONString(cashWsData)));
            // 更新设备状态 为在线
            device.setState(DeviceState.Online.getCode());
            device.setUpdateTime(DateUtils.getNowDate());
            deviceMapper.updateCtDevice(device);
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
    @Operation(summary = "接受消息处理")
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        AuthUser authUser = (AuthUser) session.getAttributes().get(JWTManager.AUTH_USER);
        try { // 获得客户端传来的消息
            String payload = message.getPayload();
            CashWsData receive = JSON.parseObject(payload, CashWsData.class);
            String options = receive.getOption();
            log.info("收银端收到指令 CMD:[" + options + "] DeviceId:[" + authUser.getId() + "],");
            BaseEventProcess process = SpringUtils.getBean(options);
            if (process == null) {
                CashWsData cashWsData = new CashWsData();
                cashWsData.setOption(options);
                cashWsData.setData(AjaxResult.error("无效指令：[" + payload + "]"));
                session.sendMessage(new TextMessage(JSONObject.toJSONString(cashWsData)));
                return;
            }
            String errorMsg = process.process(authUser, receive.getData());
            if (StringUtils.isNotEmpty(errorMsg)) {
                CashWsData cashWsData = new CashWsData();
                cashWsData.setOption(options);
                cashWsData.setData(AjaxResult.error(errorMsg));
                session.sendMessage(new TextMessage(JSONObject.toJSONString(cashWsData)));
                return;
            }
            CashWsData cashWsData = new CashWsData();
            cashWsData.setOption(options);
            cashWsData.setData(AjaxResult.success());
            session.sendMessage(new TextMessage(JSONObject.toJSONString(cashWsData)));
        } catch (Exception ex) {
            CashWsData cashWsData = new CashWsData();
            cashWsData.setOption("ERROR_MSG");
            cashWsData.setData(AjaxResult.error("执行指令异常"));
            session.sendMessage(new TextMessage(JSONObject.toJSONString(cashWsData)));
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
            log.info("websocket断开链接！！:{}", authUser.getId());
            sessionManager.remove(authUser.getId());
            // 设备设置离线
            // 更新收银机状态
            CtDevice device = new CtDevice();
            device.setId(authUser.getId());
            device.setState(DeviceState.Offline.getCode());
            device.setUpdateTime(DateUtils.getNowDate());
            deviceMapper.updateCtDevice(device);
        }
    }

}
