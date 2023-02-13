package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtAgentActivity1
import com.cloudtimes.account.service.ICtAgentActivity1Service
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
 * 代理活动1Controller
 *
 * @author 沈兵
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/account/agent_activity1")
class CtAgentActivity1Controller : BaseController() {
    @Autowired
    private lateinit var ctAgentActivity1Service: ICtAgentActivity1Service

    /**
     * 查询代理活动1列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity1:list')")
    @GetMapping("/list")
    fun list(ctAgentActivity1: CtAgentActivity1): TableDataInfo {
        startPage()
        val list: List<CtAgentActivity1> = ctAgentActivity1Service.selectCtAgentActivity1List(
            ctAgentActivity1
        )
        return getDataTable(list)
    }

    /**
     * 导出代理活动1列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity1:export')")
    @Log(title = "代理活动1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivity1: CtAgentActivity1) {
        val list = ctAgentActivity1Service.selectCtAgentActivity1List(ctAgentActivity1)
        val util = ExcelUtil(
            CtAgentActivity1::class.java
        )
        util.exportExcel(response, list, "代理活动1数据")
    }

    /**
     * 获取代理活动1详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity1:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentActivity1Service.selectCtAgentActivity1ById(id))
    }

    /**
     * 新增代理活动1
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity1:add')")
    @Log(title = "代理活动1", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivity1: CtAgentActivity1): AjaxResult {
        return toAjax(ctAgentActivity1Service.insertCtAgentActivity1(ctAgentActivity1))
    }

    /**
     * 修改代理活动1
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity1:edit')")
    @Log(title = "代理活动1", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivity1: CtAgentActivity1): AjaxResult {
        return toAjax(ctAgentActivity1Service.updateCtAgentActivity1(ctAgentActivity1))
    }

    /**
     * 删除代理活动1
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity1:remove')")
    @Log(title = "代理活动1", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctAgentActivity1Service.deleteCtAgentActivity1ByIds(ids))
    }
}