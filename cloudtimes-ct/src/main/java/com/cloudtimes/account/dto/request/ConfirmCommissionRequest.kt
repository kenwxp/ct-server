package com.cloudtimes.account.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "ConfirmCommissionRequest", description = "佣金确认请求体")
class ConfirmCommissionRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "佣金编号", required = true)
    @field:NotEmpty(message =  "佣金编号不能为空")
    @field:NotNull(message =  "佣金编号不能为空")
    var commissionId: String? = null
}
