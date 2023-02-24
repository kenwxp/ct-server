package com.cloudtimes.account.dto.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "QueryBySubUserIdRequest", description = "根据subUserId查询数据请求体")
open class QueryBySubUserIdRequest {
    @ApiModelProperty(value = "下级用户编号", required = true)
    @field:NotEmpty(message =  "下级用户编号不能为空")
    @field:NotNull(message =  "下级用户编号不能为空")
    var subUserId: String = ""
}
