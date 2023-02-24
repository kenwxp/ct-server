package com.cloudtimes.account.dto.response

import com.cloudtimes.account.domain.CtUserAssetsBook
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "QueryAssetsBookResponse")
class QueryAssetsBookResponse : CtUserAssetsBook() {
    @ApiModelProperty(value = "转账附言")
    var transferRemark: String? = null

    @ApiModelProperty(value = "付款人ID")
    var payerId: String? = null

    @ApiModelProperty(value = "付款人昵称")
    var payerNickName: String? = null

    @ApiModelProperty(value = "付款人实名")
    var payerRealName: String? = null

    @ApiModelProperty(value = "付款人微信头像")
    var payerWxAvatar: String? = null

    @ApiModelProperty(value = "收款人ID")
    var payeeId: String? = null

    @ApiModelProperty(value = "收款人昵称")
    var payeeNickName: String? = null

    @ApiModelProperty(value = "收款人实名")
    var payeeRealName: String? = null

    @ApiModelProperty(value = "收款人微信头像")
    var payeeWxAvatar: String? = null
}
