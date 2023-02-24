package com.cloudtimes.hardwaredevice.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "ConfirmCommissionRequest", description = "按月查询店铺订单")
class ShopOrderByMonthRequest : PageRequest {
    @ApiModelProperty(value = "年月", required = true)
    var yearMonth: String? = null

    @ApiModelProperty(value = "店铺编号", required = true)
    @field:NotEmpty(message =  "店铺编号不能为空")
    @field:NotNull(message =  "店铺编号不能为空")
    var storeId: String = ""

    override var pageNum: Int = 1

    override var pageSize: Int = 10
}
