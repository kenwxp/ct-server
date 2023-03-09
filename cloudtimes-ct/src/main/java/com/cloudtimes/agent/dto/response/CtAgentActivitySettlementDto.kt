package com.cloudtimes.agent.dto.response

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
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
@ApiModel(value = "CtAgentActivitySettlementDto", description = "代理活动结算")
class CtAgentActivitySettlementDto : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "活动类型")
    @Excel(name = "活动类型")
    var activityType: String? = null

    @ApiModelProperty(value = "活动规则编号")
    @Excel(name = "活动规则编号")
    var activityRuleId: String? = null

    @ApiModelProperty(value = "代理用户编号")
    @Excel(name = "代理用户编号")
    var userId: String? = null

    @ApiModelProperty(value = "操作额度/实际到账金额")
    @Excel(name = "操作额度/实际到账金额")
    var amount: BigDecimal? = null

    @ApiModelProperty(value = "手续费费率")
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    @ApiModelProperty(value = "手续费金额")
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    @ApiModelProperty(value = "稅前结算金额")
    @Excel(name = "稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null

    @ApiModelProperty(value = "平台审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "平台审核时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var platformApprovedTime: Date? = null

    @ApiModelProperty(value = "代理审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "代理审核时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var agentApprovedTime: Date? = null

    @ApiModelProperty(value = "是否已达成")
    @Excel(name = "是否已达成")
    @get:JvmName("getIsFulfilled")
    @set:JvmName("setIsFulfilled")
    var isFulfilled: String? = null

    @ApiModelProperty(value = "审核状态")
    @Excel(name = "审核状态")
    var verifyState: String? = null

    @ApiModelProperty(value = "结算状态")
    @Excel(name = "结算状态")
    var state: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @ApiModelProperty(value = "达成日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "达成日期", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var fulfilledDate: Date? = null

    @ApiModelProperty(value = "达成店铺列表")
    @Excel(name = "达成店铺列表")
    var fulfilledStores: String? = null

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null


    @ApiModelProperty(value = "代理账号")
    @Excel(name = "代理账号")
    var agentUserAccount: String? = null

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
