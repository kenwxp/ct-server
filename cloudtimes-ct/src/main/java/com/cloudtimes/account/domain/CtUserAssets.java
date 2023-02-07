package com.cloudtimes.account.domain;

import com.cloudtimes.common.annotation.Excel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Data
@Slf4j
public class CtUserAssets {
    /**
     * 用户id uuid
     */
    private String userId;
    /**
     * 顾客现金余额
     */
    @Excel(name = "顾客现金余额")
    private BigDecimal customerCashAmount;

    /**
     * 顾客积分余额
     */
    @Excel(name = "顾客积分余额")
    private BigDecimal customerTotalWithdrawal;

    /**
     * 顾客累计已提现
     */
    @Excel(name = "顾客累计已提现")
    private BigDecimal customerScorePoints;

    /**
     * 顾客信用分
     */
    @Excel(name = "顾客信用分")
    private Long customerCreditScore;
}
