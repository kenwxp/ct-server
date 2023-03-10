package com.cloudtimes.app.mq.listener;

import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.mq.service.CtOpenDoorService;
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
@RocketMQMessageListener(consumerGroup = "OpenDoorListener", topic = "${mq_topic.env}" + RocketMQConstants.CT_OPEN_DOOR, messageModel = MessageModel.CLUSTERING)
public class OpenDoorListener implements RocketMQListener<OpenDoorMqData>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private CtOpenDoorService openDoorService;

    @Override
    public void onMessage(OpenDoorMqData data) {
        log.info("======> 接受mq消息：" + data);
        if (data.getOption() == OpenDoorOption.TRANS_OPEN_DOOR) {
            openDoorService.transOpen(data.getStoreId(), data.getUserId(), data.getChannelType());
        } else if (data.getOption() == OpenDoorOption.EMERGENCY_OPEN_DOOR) {
            openDoorService.emergentOpen(data.getStoreId(), data.getUserId());
        } else if (data.getOption() == OpenDoorOption.OWNER_OPEN_DOOR) {
            openDoorService.ownerOpen(data.getStoreId(), data.getUserId());
        } else if (data.getOption() == OpenDoorOption.FORCE_LOCK_DOOR) {
            openDoorService.forceLock(data.getStoreId(), data.getUserId(), data.getChannelType());
        } else if (data.getOption() == OpenDoorOption.UNLOCK_DOOR) {
            openDoorService.unlock(data.getStoreId(), data.getUserId(), data.getChannelType());
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }
}
