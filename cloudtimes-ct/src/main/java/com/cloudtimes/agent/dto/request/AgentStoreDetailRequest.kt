package com.cloudtimes.agent.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "AgentStoreDetailRequest", description = "查询代理门店列表请求体")
class AgentStoreDetailRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "门店ID", required = true)
    @field:NotEmpty(message =  "门店ID不能为空")
    @field:NotNull(message =  "门店ID不能为空")
    var storeId: String = ""
}
