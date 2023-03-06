package com.cloudtimes.thirdpart.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty

@ApiModel(value = "YcygBatchProductSyncRequest", description = "批量商品同步请求")
data class YcygBatchProductSyncRequest(
    val pageNo: Int,
    val size: Int,
)