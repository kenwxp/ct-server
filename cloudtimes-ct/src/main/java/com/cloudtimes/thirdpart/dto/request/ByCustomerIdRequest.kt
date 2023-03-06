package com.cloudtimes.thirdpart.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty

@ApiModel(value = "ByCustomerIdRequest", description = "按客户号请求")
class ByCustomerIdRequest {
    @ApiModelProperty(value = "门店账号(电话)", required = true)
    @field:NotEmpty(message =  "门店账号不能为空")
    @JsonProperty("customerid")
    var customerId: String = ""

    override fun toString(): String {
        return "ByCustomerIdRequest(customerId='$customerId')"
    }
}