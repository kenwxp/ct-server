package com.cloudtimes.mq.listener;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.DoorStateData;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * 门禁触发开门报文处理
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.TRIGGER_MESSAGE, messageModel = MessageModel.CLUSTERING)
public class DoorTriggerMessageHandler implements RocketMQListener<DoorStateData>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(DoorStateData data) {
        //todo 处理触发开门逻辑状态
        log.info("触发开门：{}====>{}", data.getDeviceSerial(), data.getUpdateTime());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }
}
