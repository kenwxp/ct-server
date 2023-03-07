package com.cloudtimes.product.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 店铺商品采购对象 ct_shop_purchase
 *
 * @author 沈兵
 * @date 2023-03-05
 */
class CtShopPurchase : BaseEntity() {
    /** 采购编号  */
    var id: String? = null

    /** 店铺No  */
    @Excel(name = "店铺No")
    var shopNo: String? = null

    /** 供货商  */
    @Excel(name = "供货商")
    var supplier: String? = null

    /** 订单编号  */
    @Excel(name = "订单编号")
    var orderingNumber: String? = null

    /** 订单总金额  */
    @Excel(name = "订单总金额")
    var totalPrice: BigDecimal? = null

    /** 物流费用  */
    @Excel(name = "物流费用")
    var logisticsFee: BigDecimal? = null

    /** 物流编号  */
    @Excel(name = "物流编号")
    var trackingNumber: String? = null

    /** 订单状态  */
    @Excel(name = "订单状态")
    var orderingStatus: String? = null

    /** 下单时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var orderingDate: Date? = null

    /** 发货时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var deliveryTime: Date? = null

    /** 收货时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收货时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var takeTime: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("shopNo", shopNo)
            .append("supplier", supplier)
            .append("orderingNumber", orderingNumber)
            .append("totalPrice", totalPrice)
            .append("logisticsFee", logisticsFee)
            .append("trackingNumber", trackingNumber)
            .append("orderingStatus", orderingStatus)
            .append("orderingDate", orderingDate)
            .append("deliveryTime", deliveryTime)
            .append("takeTime", takeTime)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}