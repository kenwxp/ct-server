package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "StoreDividendRequest", description = "查询店铺分润")
open class AgentVerifyDividendRequest : PageRequest {
    @ApiModelProperty(value = "分页参数:开始页")
    override var pageNum: Int = 1

    @ApiModelProperty(value = "分页参数:每页笔数")
    override var pageSize: Int = 10

    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "店铺ID", required = true)
    @field:NotEmpty(message =  "店铺ID不能为空")
    @field:NotNull(message =  "店铺ID不能为空")
    var storeId: String = ""

    @ApiModelProperty(value = "分润年月", required = true)
    var yearMonth: String? = null

    @ApiModelProperty(value = "审核状态", required = true)
    var verifyState: String? = null
}
