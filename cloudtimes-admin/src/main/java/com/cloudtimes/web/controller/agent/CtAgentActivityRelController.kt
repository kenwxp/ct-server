package com.cloudtimes.web.controller.agent

import com.cloudtimes.agent.domain.CtAgentActivityRel
import com.cloudtimes.agent.service.ICtAgentActivityRelService
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
 * 代理活动关系Controller
 *
 * @author tank
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/agent/agent_activity_rel")
class CtAgentActivityRelController : BaseController() {
    @Autowired
    private lateinit var ctAgentActivityRelService: ICtAgentActivityRelService

    /**
     * 查询代理活动关系列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity_rel:list')")
    @GetMapping("/list")
    fun list(ctAgentActivityRel: CtAgentActivityRel): TableDataInfo {
        startPage()
        val list = ctAgentActivityRelService.selectCtAgentActivityRelList(ctAgentActivityRel)
        return getDataTable(list)
    }

    /**
     * 导出代理活动关系列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity_rel:export')")
    @Log(title = "代理活动关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivityRel: CtAgentActivityRel) {
        val list = ctAgentActivityRelService.selectCtAgentActivityRelList(ctAgentActivityRel)
        val util = ExcelUtil(
            CtAgentActivityRel::class.java
        )
        util.exportExcel(response, list, "代理活动关系数据")
    }

    /**
     * 获取代理活动关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity_rel:query')")
    @GetMapping(value = ["/{activityId}"])
    fun getInfo(@PathVariable("activityId") activityId: String): AjaxResult {
        return AjaxResult.success(ctAgentActivityRelService.selectCtAgentActivityRelByActivityId(activityId))
    }

    /**
     * 新增代理活动关系
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity_rel:add')")
    @Log(title = "代理活动关系", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivityRel: CtAgentActivityRel): AjaxResult {
        return toAjax(ctAgentActivityRelService.insertCtAgentActivityRel(ctAgentActivityRel))
    }

    /**
     * 修改代理活动关系
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity_rel:edit')")
    @Log(title = "代理活动关系", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivityRel: CtAgentActivityRel): AjaxResult {
        return toAjax(ctAgentActivityRelService.updateCtAgentActivityRel(ctAgentActivityRel))
    }

    /**
     * 删除代理活动关系
     */
    @PreAuthorize("@ss.hasPermi('agent:agent_activity_rel:remove')")
    @Log(title = "代理活动关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    fun remove(@PathVariable activityIds: Array<String>): AjaxResult {
        return toAjax(ctAgentActivityRelService.deleteCtAgentActivityRelByActivityIds(activityIds))
    }
}
