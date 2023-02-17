package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 代理活动结算对象 ct_agent_activity_settlement
 *
 * @author 沈兵
 * @date 2023-02-17
 */
class CtAgentActivitySettlement : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 活动类型  */
    @Excel(name = "活动类型")
    var activityType: String? = null

    /** 活动规则编号  */
    @Excel(name = "活动规则编号")
    var activityRuleId: String? = null

    /** 代理用户编号  */
    @Excel(name = "代理用户编号")
    var userId: String? = null

    /** 操作额度/实际到账金额  */
    @Excel(name = "操作额度/实际到账金额")
    var amount: BigDecimal? = null

    /** 手续费费率  */
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    /** 手续费金额  */
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    /** 稅前结算金额  */
    @Excel(name = "稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null

    /** 平台审核时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平台审核时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var platformApprovedTime: Date? = null

    /** 代理审核时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "代理审核时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var agentApprovedTime: Date? = null

    /** 是否已达成  */
    @Excel(name = "是否已达成")
    var isFulfilled: String? = null

    /** 代理是否审核  */
    @Excel(name = "代理是否审核")
    var isAgentOk: String? = null

    /** 平台是否审核  */
    @Excel(name = "平台是否审核")
    var isPlatformOk: String? = null

    /** 结算状态  */
    @Excel(name = "结算状态")
    var state: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 操作管理员  */
    @Excel(name = "操作管理员")
    var operator: String? = null

    /** 达成日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "达成日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var fulfilledDate: Date? = null

    /** 达成店铺列表  */
    @Excel(name = "达成店铺列表")
    var fulfilledStores: String? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("activityType", activityType)
            .append("activityRuleId", activityRuleId)
            .append("userId", userId)
            .append("amount", amount)
            .append("taxRatio", taxRatio)
            .append("taxAmount", taxAmount)
            .append("beforeTaxAmount", beforeTaxAmount)
            .append("platformApprovedTime", platformApprovedTime)
            .append("agentApprovedTime", agentApprovedTime)
            .append("isFulfilled", isFulfilled)
            .append("isAgentOk", isAgentOk)
            .append("isPlatformOk", isPlatformOk)
            .append("state", state)
            .append("delFlag", delFlag)
            .append("operator", operator)
            .append("remark", remark)
            .append("fulfilledDate", fulfilledDate)
            .append("fulfilledStores", fulfilledStores)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
