package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtAgentCommission
import com.cloudtimes.account.service.ICtAgentCommissionService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 代理销售佣金设置Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/agent/agent_commission")
@Api(tags = ["代理-佣金"])
class CtAgentCommissionController : BaseController() {
    @Autowired
    private lateinit var ctAgentCommissionService: ICtAgentCommissionService

    /**
     * 查询代理销售佣金设置列表
     */
    @GetMapping("/list")
    @ApiOperation("查询代理销售佣金设置列表")
    fun list(ctAgentCommission: CtAgentCommission): TableDataInfo {
        startPage()
        val list = ctAgentCommissionService.selectCtAgentCommissionList(ctAgentCommission)
        return getDataTable(list)
    }

    /**
     * 获取代理销售佣金设置详细信息
     */
    @GetMapping(value = ["/{id}"])
    @ApiOperation("获取代理销售佣金设置")
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentCommissionService.selectCtAgentCommissionById(id))
    }

    /**
     * 新增代理销售佣金设置
     */
    @Log(title = "代理销售佣金设置", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增代理销售佣金")
    fun add(@RequestBody ctAgentCommission: CtAgentCommission): AjaxResult {
        val user = loginUser
        ctAgentCommission.operator = user.username
        return toAjax(ctAgentCommissionService.insertCtAgentCommission(ctAgentCommission))
    }

    /**
     * 修改代理销售佣金设置
     */
    @Log(title = "代理销售佣金设置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改代理销售佣金")
    fun edit(@RequestBody ctAgentCommission: CtAgentCommission): AjaxResult {
        val user = loginUser
        ctAgentCommission.operator = user.username
        return toAjax(ctAgentCommissionService.updateCtAgentCommission(ctAgentCommission))
    }
}
