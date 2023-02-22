package com.cloudtimes.common;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
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
    private CtTaskCache taskCache;

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
        //处理最终态
        if (confirmFlag == 1) {
            CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
            cacheOrder.setState(payStatus);
            cacheOrder.setActualAmount(BigDecimal.valueOf(Long.parseLong(data.getTotalAmount())));
            if (StringUtils.equals(data.getPayway(), "1") || StringUtils.equals(data.getPayway(), "2")) {
                cacheOrder.setPaymentMode("0");
            } else if (StringUtils.equals(data.getPayway(), "3")) {
                cacheOrder.setPaymentMode("1");
            } else {
                cacheOrder.setPaymentMode("2");
            }
            cacheOrder.setPaymentId(data.getSn());
            cacheOrder.setUpdateTime(new Date(Long.parseLong(data.getFinishTime())));
            cacheOrder.setState(payStatus);
            taskCache.setCacheOrder(cacheOrder);
            if (orderMapper.updateCtOrder(cacheOrder) < 1) {
                throw new ServiceException("更新订单信息失败");
            }

            //todo 支付成功，发起交易开门
        }
        return confirmFlag;
    }
}
