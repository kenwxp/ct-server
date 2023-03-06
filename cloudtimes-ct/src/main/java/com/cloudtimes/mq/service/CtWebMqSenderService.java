package com.cloudtimes.mq.service;

import com.cloudtimes.cache.CtCustomerServiceCache;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.EventType;
import com.cloudtimes.common.enums.UserType;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.SendWebMsgMqData;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.supervise.domain.CtEvents;
import com.cloudtimes.supervise.mapper.CtEventsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CtWebMqSenderService {
    @Autowired
    private CtRocketMqProducer mqProducer;
    @Autowired
    private CtEventsMapper eventsMapper;
    @Autowired
    private CtCustomerServiceCache customerServiceCache;

    /**
     * 发送客服信息
     *
     * @param newEvent
     */
    public void sendCustomerServiceMessage(CtEvents newEvent) {
        if (StringUtils.isNotEmpty(newEvent.getReceiver())) {
            newEvent.setReceiverName(customerServiceCache.getServiceName(newEvent.getReceiver()));
        }
//        newEvent.setSender();
//        newEvent.setSenderName();
//        newEvent.setShoppingId();
        newEvent.setEventType(EventType.CustomerServiceMessage.getCode());
        newEvent.setUserType(UserType.Service.getCode());
        newEvent.setSourceType(ChannelType.WEB.getCode());
        newEvent.setStopped("0");
        newEvent.setCreateDate(DateUtils.getNowDate());
        newEvent.setDelFlag("0");
        newEvent.setCreateTime(DateUtils.getNowDate());
        newEvent.setUpdateTime(DateUtils.getNowDate());
        // 新增事件
        eventsMapper.insertCtEvents(newEvent);
        // 发送mq
        SendWebMsgMqData sendWebMsgMqData = new SendWebMsgMqData();
        sendWebMsgMqData.setUserId(newEvent.getReceiver());
        sendWebMsgMqData.setTopic(newEvent.getEventName());
        sendWebMsgMqData.setContent(newEvent.getContent());
        sendWebMsgMqData.setCreateTime(DateUtils.getNowDate());
        mqProducer.send(RocketMQConstants.WS_WEB_MESSAGE, sendWebMsgMqData);
        log.info("发送消息到值守端: 客服编号：" + newEvent.getSender());
    }

    /**
     * 发送店家信息
     *
     * @param newEvent
     */
    public void sendShopkeeperMessage(CtEvents newEvent) {
        if (StringUtils.isNotEmpty(newEvent.getReceiver())) {
            newEvent.setReceiverName(customerServiceCache.getServiceName(newEvent.getReceiver()));
        }
//        newEvent.setSender();
//        newEvent.setSenderName();
//        newEvent.setShoppingId();
        newEvent.setEventType(EventType.ShopkeeperMessage.getCode());
        newEvent.setUserType(UserType.Shopkeeper.getCode());
        newEvent.setSourceType(ChannelType.MOBILE.getCode());
        newEvent.setStopped("0");
        newEvent.setCreateDate(DateUtils.getNowDate());
        newEvent.setDelFlag("0");
        newEvent.setCreateTime(DateUtils.getNowDate());
        newEvent.setUpdateTime(DateUtils.getNowDate());
        // 新增事件
        eventsMapper.insertCtEvents(newEvent);
    }

}
