package com.cloudtimes.web.controller.agent

import com.cloudtimes.agent.domain.CtAgentActivity1Rule
import com.cloudtimes.agent.service.ICtAgentActivity1RuleService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 代理活动1规则Controller
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/agent/activity1_rule")
class CtAgentActivity1RuleController : BaseController() {
    @Autowired
    private lateinit var ctAgentActivity1RuleService: ICtAgentActivity1RuleService


    /**
     * 查询代理活动1规则列表
     */
    @PreAuthorize("@ss.hasPermi('agent:activity1_rule:list')")
    @GetMapping("/list")
    fun list(ctAgentActivity1Rule: CtAgentActivity1Rule): TableDataInfo {
        startPage()
        val list = ctAgentActivity1RuleService.selectCtAgentActivity1RuleList(ctAgentActivity1Rule)
        return getDataTable(list)
    }

    /**
     * 导出代理活动1规则列表
     */
    @PreAuthorize("@ss.hasPermi('agent:activity1_rule:export')")
    @Log(title = "代理活动1规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivity1Rule: CtAgentActivity1Rule) {
        val list = ctAgentActivity1RuleService.selectCtAgentActivity1RuleList(ctAgentActivity1Rule)
        val util = ExcelUtil(
            CtAgentActivity1Rule::class.java
        )
        util.exportExcel(response, list, "代理活动1规则数据")
    }

    /**
     * 获取代理活动1规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:activity1_rule:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentActivity1RuleService.selectCtAgentActivity1RuleById(id))
    }

    /**
     * 新增代理活动1规则
     */
    @PreAuthorize("@ss.hasPermi('agent:activity1_rule:add')")
    @Log(title = "代理活动1规则", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivity1Rule: CtAgentActivity1Rule): AjaxResult {
        return toAjax(ctAgentActivity1RuleService.insertCtAgentActivity1Rule(ctAgentActivity1Rule))
    }

    /**
     * 修改代理活动1规则
     */
    @PreAuthorize("@ss.hasPermi('agent:activity1_rule:edit')")
    @Log(title = "代理活动1规则", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivity1Rule: CtAgentActivity1Rule): AjaxResult {
        return toAjax(ctAgentActivity1RuleService.updateCtAgentActivity1Rule(ctAgentActivity1Rule))
    }

    /**
     * 删除代理活动1规则
     */
    @PreAuthorize("@ss.hasPermi('agent:activity1_rule:remove')")
    @Log(title = "代理活动1规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctAgentActivity1RuleService.deleteCtAgentActivity1RuleByIds(ids))
    }
}
