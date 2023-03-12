package com.cloudtimes.account.dto.request

import com.cloudtimes.agent.domain.CtAgentCommission
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "修改下级代理佣金配置请求体")
open class UpdateSubUserCommissionRequest {
    @Schema(description = "下级用户编号", required = true)
    @field:NotEmpty(message =  "下级用户编号不能为空")
    @field:NotNull(message =  "下级用户编号不能为空")
    var subUserId: String? = null;

    @Schema(description = "佣金详情", required = true)
    @field:NotNull(message =  "佣金详情不能为空")
    var detail: CtAgentCommission? = null
}
