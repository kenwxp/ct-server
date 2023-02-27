package com.cloudtimes.hardwaredevice.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "QueryOrdersByMonth", description = "按月查询店铺订单")
class QueryOrdersByMonth {
    @ApiModelProperty(value = "年月", required = true)
    @field:NotEmpty(message =  "年月不能为空")
    @field:NotNull(message =  "年月不能为空")
    var yearMonth: String = ""

    @ApiModelProperty(value = "店铺编号", required = true)
    @field:NotEmpty(message =  "店铺编号不能为空")
    @field:NotNull(message =  "店铺编号不能为空")
    var storeId: String = ""
}
