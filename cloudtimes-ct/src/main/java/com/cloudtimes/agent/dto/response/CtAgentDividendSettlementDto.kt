package com.cloudtimes.agent.dto.response

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 分润结算审核对象 ct_agent_dividend_settlement
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Schema(description = "分润结算")
class CtAgentDividendSettlementDto : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "门店编号")
    @Excel(name = "门店编号")
    var storeId: String? = null

    @Schema(description = "代理会员编号")
    @Excel(name = "代理会员编号")
    var userId: String? = null

    @Schema(description = "门店名称")
    @Excel(name = "门店名称")
    var name: String? = null

    @Schema(description = "代理会员帐号")
    @Excel(name = "代理会员帐号")
    var userAccount: String? = null

    @Schema(description = "上级代理会员编号")
    @Excel(name = "上级代理会员编号")
    var parentUserId: String? = null

    @Schema(description = "上级代理会员帐号")
    @Excel(name = "上级代理会员帐号")
    var parentUserAccount: String? = null

    @Schema(description = "分润年月")
    @Excel(name = "分润年月")
    var dividendYearMonth: Long? = null

    @Schema(description = "累计销售金额")
    @Excel(name = "累计销售金额")
    var salesTotal: BigDecimal? = null

    @Schema(description = "累计销售笔数")
    @Excel(name = "累计销售笔数")
    var salesCount: Long? = null

    @Schema(description = "提成比例")
    var dividendRatio: BigDecimal? = null

    @Schema(description = "上级提成比例")
    var parentDividendRatio: BigDecimal? = null

    @Schema(description = "手续费费率")
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    @Schema(description = "结算金额")
    @Excel(name = "结算金额")
    var amount: BigDecimal? = null

    @Schema(description = "手续费金额")
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    @Schema(description = "稅前结算金额")
    @Excel(name = "稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null

    @Schema(description = "上级结算金额")
    @Excel(name = "上级结算金额")
    var parentAmount: BigDecimal? = null

    @Schema(description = "上级手续费金额")
    @Excel(name = "上级手续费金额")
    var parentTaxAmount: BigDecimal? = null

    @Schema(description = "上级稅前结算金额")
    @Excel(name = "上级稅前结算金额")
    var parentBeforeTaxAmount: BigDecimal? = null

    @Schema(description = "审核状态")
    @Excel(name = "审核状态")
    var verifyState: String? = null

    @Schema(description = "结算状态")
    @Excel(name = "结算状态")
    var state: String? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    @Schema(description = "平台审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "平台审核时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var platformApprovedTime: Date? = null

    @Schema(description = "代理审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "代理审核时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var agentApprovedTime: Date? = null

    @Schema(description = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createDate: Date? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("storeId", storeId)
            .append("dividendYearMonth", dividendYearMonth)
            .append("verifyState", verifyState)
            .append("state", state)
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
