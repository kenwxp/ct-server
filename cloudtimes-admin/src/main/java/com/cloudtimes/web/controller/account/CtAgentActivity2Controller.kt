package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtAgentActivity2
import com.cloudtimes.account.service.ICtAgentActivity2Service
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
 * 代理活动2Controller
 *
 * @author tank
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/account/agent_activity2")
class CtAgentActivity2Controller : BaseController() {
    @Autowired
    private lateinit var ctAgentActivity2Service: ICtAgentActivity2Service

    /**
     * 查询代理活动2列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity2:list')")
    @GetMapping("/list")
    fun list(ctAgentActivity2: CtAgentActivity2): TableDataInfo {
        startPage()
        val list: List<CtAgentActivity2> = ctAgentActivity2Service.selectCtAgentActivity2List(
            ctAgentActivity2
        )
        return getDataTable(list)
    }

    /**
     * 导出代理活动2列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity2:export')")
    @Log(title = "代理活动2", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivity2: CtAgentActivity2) {
        val list = ctAgentActivity2Service.selectCtAgentActivity2List(ctAgentActivity2)
        val util = ExcelUtil(
            CtAgentActivity2::class.java
        )
        util.exportExcel(response, list, "代理活动2数据")
    }

    /**
     * 获取代理活动2详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity2:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentActivity2Service.selectCtAgentActivity2ById(id))
    }

    /**
     * 新增代理活动2
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity2:add')")
    @Log(title = "代理活动2", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivity2: CtAgentActivity2): AjaxResult {
        return toAjax(ctAgentActivity2Service.insertCtAgentActivity2(ctAgentActivity2))
    }

    /**
     * 修改代理活动2
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity2:edit')")
    @Log(title = "代理活动2", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivity2: CtAgentActivity2): AjaxResult {
        return toAjax(ctAgentActivity2Service.updateCtAgentActivity2(ctAgentActivity2))
    }

    /**
     * 删除代理活动2
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity2:remove')")
    @Log(title = "代理活动2", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctAgentActivity2Service.deleteCtAgentActivity2ByIds(ids))
    }
}