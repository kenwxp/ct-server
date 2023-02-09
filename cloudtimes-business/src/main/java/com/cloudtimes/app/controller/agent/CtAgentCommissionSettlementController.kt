package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtAgentCommissionSettlement
import com.cloudtimes.account.dto.request.ConfirmCommissionRequest
import com.cloudtimes.account.service.ICtAgentCommissionSettlementService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
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
@RequestMapping("/v1/agent/commission_settlement")
@Api(tags = ["代理佣金结算"])
class CtAgentCommissionSettlementController : BaseController() {
    @Autowired
    private lateinit var ctAgentCommissionSettlementService: ICtAgentCommissionSettlementService

    /**
     * 查询销售佣金结算列表
     */
    @GetMapping("/list")
    @ApiOperation("查询销售佣金结算列表")
    fun list(ctAgentCommissionSettlement: CtAgentCommissionSettlement?): TableDataInfo {
        startPage()
        val list: List<CtAgentCommissionSettlement?> =
            ctAgentCommissionSettlementService.selectCtAgentCommissionSettlementList(
                ctAgentCommissionSettlement!!
            )
        return getDataTable(list)
    }

    /**
     * 获取销售佣金结算详细信息
     */
    @GetMapping(value = ["/{id}"])
    @ApiOperation("获取销售佣金结算详细信息")
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentCommissionSettlementService.selectCtAgentCommissionSettlementById(id))
    }

    /** 代理佣金结算确认  */
    @PostMapping(value = ["/confirm"])
    @ApiOperation("代理佣金结算确认")
    fun confirm(@Valid @RequestBody confirmCommissionRequest: ConfirmCommissionRequest): AjaxResult {
        val id = confirmCommissionRequest.commissionId!!
        return AjaxResult.success(ctAgentCommissionSettlementService.agentConfirmCtAgentCommissionSettlementById(id))
    }

    /**
     * 新增销售佣金结算
     */
    @Log(title = "销售佣金结算", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增销售佣金结算")
    fun add(@RequestBody ctAgentCommissionSettlement: CtAgentCommissionSettlement): AjaxResult {
        return toAjax(ctAgentCommissionSettlementService.insertCtAgentCommissionSettlement(ctAgentCommissionSettlement))
    }

    /**
     * 修改销售佣金结算
     */
    @Log(title = "销售佣金结算", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改销售佣金结算")
    fun edit(@RequestBody ctAgentCommissionSettlement: CtAgentCommissionSettlement): AjaxResult {
        return toAjax(ctAgentCommissionSettlementService.updateCtAgentCommissionSettlement(ctAgentCommissionSettlement))
    }
}