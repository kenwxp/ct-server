package com.cloudtimes.account.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "根据subUserId查询数据请求体")
open class QueryBySubUserIdRequest {
    @Schema(description = "下级用户编号", required = true)
    @field:NotEmpty(message =  "下级用户编号不能为空")
    @field:NotNull(message =  "下级用户编号不能为空")
    var subUserId: String = ""
}
