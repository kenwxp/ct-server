package com.cloudtimes.agent.domain

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
/** 销售佣金结算对象 ct_agent_commission_settlement */
 *
/** @author 沈兵 */
/** @date 2023-02-03 */
 */
@ApiModel(value = "CtAgentCommissionSettlement", description = "代理佣金结算信息")
class CtAgentCommissionSettlement : BaseEntity() {
    @ApiModelProperty(value = "结算编号")
    var id: String? = null

    @ApiModelProperty(value = "门店编号")
    @Excel(name = "门店编号")
    var storeId: String? = null

    @ApiModelProperty(value = "代理会员编号")
    @Excel(name = "代理会员编号")
    var userId: String? = null

    @ApiModelProperty(value = "上级代理会员编号")
    @Excel(name = "上级代理会员编号")
    var parentUserId: String? = null

    /* 开店费用/合同价格 */
    @Excel(name = "开店费用/合同价格")
    val saleAmount: BigDecimal? = null

    @ApiModelProperty(value = "成本价格")
    @Excel(name = "成本价格")
    var costPrice: BigDecimal? = null

    @ApiModelProperty(value = "上级成本价格")
    @Excel(name = "上级成本价格")
    var parentCostPrice: BigDecimal? = null

    @ApiModelProperty(value = "手续费费率")
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    @ApiModelProperty(value = "结算金额")
    @Excel(name = "结算金额")
    var amount: BigDecimal? = null

    @ApiModelProperty(value = "手续费金额")
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    @ApiModelProperty(value = "稅前结算金额")
    @Excel(name = "稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null

    @ApiModelProperty(value = "上级结算金额")
    @Excel(name = "上级结算金额")
    var parentAmount: BigDecimal? = null

    @ApiModelProperty(value = "上级手续费金额")
    @Excel(name = "上级手续费金额")
    var parentTaxAmount: BigDecimal? = null

    @ApiModelProperty(value = "上级稅前结算金额")
    @Excel(name = "上级稅前结算金额")
    var parentBeforeTaxAmount: BigDecimal? = null

    @ApiModelProperty(value = "店铺是否上线")
    @Excel(name = "店铺是否上线")
    @get:JvmName("getIsStoreOnline")
    @set:JvmName("setIsStoreOnline")
    var isStoreOnline: String? = null

    @ApiModelProperty(value = "审核状态")
    @Excel(name = "审核状态")
    var verifyState: String? = null

    @ApiModelProperty(value = "结算状态")
    @Excel(name = "结算状态")
    var state: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "平台审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平台审核时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var platformApprovedTime: Date? = null

    @ApiModelProperty(value = "代理审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "代理审核时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var agentApprovedTime: Date? = null

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("storeId", storeId)
            .append("userId", userId)
            .append("saleAmount", saleAmount)
            .append("costPrice", costPrice)
            .append("parentCostPrice", parentCostPrice)
            .append("isStoreOnline", isStoreOnline)
            .append("verifyState", verifyState)
            .append("state", state)
            .append("remark", remark)
            .append("delFlag", delFlag)
            .append("platformApprovedTime", platformApprovedTime)
            .append("agentApprovedTime", agentApprovedTime)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
