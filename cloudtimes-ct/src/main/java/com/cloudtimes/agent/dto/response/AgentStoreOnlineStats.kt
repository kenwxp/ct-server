package com.cloudtimes.agent.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "代理门店上线统计")
data class AgentStoreOnlineStats (
    @Schema(description = "店铺开设状态")
    var buildState: String? = "0",

    @Schema(description = "统计数量")
    var count: Long? = 0L,
)
