package com.cloudtimes.agent.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "查询店铺分润")
open class StoreProfitRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "店铺ID", required = true)
    @field:NotEmpty(message =  "店铺ID不能为空")
    @field:NotNull(message =  "店铺ID不能为空")
    var storeId: String = ""
}
