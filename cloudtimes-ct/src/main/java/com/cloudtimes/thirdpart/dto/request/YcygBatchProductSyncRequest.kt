package com.cloudtimes.thirdpart.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

@Schema(description = "批量商品同步请求")
data class YcygBatchProductSyncRequest(
    val pageNo: Int,
    val size: Int,
)
