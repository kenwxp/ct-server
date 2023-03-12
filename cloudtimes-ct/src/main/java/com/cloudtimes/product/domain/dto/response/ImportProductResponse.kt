package com.cloudtimes.product.domain.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "产品导入应答")
data class ImportProductResponse(
    @Schema(description = "文件名")
    var fileName: String = "",
)