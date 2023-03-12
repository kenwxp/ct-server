package com.cloudtimes.agent.domain

import com.alibaba.fastjson.annotation.JSONField
import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
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
@Schema(description = "分润配置")
class CtAgentDividend : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "代理用户编号")
    @Excel(name = "代理用户编号")
    var userId: String? = null

    @Schema(description = "上级代理用户编号")
    @Excel(name = "上级代理用户编号")
    var parentUserId: String? = null

    @Schema(description = "比较类型")
    @Excel(name = "比较类型")
    var compareType: String? = null

    @Schema(description = "营收金额")
    @Excel(name = "营收金额")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var billAmount: BigDecimal? = null

    @Schema(description = "提成比例")
    @Excel(name = "提成比例")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var dividendRatio: BigDecimal? = null

    @Schema(description = "手续费费率")
    @Excel(name = "手续费费率")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var taxRatio: BigDecimal? = null

    @Schema(description = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    @Schema(description = "创建日期")
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
