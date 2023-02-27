package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtAgentActivity1Rule
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "AgentActivity1Detail", description = "代理活动1详情")
class AgentActivity1Detail : CtAgentActivity1Rule() {

    @ApiModelProperty(value = "是否已达成")
    @get:JvmName("getIsFulfilled")
    @set:JvmName("setIsFulfilled")
    var isFulfilled: String? = null

    @ApiModelProperty(value = "审核状态")
    var verifyState: String? = null

    @ApiModelProperty(value = "结算状态")
    var settlementState: String? = null
}