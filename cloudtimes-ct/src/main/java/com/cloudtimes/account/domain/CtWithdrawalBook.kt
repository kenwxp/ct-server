package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 提现登记薄对象 ct_withdrawal_book
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@ApiModel(value = "CtWithdrawalBook", description = "提现登记薄")
data class CtWithdrawalBook(
    @ApiModelProperty(value = "编号")
    var id: String? = null,

    @ApiModelProperty(value = "用户编号")
    @Excel(name = "用户编号")
    var userId: String? = null,

    @ApiModelProperty(value = "用户类型")
    @Excel(name = "用户类型")
    var userType: String? = null,

    @ApiModelProperty(value = "提现金额")
    @Excel(name = "提现金额")
    var amount: BigDecimal? = null,

    @ApiModelProperty(value = "支付序列号")
    @Excel(name = "支付序列号")
    var paySerial: String? = null,

    @ApiModelProperty(value = "第三方支付序列号")
    @Excel(name = "第三方支付序列号")
    var thirdSerial: String? = null,

    @ApiModelProperty(value = "支付状态")
    @Excel(name = "支付状态")
    var payState: String? = null,

    @ApiModelProperty(value = "申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var applyDate: LocalDate? = null,

    @ApiModelProperty(value = "支付日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var payDate: LocalDate? = null,

    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var payTime: LocalDateTime? = null,

    @ApiModelProperty(value = "操作管理员")
    @Excel(name = "操作管理员")
    var operator: String? = null,

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null,
) : BaseEntity()  {
        override fun toString(): String {
            return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("userId", userId)
                .append("userType", userType)
                .append("amount", amount)
                .append("paySerial", paySerial)
                .append("thirdSerial", thirdSerial)
                .append("payState", payState)
                .append("remark", remark)
                .append("applyDate", applyDate)
                .append("payDate", payDate)
                .append("payTime", payTime)
                .append("operator", operator)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("delFlag", delFlag)
                .toString()
        }

        companion object {
            private const val serialVersionUID = 1L
        }


    }
