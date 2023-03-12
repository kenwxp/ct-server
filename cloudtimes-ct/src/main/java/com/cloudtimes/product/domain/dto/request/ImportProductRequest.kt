package com.cloudtimes.product.domain.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "导入产品请求")
class ImportProductRequest {
    @Schema(description = "文件名")
    val fileName: String? = null

    @Schema(description = "是否覆盖库存")
    val isOverrideStock = false

    @Schema(description = "需要更新的数据项: [retailPrice, purchasePrice]")
    var updateItems = emptyList<String>()
}