package com.cloudtimes.app.controller.agent

import com.cloudtimes.agent.domain.CtAgentDividendSettlement
import com.cloudtimes.agent.dto.request.AgentDividendRequest
import com.cloudtimes.agent.dto.request.StoreDividendRequest
import com.cloudtimes.agent.service.ICtAgentDividendSettlementService
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

// 泛型具体化
class DividendSettlementPage: RestPageResult<CtAgentDividendSettlement>()
class DividendDetail: RestResult<CtAgentDividendSettlement>()

/**
 * 分润结算审核Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/agent/dividend_settlement")
@Api(tags = ["代理-分润结算"])
class CtAgentDividendSettlementController : BaseController() {
    @Autowired
    private lateinit var dividendSettlementService: ICtAgentDividendSettlementService

    @PostMapping("/list_store_dividend")
    @ApiOperation("查询店铺分润列表")
    fun listStoreDividend(@Valid @RequestBody request: StoreDividendRequest): DividendSettlementPage {
        startPage(request.pageNum, request.pageSize)
        val list = dividendSettlementService.selectStoreDividendList(request)
        val page = getDataTable(list)
        return DividendSettlementPage().apply {
            data = list
            total = page.total
        }
    }

    @PostMapping("/detail")
    @ApiOperation("查询店铺分润详情")
    fun detail(@Valid @RequestBody request: AgentDividendRequest): DividendDetail {
        val detail = dividendSettlementService.agentDividendDetail(request)
        return DividendDetail().apply {
            data = detail
        }
    }

    @PostMapping("/agent_approve")
    @ApiOperation("店铺分润代理确认")
    fun agentApprove(@Valid @RequestBody request: AgentDividendRequest): AjaxResult {
        dividendSettlementService.agentApproveDividend(request)
        return AjaxResult.success()
    }
}