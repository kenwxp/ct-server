package com.cloudtimes.web.controller.agent

import com.cloudtimes.agent.domain.*
import com.cloudtimes.agent.service.*
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
 * 代理活动Controller
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/agent/agent_activity")
class CtAgentActivityController : BaseController() {
    @Autowired
    private lateinit var ctAgentActivityService: ICtAgentActivityService

    /**
     * 查询代理活动列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity:list')")
    @GetMapping("/list")
    fun list(ctAgentActivity: CtAgentActivity): TableDataInfo {
        startPage()
        val list = ctAgentActivityService.selectCtAgentActivityList(ctAgentActivity)
        return getDataTable(list)
    }

    /**
     * 导出代理活动列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity:export')")
    @Log(title = "代理活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivity: CtAgentActivity) {
        val list = ctAgentActivityService.selectCtAgentActivityList(ctAgentActivity)
        val util = ExcelUtil(
            CtAgentActivity::class.java
        )
        util.exportExcel(response, list, "代理活动数据")
    }

    /**
     * 获取代理活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentActivityService.selectActivityById(id))
    }

    /**
     * 新增代理活动
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity:add')")
    @Log(title = "代理活动", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivity: CtAgentActivity): AjaxResult {
        return toAjax(ctAgentActivityService.insertCtAgentActivity(ctAgentActivity))
    }

    /**
     * 修改代理活动
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity:edit')")
    @Log(title = "代理活动", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivity: CtAgentActivity): AjaxResult {
        return toAjax(ctAgentActivityService.updateCtAgentActivity(ctAgentActivity))
    }

    /**
     * 删除代理活动
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity:remove')")
    @Log(title = "代理活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctAgentActivityService.deleteCtAgentActivityByIds(ids))
    }
}
