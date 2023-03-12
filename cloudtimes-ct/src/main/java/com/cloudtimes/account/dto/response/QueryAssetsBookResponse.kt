package com.cloudtimes.account.dto.response

import com.cloudtimes.account.domain.CtUserAssetsBook
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "QueryAssetsBookResponse")
class QueryAssetsBookResponse : CtUserAssetsBook() {
    @Schema(description = "转账附言")
    var transferRemark: String? = null

    @Schema(description = "付款人ID")
    var payerId: String? = null

    @Schema(description = "付款人昵称")
    var payerNickName: String? = null

    @Schema(description = "付款人实名")
    var payerRealName: String? = null

    @Schema(description = "付款人微信头像")
    var payerWxAvatar: String? = null

    @Schema(description = "收款人ID")
    var payeeId: String? = null

    @Schema(description = "收款人昵称")
    var payeeNickName: String? = null

    @Schema(description = "收款人实名")
    var payeeRealName: String? = null

    @Schema(description = "收款人微信头像")
    var payeeWxAvatar: String? = null
}
