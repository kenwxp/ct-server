package com.cloudtimes.stats.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "门店月销售统计请求")
class MonthlySalesRequest {
    @Schema(description = "门店ID", required = true)
    @field:NotEmpty(message =  "门店ID不能为空")
    @field:NotNull(message =  "门店ID不能为空")
    var shopId: String? = null;
}
