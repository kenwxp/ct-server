package com.cloudtimes.account.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class QueryAgentWithdrawalRequest: PageRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "提现申请日期")
    var applyDate: LocalDate? = null

    @ApiModelProperty(value = "支付状态")
    var payState: String? = null

    override var pageNum: Int = 1
    override var pageSize: Int = 10

    override fun toString(): String {
        return "QueryAgentWithdrawalRequest(userId='$userId', applyDate=$applyDate, payState=$payState, pageNum=$pageNum, pageSize=$pageSize)"
    }
}