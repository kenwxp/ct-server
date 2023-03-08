package com.cloudtimes.promotion.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 营销活动对象 ct_activity
 *
 * @author 沈兵
 * @date 2023-03-08
 */
class CtActivity : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 店主用户编号  */
    @Excel(name = "店主用户编号")
    var userId: String? = null

    /** 活动名称  */
    @Excel(name = "活动名称")
    var activityName: String? = null

    /** 活动内容  */
    @Excel(name = "活动内容")
    var activityContent: String? = null

    /** 额度  */
    @Excel(name = "额度")
    var totalAmount: BigDecimal? = null

    /** 已用额度  */
    @Excel(name = "已用额度")
    var usedAmount: BigDecimal? = null

    /** 来源类型  */
    @Excel(name = "来源类型")
    var sourceType: String? = null

    /** 期限类型  */
    @Excel(name = "期限类型")
    var termType: String? = null

    /** 是否有时段  */
    @Excel(name = "是否有时段")
    var hasTimeInterval: String? = null

    /** 是否启用  */
    @Excel(name = "是否启用")
    var isEnable: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 时段  */
    @Excel(name = "时段")
    var timeInterval: String? = null

    /** 有效开始时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效开始时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var startTime: Date? = null

    /** 有效结束时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效结束时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var endTime: Date? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("activityName", activityName)
            .append("activityContent", activityContent)
            .append("totalAmount", totalAmount)
            .append("usedAmount", usedAmount)
            .append("sourceType", sourceType)
            .append("termType", termType)
            .append("hasTimeInterval", hasTimeInterval)
            .append("isEnable", isEnable)
            .append("delFlag", delFlag)
            .append("timeInterval", timeInterval)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .append("remark", remark)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
