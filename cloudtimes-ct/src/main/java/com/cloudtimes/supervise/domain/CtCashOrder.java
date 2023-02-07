package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 取现订单对象 ct_cash_order
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Data
@Slf4j
public class CtCashOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 用户编号
     */
    @Excel(name = "用户编号")
    private String userId;

    /**
     * 用户类型
     */
    @Excel(name = "用户类型")
    private String userType;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String nickName;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private BigDecimal totalAmount;

    /**
     * 取现金额
     */
    @Excel(name = "取现金额")
    private BigDecimal discountAmount;

    /**
     * 手续费率
     */
    @Excel(name = "手续费率")
    private BigDecimal serviceRate;

    /**
     * 手续费
     */
    @Excel(name = "手续费")
    private BigDecimal serviceAmount;

    /**
     * 实际到账金额
     */
    @Excel(name = "实际到账金额")
    private BigDecimal actualAmount;

    /**
     * 支付渠道编号
     */
    @Excel(name = "支付渠道编号")
    private String paymentId;

    /**
     * 支付渠道名称
     */
    @Excel(name = "支付渠道名称")
    private String paymentName;

    /**
     * 取现方式
     */
    @Excel(name = "取现方式")
    private String paymentMode;

    /**
     * 支付流水号
     */
    @Excel(name = "支付流水号")
    private String paymentCode;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String descText;

    /**
     * 是否异常
     */
    @Excel(name = "是否异常")
    private String isExceptional;

    /**
     * 订单状态
     */
    @Excel(name = "订单状态")
    private String state;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;
}
