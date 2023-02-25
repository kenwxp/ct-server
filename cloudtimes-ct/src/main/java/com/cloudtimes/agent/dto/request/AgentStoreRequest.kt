package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "AgentStoreRequest", description = "查询代理门店请求体")
class AgentStoreRequest : PageRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "店铺开设状态")
    var buildState: String? = null

    @ApiModelProperty(value = "店铺名称")
    var storeName: String? = null

    @ApiModelProperty(value = "分页参数:开始页")
    override var pageNum: Int = 1

    @ApiModelProperty(value = "分页参数:每页笔数")
    override var pageSize: Int = 10
}
