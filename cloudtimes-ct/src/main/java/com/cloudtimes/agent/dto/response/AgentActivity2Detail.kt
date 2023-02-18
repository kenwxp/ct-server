package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtAgentActivity2Rule
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "AgentActivity2Detail", description = "代理活动2详情")
class AgentActivity2Detail : CtAgentActivity2Rule() {
    @ApiModelProperty(value = "代理完成的门店数")
    var agentStoreCount: Long? = null
}