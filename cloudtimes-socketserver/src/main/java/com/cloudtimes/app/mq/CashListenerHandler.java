package com.cloudtimes.app.mq;

import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.app.manager.CashWsSessionManager;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.CashMqData;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理收银机模块信息
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "PayOrderMqListener", topic = RocketMQConstants.WS_CASH_DEVICE, selectorType = SelectorType.TAG, selectorExpression = "${spring.profiles.active}", messageModel = MessageModel.CLUSTERING)
public class CashListenerHandler implements RocketMQListener<CashMqData>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private CashWsSessionManager wsSessionManager;

    @Override
    public void onMessage(CashMqData cashMqData) {
        log.info("接收到MQ消息：" + JSONObject.toJSONString(cashMqData));
        String option = cashMqData.getOption();
        try {
            wsSessionManager.sendSuccess(cashMqData.getDeviceId(), cashMqData);
        } catch (Exception ex) {
            log.error("执行指令[" + option + "]异常：" + ex.getMessage());
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName(this.getClass().getName());
    }
}
