package com.cloudtimes.agent.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "AgentStoreOnlineStats", description = "代理门店上线统计")
data class AgentStoreOnlineStats (
    @ApiModelProperty(value = "店铺开设状态")
    var buildState: String? = "0",

    @ApiModelProperty(value = "统计数量")
    var count: Long? = 0L,
)
