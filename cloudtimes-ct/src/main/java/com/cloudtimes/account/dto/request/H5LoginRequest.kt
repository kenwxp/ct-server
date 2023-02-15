package com.cloudtimes.account.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "H5LoginRequest", description = "H5登陆请求")
class H5LoginRequest {
    @ApiModelProperty(value = "手机号", required = true)
    @field:NotEmpty(message =  "手机号不能为空")
    @field:NotNull(message =  "手机号不能为空")
    var mobile: String? = null
}