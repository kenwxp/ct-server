package com.cloudtimes.promotion.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "根据userId查询数据请求体")
class DrawWinRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message = "用户编号不能为空")
    @field:NotNull(message = "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "奖项(规则)编号", required = true)
    @field:NotEmpty(message = "奖项编号不能为空")
    @field:NotNull(message = "奖项编号不能为空")
    var ruleId: String = ""
}
