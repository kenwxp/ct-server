package com.cloudtimes.mq.listen;

import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.mq.models.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 处理开门MQ消息
 */
@Slf4j
@Service
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.TOPIC_DEVICE, messageModel = MessageModel.BROADCASTING)
public class ListenOpenDoor implements RocketMQListener<MessageBody> {

    @Override
    public void onMessage(MessageBody messageBody) {
        log.info("接收到MQ消息：" + JSONObject.toJSONString(messageBody));
    }
}
