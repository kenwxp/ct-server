package com.cloudtimes.account.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "根据类型查询用户消息请求体")
class ListMessageByTypeRequest() : QueryByUserIdRequest(), PageRequest {
    @Schema(description = "消息类型", required = true)
    @field:NotEmpty(message =  "消息类型不能为空")
    @field:NotNull(message =  "消息类型不能为空")
    var msgType: String? = null

    override var pageNum: Int = 1
    override var pageSize: Int = 10
}
