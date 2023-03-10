package com.cloudtimes.promotion.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "QueryByUserIdRequest", description = "根据userId查询数据请求体")
class DrawWinRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message = "用户编号不能为空")
    @field:NotNull(message = "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "奖项(规则)编号", required = true)
    @field:NotEmpty(message = "奖项编号不能为空")
    @field:NotNull(message = "奖项编号不能为空")
    var ruleId: String = ""
}