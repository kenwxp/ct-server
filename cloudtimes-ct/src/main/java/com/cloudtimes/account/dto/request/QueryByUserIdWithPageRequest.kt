package com.cloudtimes.account.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "QueryByUserIdRequest", description = "根据userId查询数据请求体")
open class QueryByUserIdWithPageRequest : PageRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String? = null;


    override var pageNum: Int = 1
    override var pageSize: Int = 10
}