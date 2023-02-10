package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 代理销售佣金设置对象 ct_agent_commission
 *
 * @author 沈兵
 * @date 2023-02-03
 */
class CtAgentCommission : BaseEntity() {
    /** 代理编号  */
    var id: String? = null

    /** 上级代理编号  */
    @Excel(name = "上级代理编号")
    var parentUserId: String? = null

    /** 成本价格  */
    @Excel(name = "成本价格")
    var costPrice: BigDecimal? = null

    /** 操作管理员  */
    @JvmField
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
            .append("parentUserId", parentUserId)
            .append("costPrice", costPrice)
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