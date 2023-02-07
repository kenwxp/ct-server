package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物订单对象 ct_order
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Data
@Slf4j
public class CtOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 任务编号
     */
    @Excel(name = "任务编号")
    private String taskId;

    /**
     * 门店号
     */
    @Excel(name = "门店号")
    private String storeNo;

    /**
     * 门店名称
     */
    @Excel(name = "门店名称")
    private String storeName;

    /**
     * 门店省位置
     */
    @Excel(name = "门店省位置")
    private String storeProvince;

    /**
     * 门店市位置
     */
    @Excel(name = "门店市位置")
    private String storeCity;

    /**
     * 代理编号
     */
    @Excel(name = "代理编号")
    private String agentId;

    /**
     * 店老板编号
     */
    @Excel(name = "店老板编号")
    private String bossUserId;

    /**
     * 购物编号
     */
    @Excel(name = "购物编号")
    private String shoppingId;

    /**
     * 值守员工号
     */
    @Excel(name = "值守员工号")
    private String staffCode;

    /**
     * 顾客编号
     */
    @Excel(name = "顾客编号")
    private String userId;

    /**
     * 是否组合支付
     */
    @Excel(name = "是否组合支付")
    private String isCompose;

    /**
     * 现场支付
     */
    @Excel(name = "现场支付")
    private BigDecimal moneyAmount;

    /**
     * 应付金额
     */
    @Excel(name = "应付金额")
    private BigDecimal totalAmount;

    /**
     * 折扣金额
     */
    @Excel(name = "折扣金额")
    private BigDecimal discountAmount;

    /**
     * 抵扣金额
     */
    @Excel(name = "抵扣金额")
    private BigDecimal deductionAmount;

    /**
     * 实付金额
     */
    @Excel(name = "实付金额")
    private BigDecimal actualAmount;

    /**
     * 商品总数
     */
    @Excel(name = "商品总数")
    private Long itemCount;

    /**
     * 支付渠道编号
     */
    @Excel(name = "支付渠道编号")
    private String paymentCode;

    /**
     * 支付渠道名称
     */
    @Excel(name = "支付渠道名称")
    private String paymentName;

    /**
     * 支付方式
     */
    @Excel(name = "支付方式")
    private String paymentMode;

    /**
     * 支付行为
     */
    @Excel(name = "支付行为")
    private String paymentAction;

    /**
     * 支付流水号
     */
    @Excel(name = "支付流水号")
    private String paymentId;

    /**
     * 收银机编号
     */
    @Excel(name = "收银机编号")
    private String deviceCashId;

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
    private Date createDate;

}
