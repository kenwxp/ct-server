package com.cloudtimes.thirdpart.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "YcygSuggestionPurchase", description = "建议采购")
class YcygSuggestionPurchase {
    @ApiModelProperty(value = "商品唯一编码（蓉城易购商品唯一编码）")
    @JsonProperty("factorycode")
    var thirdPartProductId = ""

    @ApiModelProperty(value = "建议采购数量")
    @JsonProperty("jycgsl")
    var suggestionCount = ""
}