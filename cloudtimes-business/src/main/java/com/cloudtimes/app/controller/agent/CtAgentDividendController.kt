package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtAgentDividend
import com.cloudtimes.account.service.ICtAgentDividendService
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
 * 分润配置Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/v1/agent/agent_dividend")
@Api(tags = ["代理分润"])
class CtAgentDividendController : BaseController() {
    @Autowired
    private lateinit var ctAgentDividendService: ICtAgentDividendService

    /**
     * 查询分润配置列表
     */
    @GetMapping("/list")
    @ApiOperation("查询分润配置列表")
    fun list(ctAgentDividend: CtAgentDividend): TableDataInfo {
        // :TODO: userId从接口中取
        ctAgentDividend.userId = "e4011707-a691-11ed-8957-0242ac110003"
        startPage()
        val list = ctAgentDividendService.selectCtAgentDividendList(ctAgentDividend)
        return getDataTable(list)
    }

    /**
     * 获取分润配置详细信息
     */
    @GetMapping(value = ["/{id}"])
    @ApiOperation("获取分润配置详细信息")
    fun getInfo(@PathVariable("id") id: String?): AjaxResult {
        return AjaxResult.success(ctAgentDividendService.selectCtAgentDividendById(id))
    }

    /**
     * 新增分润配置
     */
    @Log(title = "分润配置", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增分润配置")
    fun add(@RequestBody ctAgentDividend: CtAgentDividend?): AjaxResult {
        return toAjax(ctAgentDividendService.insertCtAgentDividend(ctAgentDividend))
    }

    /**
     * 修改分润配置
     */
    @Log(title = "分润配置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改分润配置")
    fun edit(@RequestBody ctAgentDividend: CtAgentDividend?): AjaxResult {
        return toAjax(ctAgentDividendService.updateCtAgentDividend(ctAgentDividend))
    }
}