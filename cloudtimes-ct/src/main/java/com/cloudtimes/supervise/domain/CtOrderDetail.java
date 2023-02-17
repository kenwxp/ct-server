package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单物品清单对象 ct_order_detail
 *
 * @author tank
 * @date 2023-02-17
 */
@Data
public class CtOrderDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String orderId;

    /**
     * 门店编号
     */
    @Excel(name = "门店编号")
    private String storeId;

    /**
     * 物品编号
     */
    @Excel(name = "物品编号")
    private String itemId;

    /**
     * 物品名称
     */
    @Excel(name = "物品名称")
    private String itemName;

    /**
     * 物品类别
     */
    @Excel(name = "物品类别")
    private String itemTypeId;

    /**
     * 类别名称
     */
    @Excel(name = "类别名称")
    private String itemTypeName;

    /**
     * 物品数量
     */
    @Excel(name = "物品数量")
    private BigDecimal itemCount;

    /**
     * 物品单价
     */
    @Excel(name = "物品单价")
    private BigDecimal itemPrice;

    /**
     * 小计
     */
    @Excel(name = "小计")
    private BigDecimal itemSum;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 订单年月
     */
    private Long yearMonth;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

}
