package com.cloudtimes.agent.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "查询代理门店列表请求体")
class AgentStoreDetailRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "门店ID", required = true)
    @field:NotEmpty(message =  "门店ID不能为空")
    @field:NotNull(message =  "门店ID不能为空")
    var storeId: String = ""
}
