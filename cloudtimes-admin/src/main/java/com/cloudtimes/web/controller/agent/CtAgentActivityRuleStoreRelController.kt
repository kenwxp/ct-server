package com.cloudtimes.web.controller.agent

import com.cloudtimes.agent.domain.CtAgentActivityRuleStoreRel
import com.cloudtimes.agent.service.ICtAgentActivityRuleStoreRelService
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
 * 代理活动规则与店铺关系Controller
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/agent/activity_store_rel")
class CtAgentActivityRuleStoreRelController : BaseController() {
    @Autowired
    private lateinit var ctAgentActivityRuleStoreRelService: ICtAgentActivityRuleStoreRelService

    /**
     * 查询代理活动规则与店铺关系列表
     */
    @PreAuthorize("@ss.hasPermi('agent:activity_store_rel:list')")
    @GetMapping("/list")
    fun list(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): TableDataInfo {
        startPage()
        val list =
            ctAgentActivityRuleStoreRelService.selectCtAgentActivityRuleStoreRelList(ctAgentActivityRuleStoreRel)
        return getDataTable(list)
    }

    /**
     * 导出代理活动规则与店铺关系列表
     */
    @PreAuthorize("@ss.hasPermi('agent:activity_store_rel:export')")
    @Log(title = "代理活动规则与店铺关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel) {
        val list =
            ctAgentActivityRuleStoreRelService.selectCtAgentActivityRuleStoreRelList(ctAgentActivityRuleStoreRel)
        val util = ExcelUtil(
            CtAgentActivityRuleStoreRel::class.java
        )
        util.exportExcel(response, list, "代理活动规则与店铺关系数据")
    }

    /**
     * 获取代理活动规则与店铺关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:activity_store_rel:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctAgentActivityRuleStoreRelService.selectCtAgentActivityRuleStoreRelById(id))
    }

    /**
     * 新增代理活动规则与店铺关系
     */
    @PreAuthorize("@ss.hasPermi('agent:activity_store_rel:add')")
    @Log(title = "代理活动规则与店铺关系", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): AjaxResult {
        return toAjax(ctAgentActivityRuleStoreRelService.insertCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel))
    }

    /**
     * 修改代理活动规则与店铺关系
     */
    @PreAuthorize("@ss.hasPermi('agent:activity_store_rel:edit')")
    @Log(title = "代理活动规则与店铺关系", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): AjaxResult {
        return toAjax(ctAgentActivityRuleStoreRelService.updateCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel))
    }

    /**
     * 删除代理活动规则与店铺关系
     */
    @PreAuthorize("@ss.hasPermi('agent:activity_store_rel:remove')")
    @Log(title = "代理活动规则与店铺关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctAgentActivityRuleStoreRelService.deleteCtAgentActivityRuleStoreRelByIds(ids))
    }
}
