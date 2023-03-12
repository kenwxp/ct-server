package com.cloudtimes.thirdpart.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

@Schema(description = "按客户号请求")
class ByCustomerIdRequest {
    @Schema(description = "门店账号(电话)", required = true)
    @field:NotEmpty(message =  "门店账号不能为空")
    @JsonProperty("customerid")
    var customerId: String = ""

    override fun toString(): String {
        return "ByCustomerIdRequest(customerId='$customerId')"
    }
}
