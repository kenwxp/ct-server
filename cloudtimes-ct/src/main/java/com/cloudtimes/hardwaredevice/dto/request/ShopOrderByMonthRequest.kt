package com.cloudtimes.hardwaredevice.dto.request

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Min
import javax.validation.constraints.Max

@ApiModel(value = "ConfirmCommissionRequest", description = "按月查询店铺订单")
class ShopOrderByMonthRequest {
    @ApiModelProperty(value = "年月", required = true)
    @field:NotNull(message =  "年月不能为空")
    @field:Min(value = 202201)
    @field:Max(value = 220001)
    var yearMonth: Int? = null;

    @ApiModelProperty(value = "店铺编号", required = true)
    @field:NotEmpty(message =  "店铺编号不能为空")
    @field:NotNull(message =  "店铺编号不能为空")
    var storeId: String? = null;
}
