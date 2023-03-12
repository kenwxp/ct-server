package com.cloudtimes.account.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "H5登陆请求")
class H5LoginRequest {
    @Schema(description = "手机号", required = true)
    @field:NotEmpty(message =  "手机号不能为空")
    @field:NotNull(message =  "手机号不能为空")
    var mobile: String? = null
}
