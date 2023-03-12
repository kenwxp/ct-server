package com.cloudtimes.thirdpart.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

@Schema(description = "增量商品同步请求")
class YcygIncrementalProductSyncRequest {
    @Schema(description = "商品列表")
    @field:NotEmpty(message = "商品列表不能为空")
    var list: List<RcygProductRecord> = emptyList()

    override fun toString(): String {
        return "YcygIncrementalProductSyncRequest(list=$list)"
    }
}
