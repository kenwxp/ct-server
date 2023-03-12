package com.cloudtimes.agent.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "邀请")
data class InviteResponse (
    @Schema(description = "邀请码")
    val inviteCode: String,

    @Schema(description = "邀请地址")
    val inviteUrl: String,
)
