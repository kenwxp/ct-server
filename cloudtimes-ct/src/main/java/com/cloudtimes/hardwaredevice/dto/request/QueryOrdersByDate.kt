package com.cloudtimes.hardwaredevice.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "QueryOrdersByDate", description = "按日查询店铺订单")
class QueryOrdersByDate : PageRequest {
    @ApiModelProperty(value = "店铺编号", required = true)
    @field:NotEmpty(message =  "店铺编号不能为空")
    @field:NotNull(message =  "店铺编号不能为空")
    var storeId: String = ""

    @ApiModelProperty(value = "日期", required = true)
    @field:NotNull(message =  "日期不能为空")
    var tranDate: LocalDate = LocalDate.now()

    override var pageNum: Int = 1

    override var pageSize: Int = 10
}
