package com.cloudtimes.promotion.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 活动店铺关系对象 ct_activity_store_rel
 *
 * @author tank
 * @date 2023-03-08
 */
class CtActivityStoreRel : BaseEntity() {
    /** 活动编号  */
    var activityId: String? = null

    /** 店铺编号  */
    var storeId: String? = null

    /** 领取数据  */
    @Excel(name = "领取数据")
    var receivedCount: Long? = null

    /** 领取金额  */
    @Excel(name = "领取金额")
    var receivedAmount: BigDecimal? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", activityId)
            .append("storeId", storeId)
            .append("receivedCount", receivedCount)
            .append("receivedAmount", receivedAmount)
            .append("delFlag", delFlag)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
