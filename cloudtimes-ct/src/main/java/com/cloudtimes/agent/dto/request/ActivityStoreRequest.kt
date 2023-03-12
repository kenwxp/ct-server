package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "查询活动店铺请求")
open class ActivityStoreRequest : PageRequest {
    @Schema(description = "分页参数:开始页")
    override var pageNum: Int = 1

    @Schema(description = "分页参数:每页笔数")
    override var pageSize: Int = 10

    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null

    @Schema(description = "活动类型", required = true)
    @field:NotEmpty(message =  "活动类型不能为空")
    @field:NotNull(message =  "活动类型不能为空")
    var activityType: String? = null

    @Schema(description = "活动规则编号", required = true)
    @field:NotEmpty(message =  "活动规则编号不能为空")
    @field:NotNull(message =  "活动规则编号不能为空")
    var activityRuleId: String? = null
}
