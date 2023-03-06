package com.cloudtimes.app.mq;

import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.app.manager.SuperviseWsSessionManager;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.SendWebMsgMqData;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理收银机模块信息
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.WS_WEB_MESSAGE, messageModel = MessageModel.CLUSTERING)
public class WebMessageListenerHandler implements RocketMQListener<SendWebMsgMqData> {
    @Autowired
    private SuperviseWsSessionManager wsSessionManager;

    @Override
    public void onMessage(SendWebMsgMqData data) {
        log.info("接收到MQ消息：" + JSONObject.toJSONString(data));
        try {
            wsSessionManager.batchSendSuccess(data.getUserId(), "MESSAGE_DATA", data);
        } catch (Exception ex) {
            log.info("发送mq消息异常：" + JSONObject.toJSONString(data));
        }
    }
}
