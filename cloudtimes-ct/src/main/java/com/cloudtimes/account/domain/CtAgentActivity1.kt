package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 代理活动1对象 ct_agent_activity1
 *
 * @author 沈兵
 * @date 2023-02-13
 */
class CtAgentActivity1 : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 标题  */
    @Excel(name = "标题")
    var title: String? = null

    /** 内容  */
    @Excel(name = "内容")
    var content: String? = null

    /** 新开店数量  */
    @Excel(name = "新开店数量")
    var storeCount: Long? = null

    /** 奖励金额  */
    @Excel(name = "奖励金额")
    var rewardMoney: BigDecimal? = null

    /** 手续费费率  */
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    /** 结算时长条件  */
    @Excel(name = "结算时长条件")
    var timeSpan: Long? = null

    /** 操作管理员  */
    @Excel(name = "操作管理员")
    var operator: String? = null

    /** 是否启用  */
    @Excel(name = "是否启用")
    var isEnabled: String? = null

    /** 活动状态  */
    @Excel(name = "活动状态")
    var state: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 开始时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var startTime: Date? = null

    /** 结束时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var endTime: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("title", title)
            .append("content", content)
            .append("storeCount", storeCount)
            .append("rewardMoney", rewardMoney)
            .append("taxRatio", taxRatio)
            .append("timeSpan", timeSpan)
            .append("operator", operator)
            .append("isEnabled", isEnabled)
            .append("state", state)
            .append("delFlag", delFlag)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}