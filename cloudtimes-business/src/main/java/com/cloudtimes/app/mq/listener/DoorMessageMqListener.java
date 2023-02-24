package com.cloudtimes.app.mq.listener;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.DoorMessageMqData;
import com.cloudtimes.mq.service.CtDoorMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.CT_DOOR_MESSAGE, messageModel = MessageModel.CLUSTERING)
public class DoorMessageMqListener implements RocketMQListener<DoorMessageMqData>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private CtDoorMessageService doorMessageService;

    @Override
    public void onMessage(DoorMessageMqData data) {
        log.info("=====> 接收mq消息：" + data.toString());
        if (data.getOption() == 0) {
            doorMessageService.handleStateMessage(data);
        } else if (data.getOption() == 1) {
            doorMessageService.handleTriggerMessage(data);
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }

}
