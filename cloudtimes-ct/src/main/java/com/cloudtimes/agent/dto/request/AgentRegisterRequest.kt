package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.enums.AgentType
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "代理注册请求")
class AgentRegisterRequest {
    @Schema(description = "微信UNION_ID", required = true)
    @field:NotEmpty(message =  "微信UNION_ID不能为空")
    @field:NotNull(message =  "微信UNION_ID不能为空")
    var wxUnionId: String? = null

    @Schema(description = "手机号", required = true)
    @field:NotEmpty(message =  "手机号不能为空")
    @field:NotNull(message =  "手机号不能为空")
    var mobile: String? = null

    @Schema(description = "用户名", required = true)
    @field:NotEmpty(message =  "用户名不能为空")
    @field:NotNull(message =  "用户名不能为空")
    var userName: String? = null

    @Schema(description = "邀请码-代理拓展时，下级代理注册使用", required = false)
    var inviteCode: String? = null

    @Schema(description = "手机验证码UUID", required = true)
    @field:NotNull(message =  "手机验证码UUID不能为空")
    @field:NotEmpty(message =  "手机验证码UUID不能为空")
    var verifyUUID: String? = null

    @Schema(description = "手机验证码", required = true)
    @field:NotNull(message =  "手机验证码不能为空")
    @field:NotEmpty(message =  "手机验证码不能为空")
    var verifyCode: String? = null

    @JsonIgnore
    var agentType = AgentType.None.code
}
