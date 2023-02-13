package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 分润配置对象 ct_agent_dividend
 *
 * @author 沈兵
 * @date 2023-02-03
 */
class CtAgentDividend : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 代理用户编号  */
    @Excel(name = "代理用户编号")
    var userId: String? = null

    /** 营收金额  */
    @Excel(name = "营收金额")
    var billAmount: BigDecimal? = null

    /** 提成比例  */
    @Excel(name = "提成比例")
    var dividendRatio: BigDecimal? = null

    /** 手续费费率  */
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    /** 操作管理员  */
    @Excel(name = "操作管理员")
    var operator: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("billAmount", billAmount)
            .append("dividendRatio", dividendRatio)
            .append("taxRatio", taxRatio)
            .append("operator", operator)
            .append("remark", remark)
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