package com.cloudtimes.app.controller.mp

import com.cloudtimes.account.dto.request.ConfirmCommissionRequest
import com.cloudtimes.agent.service.ICtAgentCommissionSettlementService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * 销售佣金结算Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/commission_settlement")
@Api(tags = ["代理-佣金结算"])
class CtAgentCommissionSettlementController : BaseController() {
    @Autowired
    private lateinit var commissionSettlementService: ICtAgentCommissionSettlementService

    /** 代理佣金结算确认  */
    @PostMapping(value = ["/confirm"])
    @ApiOperation("代理佣金结算确认")
    fun confirm(@Valid @RequestBody confirmCommissionRequest: ConfirmCommissionRequest): AjaxResult {
        val id = confirmCommissionRequest.commissionId!!
        return AjaxResult.success(commissionSettlementService.agentConfirmCtAgentCommissionSettlement(id))
    }
}
