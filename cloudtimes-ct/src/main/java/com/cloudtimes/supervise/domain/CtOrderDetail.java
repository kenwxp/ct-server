package com.cloudtimes.supervise.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 订单物品清单对象 ct_order_detail
 *
 * @author wangxp
 * @date 2023-02-07
 */
@Data
@Slf4j
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
     * 物品编号
     */
    @Excel(name = "物品编号")
    private String storeNo;

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
     * 物品名称
     */
    @Excel(name = "物品名称")
    private String itemName;

    /**
     * 物品数量
     */
    @Excel(name = "物品数量")
    private String itemCount;

    /**
     * 物品单价
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "物品单价", width = 30, dateFormat = "yyyy-MM-dd")
    private Date itemPrice;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 创建日期
     */
    private Date createDate;

}
