package com.cloudtimes.app.mq.listener;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.PayOrderOption;
import com.cloudtimes.common.mq.PayOrderMqData;
import com.cloudtimes.mq.service.CtMqPayOrderService;
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
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.CT_PAY_ORDER, messageModel = MessageModel.CLUSTERING)
public class PayOrderMqListener implements RocketMQListener<PayOrderMqData>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private CtMqPayOrderService mqPayOrderService;

    @Override
    public void onMessage(PayOrderMqData data) {
        log.info("======> 接受mq消息：" + data);
        if (data.getOption() == PayOrderOption.QUERY_PAY_ORDER) {
            mqPayOrderService.queryPayOrderService(data);
        } else if (data.getOption() == PayOrderOption.CANCEL_PAY_ORDER) {
            mqPayOrderService.cancelPayOrder(data);
        } else if (data.getOption() == PayOrderOption.MAINTAIN_STOCK) {
            mqPayOrderService.maintainStock(data);
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }
}
