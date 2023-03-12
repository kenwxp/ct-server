package com.cloudtimes.web.controller.agent

import com.cloudtimes.agent.domain.CtAgentActivity2Rule
import com.cloudtimes.agent.service.ICtAgentActivity2RuleService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 代理活动2规则Controller
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/agent/activity2_rule")
@Tag(name = "代理活动2规则")
class CtAgentActivity2RuleController : BaseController() {
    @Autowired
    private lateinit var ctAgentActivity2RuleService: ICtAgentActivity2RuleService

    @Operation(summary = "查询代理活动2规则列表")
    @PreAuthorize("@ss.hasPermi('agent:activity2_rule:list')")
    @GetMapping("/list")
    fun list(ctAgentActivity2Rule: CtAgentActivity2Rule): TableDataInfo {
        startPage()
        val list = ctAgentActivity2RuleService.selectCtAgentActivity2RuleListPlus(ctAgentActivity2Rule)
        return getDataTable(list)
    }

    @Operation(summary = "导出代理活动2规则列表")
    @PreAuthorize("@ss.hasPermi('agent:activity2_rule:export')")
    @Log(title = "代理活动2规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivity2Rule: CtAgentActivity2Rule) {
        val list = ctAgentActivity2RuleService.selectCtAgentActivity2RuleList(ctAgentActivity2Rule)
        val util = ExcelUtil(
            CtAgentActivity2Rule::class.java
        )
        util.exportExcel(response, list, "代理活动2规则数据")
    }

    @Operation(summary = "获取代理活动2规则详细信息")
    @PreAuthorize("@ss.hasPermi('agent:activity2_rule:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentActivity2RuleService.selectCtAgentActivity2RuleById(id))
    }

    @Operation(summary = "新增代理活动2规则")
    @PreAuthorize("@ss.hasPermi('agent:activity2_rule:add')")
    @Log(title = "代理活动2规则", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivity2Rule: CtAgentActivity2Rule): AjaxResult {
        return toAjax(ctAgentActivity2RuleService.insertCtAgentActivity2Rule(ctAgentActivity2Rule))
    }

    @Operation(summary = "修改代理活动2规则")
    @PreAuthorize("@ss.hasPermi('agent:activity2_rule:edit')")
    @Log(title = "代理活动2规则", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivity2Rule: CtAgentActivity2Rule): AjaxResult {
        return toAjax(ctAgentActivity2RuleService.updateCtAgentActivity2Rule(ctAgentActivity2Rule))
    }

    @Operation(summary = "删除代理活动2规则")
    @PreAuthorize("@ss.hasPermi('agent:activity2_rule:remove')")
    @Log(title = "代理活动2规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctAgentActivity2RuleService.deleteCtAgentActivity2RuleByIds(ids))
    }
}
