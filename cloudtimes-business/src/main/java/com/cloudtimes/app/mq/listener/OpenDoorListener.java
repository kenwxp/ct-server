package com.cloudtimes.app.mq.listener;

import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.mq.service.CtOpenDoorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.CT_OPEN_DOOR, messageModel = MessageModel.CLUSTERING)
public class OpenDoorListener implements RocketMQListener<OpenDoorMqData> {
    @Autowired
    private CtOpenDoorService openDoorService;

    @Override
    public void onMessage(OpenDoorMqData data) {
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
}
