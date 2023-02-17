package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 代理活动2规则对象 ct_agent_activity2_rule
 *
 * @author 沈兵
 * @date 2023-02-17
 */
class CtAgentActivity2Rule : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 活动编号  */
    @Excel(name = "活动编号")
    var activityId: String? = null

    /** 活动规则标题  */
    @Excel(name = "活动规则标题")
    var title: String? = null

    /** 活动规则内容  */
    @Excel(name = "活动规则内容")
    var content: String? = null

    /** 活动类型  */
    @Excel(name = "活动类型")
    var activityType: String? = null

    /** 活动区域  */
    @Excel(name = "活动区域")
    var region: String? = null

    /** 奖励门店数  */
    @Excel(name = "奖励门店数")
    var storeCount: Long? = null

    /** 已奖励门店数  */
    @Excel(name = "已奖励门店数")
    var usedStoreCount: Long? = null

    /** 手续费费率  */
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

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
            .append("activityId", activityId)
            .append("title", title)
            .append("content", content)
            .append("activityType", activityType)
            .append("region", region)
            .append("storeCount", storeCount)
            .append("usedStoreCount", usedStoreCount)
            .append("taxRatio", taxRatio)
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
