package com.cloudtimes.hardwaredevice.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "按月查询店铺订单")
class QueryOrdersByMonth {
    @Schema(description = "年月", required = true)
    @field:NotEmpty(message =  "年月不能为空")
    @field:NotNull(message =  "年月不能为空")
    var yearMonth: String = ""

    @Schema(description = "店铺编号", required = true)
    @field:NotEmpty(message =  "店铺编号不能为空")
    @field:NotNull(message =  "店铺编号不能为空")
    var storeId: String = ""
}
