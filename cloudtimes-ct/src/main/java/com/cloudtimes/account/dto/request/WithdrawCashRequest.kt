package com.cloudtimes.account.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "现金提现请求体")
data class WithdrawCashRequest (
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null,

    @Schema(description = "提现金额", required = true)
    @field:NotNull(message =  "提现金额不能为空")
    @field:DecimalMin( value = "1.00", message =  "提现金额太小")
    @field:DecimalMax( value = "10000000.00", message =  "提现金额过大")
    val amount: BigDecimal? = null,
)
