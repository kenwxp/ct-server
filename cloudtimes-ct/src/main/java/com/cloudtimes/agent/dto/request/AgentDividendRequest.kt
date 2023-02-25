package com.cloudtimes.agent.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "AgentDividendRequest", description = "代理店铺分润请求")
open class AgentDividendRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "店铺ID", required = true)
    @field:NotEmpty(message =  "店铺ID不能为空")
    @field:NotNull(message =  "店铺ID不能为空")
    var storeId: String = ""

    @ApiModelProperty(value = "分润年月", required = true)
    @field:NotEmpty(message =  "分润年月不能为空")
    @field:NotNull(message =  "分润年月不能为空")
    var yearMonth: String = ""
}
