package com.cloudtimes.mq.service;

import com.cloudtimes.common.PayOrderUtils;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.mq.domain.CtMQConstants;
import com.cloudtimes.mq.domain.PayOrderMsgData;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaApiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Calendar;

@Slf4j
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = CtMQConstants.QUERY_PAY_ORDER, messageModel = MessageModel.CLUSTERING)
public class QueryPayOrderListener implements RocketMQListener<PayOrderMsgData> {
    @Autowired
    private CtShouqianbaApiServiceImpl shouqianbaApiService;
    @Autowired
    private PayOrderUtils payOrderUtils;
    @Autowired
    private RocketMQTemplate mqTemplate;
    private final int loopQueryPayOrderTimeOut = 50;

    @Override
    public void onMessage(PayOrderMsgData data) {
        PayOrderData payOrderData = shouqianbaApiService.queryPayOrder(data.getPaySn(), "", data.getTerminalSN(), data.getTerminalKey());
        if (payOrderData != null && payOrderUtils.handlePayOrder(payOrderData) == 1) {
            // 发起订单库存维护
            mqTemplate.convertAndSend(CtMQConstants.MAINTAIN_STOCK, data);
            return;
        }
        // 未获取终态，则校验超时时间
        Calendar instance = Calendar.getInstance();
        instance.setTime(data.getCreateTime());
        instance.add(Calendar.SECOND, loopQueryPayOrderTimeOut);
        if (DateUtils.getNowDate().after(instance.getTime())) {
            //超时未获取最终态，则发起撤单
            if (!data.isCancelFlag()) {
                data.setCancelFlag(true);
                mqTemplate.convertAndSend(CtMQConstants.CANCEL_PAY_ORDER, data);
            }
        } else {
            // 未超时则发起信息 延时5秒
            mqTemplate.syncSend(CtMQConstants.QUERY_PAY_ORDER, MessageBuilder.withPayload(data).build(), 3000, 2);
        }
    }
}
