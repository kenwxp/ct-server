package com.cloudtimes.thirdpart.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "YcygSuggestPurchase", description = "建议采购")
class YcygSuggestPurchase {
    @ApiModelProperty(value = "商品唯一编码（蓉城易购商品唯一编码）")
    @JsonProperty("factorycode")
    var thirdProductId = ""

    @ApiModelProperty(value = "建议采购数量")
    @JsonProperty("jycgsl")
    var suggestionCount = 0
}