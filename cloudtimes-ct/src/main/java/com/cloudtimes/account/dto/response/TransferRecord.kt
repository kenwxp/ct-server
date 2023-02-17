package com.cloudtimes.account.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.time.LocalDateTime

@ApiModel(value = "TransferRecord", description = "转账记录")
class TransferRecord {
    @ApiModelProperty(value = "转账ID")
    val transferId: String = ""

    @ApiModelProperty(value = "转账金额")
    val transferAmount: BigDecimal? = null

    @ApiModelProperty(value = "转账附言")
    val transferRemark: String = ""

    @ApiModelProperty(value = "转账时间")
    val transferTime: LocalDateTime? = null

    @ApiModelProperty(value = "付款人ID")
    val payerUserId: String = ""

    @ApiModelProperty(value = "付款人昵称")
    val payerNickName: String = ""

    @ApiModelProperty(value = "付款人姓名")
    val payerRealName: String = ""

    @ApiModelProperty(value = "付款人手机号")
    val payerMobile: String = ""

    @ApiModelProperty(value = "收款人ID")
    val payeeUserId: String = ""

    @ApiModelProperty(value = "收款人昵称")
    val payeeNickName: String = ""

    @ApiModelProperty(value = "收款人姓名")
    val payeeRealName: String = ""

    @ApiModelProperty(value = "收款人手机号")
    val payeeMobile: String = ""
}