package com.cloudtimes.agent.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "InviteResponse", description = "邀请")
data class InviteResponse (
    @ApiModelProperty(value = "邀请码")
    val inviteCode: String,

    @ApiModelProperty(value = "邀请地址")
    val inviteUrl: String,
)