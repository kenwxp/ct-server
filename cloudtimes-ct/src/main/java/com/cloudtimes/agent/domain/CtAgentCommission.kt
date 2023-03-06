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
 * 代理销售佣金设置对象 ct_agent_commission
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@ApiModel(value = "CtAgentCommissionDto", description = "代理佣金")
class CtAgentCommission : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "代理编号")
    var userId: String? = null


    @ApiModelProperty(value = "上级代理编号")
    @Excel(name = "上级代理编号")
    var parentUserId: String? = null

    @ApiModelProperty(value = "成本价格")
    @Excel(name = "成本价格")
    var costPrice: BigDecimal? = null

    @ApiModelProperty(value = "手续费费率")
    @Excel(name = "成本价格")
    var taxRatio: BigDecimal? = null

    @ApiModelProperty(value = "操作管理员")
    @JvmField
    @Excel(name = "操作管理员")
    var operator: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
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
