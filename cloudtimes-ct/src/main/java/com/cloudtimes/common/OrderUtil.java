package com.cloudtimes.common;

import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtShopping;

import java.math.BigDecimal;
import java.util.Date;

public class OrderUtil {
    public static CtOrder getInitCtOrder() {
        CtOrder newOrder = new CtOrder();
        newOrder.setMoneyAmount(BigDecimal.valueOf(0));
        newOrder.setTotalAmount(BigDecimal.valueOf(0));
        newOrder.setDiscountAmount(BigDecimal.valueOf(0));
        newOrder.setDeductionAmount(BigDecimal.valueOf(0));
        newOrder.setActualAmount(BigDecimal.valueOf(0));
        newOrder.setItemCount(0L);
        newOrder.setIsExceptional("0");
        newOrder.setState(PayState.READY_TO_PAY.getCode());
        newOrder.setDelFlag("0");
        newOrder.setYearMonth(DateUtils.getYearMonth());
        newOrder.setCreateDate(DateUtils.getNowDate());
        newOrder.setCreateTime(DateUtils.getNowDate());
        newOrder.setUpdateTime(DateUtils.getNowDate());
        return newOrder;
    }

    public static CtShopping getInitCtShopping() {
        CtShopping newShopping = new CtShopping();
//        newShopping.setExceptionalState("0");
//        newShopping.setIsApprove("0");
//        newShopping.setIsLeadApprove("0");
//        newShopping.setIsBossApprove("0");
        newShopping.setState("0");
        newShopping.setDelFlag("0");
        newShopping.setCreateTime(new Date());
        newShopping.setUpdateTime(new Date());
        return newShopping;
    }
}
