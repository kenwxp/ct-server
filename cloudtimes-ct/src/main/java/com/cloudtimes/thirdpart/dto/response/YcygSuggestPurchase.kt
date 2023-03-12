package com.cloudtimes.thirdpart.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "建议采购")
class YcygSuggestPurchase {
    @Schema(description = "商品唯一编码（蓉城易购商品唯一编码）")
    @JsonProperty("factorycode")
    var thirdProductId = ""

    @Schema(description = "建议采购数量")
    @JsonProperty("jycgsl")
    var suggestionCount = 0
}
