package com.cloudtimes.account.dto.request

import io.swagger.annotations.ApiModel
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

// 微信登陆请求
@ApiModel(value = "WxLoginRequest", description = "微信登陆请求")
class WxLoginRequest {
    @field:NotEmpty(message =  "微信授权码不能为空")
    @field:NotNull(message =  "微信授权码不能为空")
    var code: String? = null

    @field:NotEmpty(message =  "微信授权码不能为空")
    @field:NotNull(message =  "微信状态码不能为空")
    val state: String? = null
}
