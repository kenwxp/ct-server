package com.cloudtimes.account.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


@ApiModel(value = "VerifyRealNameRequest", description = "实名认证请求体")
class VerifyRealNameRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null;

    @ApiModelProperty(value = "姓名", required = true)
    @field:NotEmpty(message =  "姓名不能为空")
    @field:NotNull(message =  "姓名不能为空")
    var name: String? = null;

    @ApiModelProperty(value = "身份证", required = true)
    @field:NotEmpty(message =  "身份证不能为空")
    @field:NotNull(message =  "身份证不能为空")
    var ssn: String? = null;
}
