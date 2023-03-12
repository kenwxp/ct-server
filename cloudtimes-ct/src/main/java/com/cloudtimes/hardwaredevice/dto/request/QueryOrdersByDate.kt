package com.cloudtimes.hardwaredevice.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "按日查询店铺订单")
class QueryOrdersByDate : PageRequest {
    @Schema(description = "店铺编号", required = true)
    @field:NotEmpty(message =  "店铺编号不能为空")
    @field:NotNull(message =  "店铺编号不能为空")
    var storeId: String = ""

    @Schema(description = "日期", required = true)
    @field:NotNull(message =  "日期不能为空")
    var tranDate: LocalDate = LocalDate.now()

    override var pageNum: Int = 1

    override var pageSize: Int = 10
}
