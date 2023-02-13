package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
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
class CtAgentDividendSettlement : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 代理会员编号  */
    @Excel(name = "代理会员编号")
    var userId: String? = null

    /** 门店编号  */
    @Excel(name = "门店编号")
    var storeId: String? = null

    /** 分润年份  */
    @Excel(name = "分润年份")
    var dividendYear: Long? = null

    /** 分润月份  */
    @Excel(name = "分润月份")
    var dividendMonth: Long? = null

    /** 手续费费率 */
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    /** 结算金额 */
    @Excel(name = "结算金额")
    var amount: BigDecimal? = null

    /** 手续费金额 */
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    /** 稅前结算金额 */
    @Excel(name = "稅前结算金额")
    var beforeTaxAmount: BigDecimal? = null

    /** 上级结算金额 */
    @Excel(name = "上级结算金额")
    var parentAmount: BigDecimal? = null

    /** 上级手续费金额 */
    @Excel(name = "上级手续费金额")
    var parentTaxAmount: BigDecimal? = null

    /** 上级稅前结算金额 */
    @Excel(name = "上级稅前结算金额")
    var parentBeforeTaxAmount: BigDecimal? = null

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

    /** 平台审核时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平台审核时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var platformApprovedTime: Date? = null

    /** 代理审核时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "代理审核时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var agentApprovedTime: Date? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("storeId", storeId)
            .append("dividendYear", dividendYear)
            .append("dividendMonth", dividendMonth)

            .append("isAgentOk", isAgentOk)
            .append("isPlatformOk", isPlatformOk)
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