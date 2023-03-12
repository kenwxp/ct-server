package com.cloudtimes.thirdpart.dto.response

import com.cloudtimes.thirdpart.dto.request.YcygPurchaseRecord
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "采购入库应答")
class YcygPurchaseBookKeepResponse {
    @Schema(description = "返回结果码")
    var code = 0

    @Schema(description = "返回结果说明")
    var message = "入库成功"

    @Schema(description = "成功商品明细")
    var data = emptyArray<YcygPurchaseRecord>()

    @Schema(description = "失败商品明细")
    @JsonProperty("error_data")
    var errorData = emptyArray<YcygPurchaseRecord>()
}
