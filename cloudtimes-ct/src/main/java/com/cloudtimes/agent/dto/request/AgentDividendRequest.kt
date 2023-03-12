package com.cloudtimes.agent.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "代理店铺分润请求")
open class AgentDividendRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "店铺ID", required = true)
    @field:NotEmpty(message =  "店铺ID不能为空")
    @field:NotNull(message =  "店铺ID不能为空")
    var storeId: String = ""

    @Schema(description = "分润年月", required = true)
    @field:NotEmpty(message =  "分润年月不能为空")
    @field:NotNull(message =  "分润年月不能为空")
    var yearMonth: String = ""
}
