package com.cloudtimes.thirdpart.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty

@ApiModel(value = "PurchaseBookKeepRequest", description = "采购入库请求")
class YcygPurchaseBookKeepRequest {
    @ApiModelProperty(value = "入库单号", required = true)
    @field:NotEmpty(message =  "入库单号不能为空")
    @JsonProperty("djbh")
    var orderId: String = ""

    @ApiModelProperty(value = "入库门店", required = true)
    @field:NotEmpty(message =  "入库门店不能为空")
    @JsonProperty("customerid")
    var customerId: String = ""

    @ApiModelProperty(value = "备注说明")
    var remark: String = ""

    @ApiModelProperty(value = "商品列表")
    var list: List<YcygPurchaseRecord> = emptyList()
}