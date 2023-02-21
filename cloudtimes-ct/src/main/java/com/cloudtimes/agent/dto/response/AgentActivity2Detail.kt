package com.cloudtimes.agent.dto.response

import com.cloudtimes.agent.domain.CtAgentActivity2Rule
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "AgentActivity2Detail", description = "代理活动2详情")
class AgentActivity2Detail : CtAgentActivity2Rule() {
    @ApiModelProperty(value = "上级地区名称")
    var parentRegionName: String? = null

    @ApiModelProperty(value = "地区名称")
    var regionName: String? = null

    @ApiModelProperty(value = "代理自己完成的门店数")
    var agentStoreCount: Long? = null

    @ApiModelProperty(value = "下级代理完成的门店数")
    var subAgentStoreCount: Long? = null
}