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

    @ApiModelProperty(value = "代理是否审核")
    @get:JvmName("getIsAgentOk")
    @set:JvmName("setIsAgentOk")
    var isAgentOk: String? = null

    @get:JvmName("getIsPlatformOk")
    @set:JvmName("setIsPlatformOk")
    var isPlatformOk: String? = null

    @ApiModelProperty(value = "结算状态")
    var settlementState: String? = null
}