package com.cloudtimes.common;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.enums.PaymentMode;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class PayOrderUtils {
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtRocketMqProducer producer;

    /**
     * 订单确认标志 0-未确认 1-确认成功 2-确认失败
     *
     * @param data
     * @return
     */
    public int handlePayOrder(PayOrderData data) {
//        String orderId = NoUtils.parseOrderNo(data.getClientSn());
        String orderId = data.getReflect();// 通过反射
        if (StringUtils.isEmpty(orderId)) {
            throw new ServiceException("订单号异常");
        }
        CtOrder dbOrder = orderMapper.selectCtOrderById(orderId);
        if (dbOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        int confirmFlag = 0;

        String payStatus = dbOrder.getState();
        if (StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderPaid)) {
            //最终成功状态
            payStatus = "2";
            confirmFlag = 1;
        } else if (StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderPayCanceled) ||
                StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderCanceled) ||
                StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderRefunded) ||
                StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderPartialRefunded)
        ) {
            payStatus = "3";
            confirmFlag = 2;
        }
        //处理最终态 成功的情况
        if (confirmFlag == 1) {
            CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
            cacheOrder.setState(payStatus);
            cacheOrder.setActualAmount(BigDecimal.valueOf(Long.parseLong(data.getTotalAmount())));
            if (StringUtils.equals(data.getPayway(), "1") || StringUtils.equals(data.getPayway(), "2")) {
                cacheOrder.setPaymentMode(PaymentMode.ALI_PAY.getCode());
            } else if (StringUtils.equals(data.getPayway(), "3")) {
                cacheOrder.setPaymentMode(PaymentMode.WECHAT_PAY.getCode());
            } else {
                cacheOrder.setPaymentMode(PaymentMode.E_BANK.getCode());
            }
            cacheOrder.setPaymentId(data.getSn());
            cacheOrder.setUpdateTime(new Date(Long.parseLong(data.getFinishTime())));
            cacheOrder.setState(payStatus);
            taskCache.setCacheOrder(cacheOrder);
            if (orderMapper.updateCtOrder(cacheOrder) < 1) {
                throw new ServiceException("更新订单信息失败");
            }

            //更新 购物结束时间
            if (!StringUtils.isEmpty(cacheOrder.getShoppingId())) {
                CtShopping cacheShopping = taskCache.getCacheShopping(cacheOrder.getShoppingId());
                cacheShopping.setEndTime(DateUtils.getNowDate());
                cacheShopping.setState("2");
                taskCache.setCacheShopping(cacheShopping);
                shoppingMapper.updateCtShopping(cacheShopping);
            }

            // 支付成功，发起交易开门
            OpenDoorMqData mqData = new OpenDoorMqData();
            mqData.setOption(OpenDoorOption.TRANS_OPEN_DOOR);
            mqData.setStoreId(dbOrder.getStoreId());
            mqData.setUserId(dbOrder.getUserId());
            mqData.setChannelType(ChannelType.CASH);
            producer.send(RocketMQConstants.CT_OPEN_DOOR, mqData);
        }
        return confirmFlag;
    }
}
