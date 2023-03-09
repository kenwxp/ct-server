package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.enums.AgentType
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "AgentRegisterRequest", description = "代理注册请求")
class AgentRegisterRequest {
    @ApiModelProperty(value = "微信UNION_ID", required = true)
    @field:NotEmpty(message =  "微信UNION_ID不能为空")
    @field:NotNull(message =  "微信UNION_ID不能为空")
    var wxUnionId: String? = null

    @ApiModelProperty(value = "手机号", required = true)
    @field:NotEmpty(message =  "手机号不能为空")
    @field:NotNull(message =  "手机号不能为空")
    var mobile: String? = null

    @ApiModelProperty(value = "邀请码-代理拓展时，下级代理注册使用", required = false)
    var inviteCode: String? = null

    @ApiModelProperty(value = "手机验证码UUID", required = true)
    @field:NotNull(message =  "手机验证码UUID不能为空")
    @field:NotEmpty(message =  "手机验证码UUID不能为空")
    var verifyUUID: String? = null

    @ApiModelProperty(value = "手机验证码", required = true)
    @field:NotNull(message =  "手机验证码不能为空")
    @field:NotEmpty(message =  "手机验证码不能为空")
    var verifyCode: String? = null

    @JsonIgnore
    var agentType = AgentType.None.code
}