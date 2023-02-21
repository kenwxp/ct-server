package com.cloudtimes.app.mq;

import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.app.manager.SingletonWsSessionManager;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.CashMqData;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 处理收银机模块信息
 */
@Slf4j
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.WS_CASH_DEVICE, messageModel = MessageModel.CLUSTERING)
public class CashListenerHandler implements RocketMQListener<CashMqData> {
    @Autowired
    private SingletonWsSessionManager wsSessionManager;

    @Override
    public void onMessage(CashMqData cashMqData) {
        log.info("接收到MQ消息：" + JSONObject.toJSONString(cashMqData));
        String option = cashMqData.getOption();
        try {

            wsSessionManager.sendSuccess(cashMqData.getDeviceId(), cashMqData);
        } catch (Exception ex) {
            log.error("执行指令[" + option + "]异常：" + ex.getMessage());
        }
        return;
    }
}
