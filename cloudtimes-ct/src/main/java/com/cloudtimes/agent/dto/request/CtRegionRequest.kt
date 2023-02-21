package com.cloudtimes.agent.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "CtRegionRequest", description = "查询地区列表请求体")
class CtRegionRequest {
    @ApiModelProperty(value = "地区级别")
    var regionLevel: String = ""
}
