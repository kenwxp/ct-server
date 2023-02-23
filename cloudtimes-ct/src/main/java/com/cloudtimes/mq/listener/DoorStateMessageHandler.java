package com.cloudtimes.mq.listener;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.DoorStateData;
import com.cloudtimes.mq.domain.CtMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * 门禁状态相关报文处理
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.STATE_MESSAGE, messageModel = MessageModel.CLUSTERING)
public class DoorStateMessageHandler implements RocketMQListener<DoorStateData>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(DoorStateData data) {
        //todo 处理门禁状态
        log.info("更新门禁设备刷新时间：{}====>{}", data.getDeviceSerial(), data.getUpdateTime());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }
}
