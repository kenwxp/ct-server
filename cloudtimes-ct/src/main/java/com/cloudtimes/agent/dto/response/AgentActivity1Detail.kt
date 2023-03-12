package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtAgentActivity1Rule
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "代理活动1详情")
class AgentActivity1Detail : CtAgentActivity1Rule() {

    @Schema(description = "是否已达成")
    @get:JvmName("getIsFulfilled")
    @set:JvmName("setIsFulfilled")
    var isFulfilled: String? = null

    @Schema(description = "审核状态")
    var verifyState: String? = null

    @Schema(description = "结算状态")
    var settlementState: String? = null
}
