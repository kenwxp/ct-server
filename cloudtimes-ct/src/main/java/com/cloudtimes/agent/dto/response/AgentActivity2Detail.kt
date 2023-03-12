package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtAgentActivity2Rule
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "代理活动2详情")
class AgentActivity2Detail : CtAgentActivity2Rule() {
    @Schema(description = "上级地区名称")
    var parentRegionName: String? = null

    @Schema(description = "地区名称")
    var regionName: String? = null

    @Schema(description = "代理自己完成的门店数")
    var agentStoreCount: Long? = null

    @Schema(description = "下级代理完成的门店数")
    var subAgentStoreCount: Long? = null
}
