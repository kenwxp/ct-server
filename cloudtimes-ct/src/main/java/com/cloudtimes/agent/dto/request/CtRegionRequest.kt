package com.cloudtimes.agent.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "查询地区列表请求体")
class CtRegionRequest {
    @Schema(description = "地区级别")
    var regionLevel: String = ""
}
