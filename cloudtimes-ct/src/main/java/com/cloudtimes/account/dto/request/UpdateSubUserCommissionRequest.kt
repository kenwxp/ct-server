package com.cloudtimes.account.dto.request

import com.cloudtimes.account.domain.CtAgentCommission
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "UpdateSubUserCommissionRequest", description = "修改下级代理佣金配置请求体")
open class UpdateSubUserCommissionRequest {
    @ApiModelProperty(value = "下级用户编号", required = true)
    @field:NotEmpty(message =  "下级用户编号不能为空")
    @field:NotNull(message =  "下级用户编号不能为空")
    var subUserId: String? = null;

    @ApiModelProperty(value = "佣金详情", required = true)
    @field:NotNull(message =  "佣金详情不能为空")
    var detail: CtAgentCommission? = null
}
