package com.cloudtimes.agent.dto.response;

import com.cloudtimes.common.annotation.Excel
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
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
@Schema(description = "代理佣金")
public class CtAgentCommissionDto {

    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "代理帐号")
    var userAccount: String? = null

    @Schema(description = "上级代理账号")
    @Excel(name = "上级代理账号")
    var parentUserAccount: String? = null

    @Schema(description = "代理编号")
    var userId: String? = null

    @Schema(description = "上级代理编号")
    @Excel(name = "上级代理编号")
    var parentUserId: String? = null

    @Schema(description = "成本价格")
    @Excel(name = "成本价格")
    var costPrice: BigDecimal? = null

    @Schema(description = "手续费费率")
    @Excel(name = "成本价格")
    var taxRatio: BigDecimal? = null

    @Schema(description = "备注")
    @Excel(name = "备注")
    var remark: String? = null

    @Schema(description = "操作管理员")
    @JvmField
    @Excel(name = "操作管理员")
    var operator: String? = null

    @Schema(description = "是否删除")
    var delFlag: String? = null

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: Date? = null

    @Schema(description = "最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最近修改时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var updateTime: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("parentUserId", parentUserId)
            .append("costPrice", costPrice)
            .append("operator", operator)
            .append("delFlag", delFlag)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}



