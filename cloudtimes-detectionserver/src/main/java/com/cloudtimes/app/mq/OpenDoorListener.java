package com.cloudtimes.app.mq;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.mq.CtOpenDoorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "OpenDoorListener_${spring.profiles.active}", topic = RocketMQConstants.CT_OPEN_DOOR, selectorType = SelectorType.TAG, selectorExpression = "${spring.profiles.active}", messageModel = MessageModel.CLUSTERING)
public class OpenDoorListener implements RocketMQListener<OpenDoorMqData>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private CtOpenDoorService openDoorService;
    private final long VALID_PERIOD_SECOND = 10;

    @Override
    public void onMessage(OpenDoorMqData data) {
        try {
            log.info("接受mq消息，开始处理开门请求，门店编号:{}", data.getStoreId());
            if (DateUtil.between(data.getSendTime(), DateUtils.getNowDate(), DateUnit.SECOND, true) > VALID_PERIOD_SECOND) {
                log.info("开门mq消息超时，略过");
                return;
            }
            if (data.getOption() == OpenDoorOption.TRANS_OPEN_DOOR) {
                openDoorService.transOpen(data);
            } else if (data.getOption() == OpenDoorOption.EMERGENCY_OPEN_DOOR) {
                openDoorService.emergentOpen(data);
            } else if (data.getOption() == OpenDoorOption.OWNER_OPEN_DOOR) {
                openDoorService.ownerOpen(data);
            } else if (data.getOption() == OpenDoorOption.FORCE_LOCK_DOOR) {
                openDoorService.forceLock(data);
            } else if (data.getOption() == OpenDoorOption.UNLOCK_DOOR) {
                openDoorService.unlock(data);
            } else if (data.getOption() == OpenDoorOption.SETTING_DOOR_ACCESS) {
                openDoorService.setDoorAccess(data);
            }
        } catch (Exception e) {
            log.error("门禁操作失败", e);
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }
}
