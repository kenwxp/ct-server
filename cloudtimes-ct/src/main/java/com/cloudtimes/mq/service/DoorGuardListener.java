package com.cloudtimes.mq.service;

import com.cloudtimes.mq.domain.CtMQConstants;
import com.cloudtimes.mq.domain.DoorOpData;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

@Slf4j
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = CtMQConstants.OPERATE_DOOR_GUARD, messageModel = MessageModel.CLUSTERING)
public class DoorGuardListener implements RocketMQListener<DoorOpData> {
    @Override
    public void onMessage(DoorOpData data) {


    }
}
