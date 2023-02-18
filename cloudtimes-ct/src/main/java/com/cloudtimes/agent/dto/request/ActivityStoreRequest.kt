package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "ActivityStoreRequest", description = "查询活动店铺请求")
open class ActivityStoreRequest : PageRequest {
    @ApiModelProperty(value = "分页参数:开始页")
    override var pageNum: Int = 1

    @ApiModelProperty(value = "分页参数:每页笔数")
    override var pageSize: Int = 10

    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null

    @ApiModelProperty(value = "活动编号", required = true)
    @field:NotEmpty(message =  "活动编号不能为空")
    @field:NotNull(message =  "活动编号不能为空")
    var activityId: String? = null
}
