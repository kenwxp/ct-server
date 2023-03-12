package com.cloudtimes.agent.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "根据活动编号查询活动详情请求体")
open class ActivityDetailRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null

    @Schema(description = "活动编号", required = true)
    @field:NotEmpty(message =  "活动编号不能为空")
    @field:NotNull(message =  "活动编号不能为空")
    var activityId: String? = null
}
