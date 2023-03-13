package com.cloudtimes.common.service;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.enums.PaymentMode;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.mapper.CtOrderDetailMapper;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Component
public class PayOrderHandlerService {
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtOrderDetailMapper orderDetailMapper;
    @Autowired
    private CtTaskCache taskCache;

    public static final int NOT_CONFIRM = 0;
    public static final int CONFIRM_SUCCESS = 1;
    public static final int CONFIRM_FAIL = 2;

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
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        int confirmFlag = NOT_CONFIRM;
        if (StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderPaid)) {
            //最终成功状态
            cacheOrder.setState(PayState.PAY_SUCCESS.getCode());
            confirmFlag = CONFIRM_SUCCESS;
        } else if (StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderPayCanceled) ||
                StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderCanceled) ||
                StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderRefunded) ||
                StringUtils.equals(data.getOrderStatus(), ShouqianbaConstant.orderPartialRefunded)
        ) {
            cacheOrder.setState(PayState.PAY_FAIL.getCode());
            confirmFlag = CONFIRM_FAIL;
        }
        //处理最终态
        if (confirmFlag != NOT_CONFIRM) {
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
            if (orderMapper.updateCtOrder(cacheOrder) < 1) {
                throw new ServiceException("更新订单信息失败");
            }
            taskCache.setCacheOrder(cacheOrder);
            //更新 购物结束时间
            if (!StringUtils.isEmpty(cacheOrder.getShoppingId())) {
                CtShopping cacheShopping = taskCache.getCacheShopping(cacheOrder.getShoppingId());
                cacheShopping.setEndTime(DateUtils.getNowDate());
                cacheShopping.setState("2");
                shoppingMapper.updateCtShopping(cacheShopping);
                taskCache.setCacheShopping(cacheShopping);
            }
            //持久化物品清单
            Map<String, CtOrderDetail> cacheOrderDetails = taskCache.getCacheOrderDetails(orderId);
            for (CtOrderDetail item :
                    cacheOrderDetails.values()) {
                orderDetailMapper.insertCtOrderDetail(item);
            }
        }
        return confirmFlag;
    }
}
