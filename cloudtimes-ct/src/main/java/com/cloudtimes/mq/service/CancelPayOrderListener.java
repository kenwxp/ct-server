package com.cloudtimes.mq.service;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.mq.domain.CtMQConstants;
import com.cloudtimes.mq.domain.PayOrderMsgData;
import com.cloudtimes.partner.pay.shouqianba.domain.BuzResponse;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaApiServiceImpl;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = CtMQConstants.CANCEL_PAY_ORDER, messageModel = MessageModel.CLUSTERING)
public class CancelPayOrderListener implements RocketMQListener<PayOrderMsgData> {
    @Autowired
    private CtShouqianbaApiServiceImpl shouqianbaApiService;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private RocketMQTemplate mqTemplate;

    @Override
    public void onMessage(PayOrderMsgData data) {
//        PayOrderMsgData data = JSON.parseObject(msgData.getBody(), PayOrderMsgData.class);
        BuzResponse buzResponse = shouqianbaApiService.cancelPayOrder(data.getPaySn(), "", data.getTerminalSN(), data.getTerminalKey());
        if (buzResponse != null) {
            if (StringUtils.equals(buzResponse.getResultCode(), ShouqianbaConstant.busiCancelInProgress)) {
                //发起订单查询
                mqTemplate.convertAndSend(CtMQConstants.QUERY_PAY_ORDER, data);
            } else if (StringUtils.equals(buzResponse.getResultCode(), ShouqianbaConstant.busiCancelSuccess)) {
//                String orderId = NoUtils.parseOrderNo(data.getOrderId());
                // 更新订单状态
                CtOrder cacheOrder = taskCache.getCacheOrder(data.getOrderId());
                cacheOrder.setState(PayState.PAY_FAIL.getCode());
                cacheOrder.setUpdateTime(DateUtils.getNowDate());
                taskCache.setCacheOrder(cacheOrder);
                orderMapper.updateCtOrder(cacheOrder);
            }
        }
    }
}
