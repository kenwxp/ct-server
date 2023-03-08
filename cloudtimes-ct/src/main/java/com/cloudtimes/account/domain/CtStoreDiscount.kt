package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal

/**
 * 店铺会员折扣对象 ct_store_discount
 *
 * @author 沈兵
 * @date 2023-03-08
 */
class CtStoreDiscount : BaseEntity() {
    /** 店铺编号  */
    var storeId: String? = null

    /** VIP会员类型  */
    var vipType: String? = null

    /** 折扣率, 如0.95表示打95折  */
    @Excel(name = "折扣率, 如0.95表示打95折")
    var discountRatio: BigDecimal? = null

    /** 是否启用  */
    @Excel(name = "是否启用")
    @get:JvmName("getIsEnable")
    @set:JvmName("setIsEnable")
    var isEnable: String? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("storeId", storeId)
            .append("vipType", vipType)
            .append("discountRatio", discountRatio)
            .append("isEnable", isEnable)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}