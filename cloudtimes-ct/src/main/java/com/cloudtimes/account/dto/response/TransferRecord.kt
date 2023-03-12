package com.cloudtimes.account.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime

@Schema(description = "转账记录")
class TransferRecord {
    @Schema(description = "转账ID")
    val transferId: String = ""

    @Schema(description = "转账金额")
    val transferAmount: BigDecimal? = null

    @Schema(description = "转账附言")
    val transferRemark: String = ""

    @Schema(description = "转账时间")
    val transferTime: LocalDateTime? = null

    @Schema(description = "付款人ID")
    val payerUserId: String = ""

    @Schema(description = "付款人昵称")
    val payerNickName: String = ""

    @Schema(description = "付款人姓名")
    val payerRealName: String = ""

    @Schema(description = "付款人手机号")
    val payerMobile: String = ""

    @Schema(description = "收款人ID")
    val payeeUserId: String = ""

    @Schema(description = "收款人昵称")
    val payeeNickName: String = ""

    @Schema(description = "收款人姓名")
    val payeeRealName: String = ""

    @Schema(description = "收款人手机号")
    val payeeMobile: String = ""
}
