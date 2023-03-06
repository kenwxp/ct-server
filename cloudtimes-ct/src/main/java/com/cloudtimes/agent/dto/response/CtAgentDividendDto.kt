package com.cloudtimes.agent.dto.response

import com.alibaba.fastjson.annotation.JSONField
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
 * 分润配置对象 ct_agent_dividend
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@ApiModel(value = "CtAgentDividendDto", description = "分润配置")
class CtAgentDividendDto : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "代理用户编号")
    @Excel(name = "代理用户编号")
    var userId: String? = null

    @ApiModelProperty(value = "代理用户帐号")
    @Excel(name = "代理用户帐号")
    var userAccount: String? = null

    @ApiModelProperty(value = "上级代理用户编号")
    @Excel(name = "上级代理用户编号")
    var parentUserId: String? = null

    @ApiModelProperty(value = "比较类型")
    @Excel(name = "比较类型")
    var compareType: String? = null

    @ApiModelProperty(value = "营收金额")
    @Excel(name = "营收金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var billAmount: BigDecimal? = null

    @ApiModelProperty(value = "提成比例")
    @Excel(name = "提成比例")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var dividendRatio: BigDecimal? = null

    @ApiModelProperty(value = "手续费费率")
    @Excel(name = "手续费费率")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var taxRatio: BigDecimal? = null

    @ApiModelProperty(value = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("compareType", compareType)
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
