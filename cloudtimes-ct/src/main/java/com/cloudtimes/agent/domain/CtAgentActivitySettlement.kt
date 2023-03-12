package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
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
@Schema(description = "代理活动结算")
class CtAgentActivitySettlement : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "活动类型")
    @Excel(name = "活动类型")
    var activityType: String? = null

    @Schema(description = "活动规则编号")
    @Excel(name = "活动规则编号")
    var activityRuleId: String? = null

    @Schema(description = "代理用户编号")
    @Excel(name = "代理用户编号")
    var userId: String? = null

    @Schema(description = "操作额度/实际到账金额")
    @Excel(name = "操作额度/实际到账金额")
    var amount: BigDecimal? = null

    @Schema(description = "手续费费率")
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    @Schema(description = "手续费金额")
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    @Schema(description = "稅前结算金额")
    @Excel(name = "稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null

    @Schema(description = "平台审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "平台审核时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var platformApprovedTime: Date? = null

    @Schema(description = "代理审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "代理审核时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var agentApprovedTime: Date? = null

    @Schema(description = "是否已达成")
    @Excel(name = "是否已达成")
    var isFulfilled: String? = null

    @Schema(description = "审核状态")
    @Excel(name = "审核状态")
    var verifyState: String? = null

    @Schema(description = "结算状态")
    @Excel(name = "结算状态")
    var state: String? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    @Schema(description = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @Schema(description = "达成日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "达成日期", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var fulfilledDate: Date? = null

    @Schema(description = "达成店铺列表")
    @Excel(name = "达成店铺列表")
    var fulfilledStores: String? = null

    @Schema(description = "创建日期")
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
            .append("verifyState", verifyState)
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
