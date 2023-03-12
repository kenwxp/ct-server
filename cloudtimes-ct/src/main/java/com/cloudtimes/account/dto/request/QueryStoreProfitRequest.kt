package com.cloudtimes.account.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "查询代理门店收益请求体")
open class QueryStoreProfitRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "店铺ID", required = true)
    @field:NotEmpty(message =  "店铺ID不能为空")
    @field:NotNull(message =  "店铺ID不能为空")
    var storeId: String = ""
}
