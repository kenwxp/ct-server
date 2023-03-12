package com.cloudtimes.agent.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "查询代理门店列表请求体")
class AgentStoreListRequest : PageRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "下级用户编号", required = true)
    var subUserId: String? = null

    @Schema(description = "店铺开设状态")
    var buildState: String? = null

    @Schema(description = "店铺名称")
    var storeName: String? = null

    @Schema(description = "分页参数:开始页")
    override var pageNum: Int = 1

    @Schema(description = "分页参数:每页笔数")
    override var pageSize: Int = 10

    override fun toString(): String {
        return "AgentStoreListRequest(userId='$userId', subUserId=$subUserId, buildState=$buildState, storeName=$storeName, pageNum=$pageNum, pageSize=$pageSize)"
    }
}
