package com.cloudtimes.account.dto.request

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.DecimalMax

@Schema(description = "现金转账请求体")
class TransferCashRequest {
    @Schema(description = "付款人编号", required = true)
    @field:NotEmpty(message =  "付款人编号不能为空")
    @field:NotNull(message =  "付款人编号不能为空")
    var payerUserId: String? = null;

    @Schema(description = "收款人编号", required = true)
    @field:NotEmpty(message =  "收款人编号不能为空")
    @field:NotNull(message =  "收款人编号不能为空")
    var payeeUserId: String? = null;

    @Schema(description = "附言", required = true)
    @field:NotNull(message =  "转账附言不能为空")
    @field:NotEmpty(message =  "转账附言不能为空")
    val remark: String? = null

    @Schema(description = "转账金额", required = true)
    @field:NotNull(message =  "转账金额不能为空")
    @DecimalMin("1.00", message = "转账金额太小")
    @DecimalMax("50000.00", message = "转账金额不能大于5万元")
    val amount: BigDecimal? = null

    @Schema(description = "手机验证码UUID", required = true)
    @field:NotNull(message =  "手机验证码UUID不能为空")
    @field:NotEmpty(message =  "手机验证码UUID不能为空")
    val verifyUUID: String? = null

    @Schema(description = "手机验证码", required = true)
    @field:NotNull(message =  "手机验证码不能为空")
    @field:NotEmpty(message =  "手机验证码不能为空")
    val verifyCode: String? = null
}
