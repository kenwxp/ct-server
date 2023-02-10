package com.cloudtimes.stats.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "MonthlySalesRequest", description = "门店月销售统计请求")
class MonthlySalesRequest {
    @ApiModelProperty(value = "门店ID", required = true)
    @field:NotEmpty(message =  "门店ID不能为空")
    @field:NotNull(message =  "门店ID不能为空")
    var shopId: String? = null;
}
