package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtAgentCommission
import com.cloudtimes.account.dto.request.QueryBySubUserIdRequest
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.dto.request.UpdateSubUserCommissionRequest
import com.cloudtimes.account.service.ICtAgentCommissionService
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


class AgentCommissionDetail() : RestResult<CtAgentCommission>()


/**
 * 代理销售佣金设置Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/agent/agent_commission")
@Api(tags = ["代理-佣金配置"])
class CtAgentCommissionController : BaseController() {
    @Autowired
    private lateinit var commissionService: ICtAgentCommissionService

    /**
     * 获取代理销售佣金设置详细信息
     */
    @PostMapping(value = ["detail"])
    @ApiOperation("获取代理销售佣金设置")
    fun detail(@Valid @RequestBody request: QueryByUserIdRequest): AgentCommissionDetail {
        val commission = commissionService.selectCtAgentCommissionByUserId(request.userId!!)
        return AgentCommissionDetail().apply { data = commission }
    }

    @PostMapping(value = ["sub_detail"])
    @ApiOperation("获取下级代理销售佣金设置")
    fun subDetail(@Valid @RequestBody request: QueryBySubUserIdRequest): AgentCommissionDetail {
        val commission = commissionService.selectCtAgentCommissionByUserId(request.subUserId!!)
        return AgentCommissionDetail().apply { data = commission }
    }

    @PostMapping(value = ["update_sub_detail"])
    @ApiOperation("修改下级代理销售佣金设置")
    fun updateSubDetail(@Valid @RequestBody request: UpdateSubUserCommissionRequest): AjaxResult {
        commissionService.updateSubUserCommission(request)
        return AjaxResult.success()
    }
}
