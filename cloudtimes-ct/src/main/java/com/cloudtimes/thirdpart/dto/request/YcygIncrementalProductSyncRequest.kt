package com.cloudtimes.thirdpart.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty

@ApiModel(value = "YcygIncrementalProductSyncRequest", description = "增量商品同步请求")
class YcygIncrementalProductSyncRequest {
    @ApiModelProperty(value = "商品列表")
    @field:NotEmpty(message = "商品列表不能为空")
    var list: List<RcygProductRecord> = emptyList()

    override fun toString(): String {
        return "YcygIncrementalProductSyncRequest(list=$list)"
    }
}