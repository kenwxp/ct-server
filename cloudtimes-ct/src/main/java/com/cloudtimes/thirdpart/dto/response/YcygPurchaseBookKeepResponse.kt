package com.cloudtimes.thirdpart.dto.response

import com.cloudtimes.thirdpart.dto.request.YcygPurchaseRecord
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "YcygPurchaseBookKeepResponse", description = "采购入库应答")
class YcygPurchaseBookKeepResponse {
    @ApiModelProperty(value = "返回结果码")
    var code = 0

    @ApiModelProperty(value = "返回结果说明")
    var message = "入库成功"

    @ApiModelProperty(value = "成功商品明细")
    var data = emptyArray<YcygPurchaseRecord>()

    @ApiModelProperty(value = "失败商品明细")
    @JsonProperty("error_data")
    var errorData = emptyArray<YcygPurchaseRecord>()
}