package com.cloudtimes.agent.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "活动规则请求体")
open class ActivityRuleRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null

    @Schema(description = "活动规则编号", required = true)
    @field:NotEmpty(message =  "活动规则编号不能为空")
    @field:NotNull(message =  "活动规则编号不能为空")
    var activityRuleId: String? = null
}
