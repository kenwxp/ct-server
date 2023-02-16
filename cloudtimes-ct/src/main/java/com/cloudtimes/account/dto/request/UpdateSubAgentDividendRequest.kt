package com.cloudtimes.account.dto.request

import com.cloudtimes.account.domain.CtAgentDividend
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = "UpdateSubAgentDividendRequest", description = "更新下级代理分润配置")
class UpdateSubAgentDividendRequest {
    @ApiModelProperty(value = "上级代理用户编号", required = true)
    @field:NotEmpty(message =  "上级代理用户编号不能为空")
    @field:NotNull(message =  "上级代理用户编号不能为空")
    var userId: String? = null

    @ApiModelProperty(value = "下级代理用户编号", required = true)
    @field:NotEmpty(message =  "下级代理用户编号不能为空")
    @field:NotNull(message =  "下级代理用户编号不能为空")
    var subUserId: String? = null

    @ApiModelProperty(value = "下级分润配置列表", required = true)
    @field:NotNull(message =  "下级分润配置列表不能为空")
    @field:Size(min = 1, message =  "下级分润配置列表不能为空")
    var dividendList: List<CtAgentDividend>? = emptyList()
}
