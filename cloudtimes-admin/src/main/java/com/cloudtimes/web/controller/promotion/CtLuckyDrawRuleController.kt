package com.cloudtimes.web.controller.promotion

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.service.ICtLuckyDrawRuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 幸运大抽奖规则Controller
 *
 * @author tank
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/promotion/draw_rule")
class CtLuckyDrawRuleController : BaseController() {
    @Autowired
    private lateinit var ctLuckyDrawRuleService: ICtLuckyDrawRuleService

    /**
     * 查询幸运大抽奖规则列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:list')")
    @GetMapping("/list")
    fun list(ctLuckyDrawRule: CtLuckyDrawRule): TableDataInfo {
        startPage()
        val list: List<CtLuckyDrawRule> = ctLuckyDrawRuleService.selectCtLuckyDrawRuleList(
            ctLuckyDrawRule
        )
        return getDataTable(list)
    }

    /**
     * 导出幸运大抽奖规则列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:export')")
    @Log(title = "幸运大抽奖规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctLuckyDrawRule: CtLuckyDrawRule) {
        val list = ctLuckyDrawRuleService.selectCtLuckyDrawRuleList(ctLuckyDrawRule)
        val util = ExcelUtil(
            CtLuckyDrawRule::class.java
        )
        util.exportExcel(response, list, "幸运大抽奖规则数据")
    }

    /**
     * 获取幸运大抽奖规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctLuckyDrawRuleService.selectCtLuckyDrawRuleById(id))
    }

    /**
     * 新增幸运大抽奖规则
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:add')")
    @Log(title = "幸运大抽奖规则", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctLuckyDrawRule: CtLuckyDrawRule): AjaxResult {
        return toAjax(ctLuckyDrawRuleService.insertCtLuckyDrawRule(ctLuckyDrawRule))
    }

    /**
     * 修改幸运大抽奖规则
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:edit')")
    @Log(title = "幸运大抽奖规则", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctLuckyDrawRule: CtLuckyDrawRule): AjaxResult {
        return toAjax(ctLuckyDrawRuleService.updateCtLuckyDrawRule(ctLuckyDrawRule))
    }

    /**
     * 删除幸运大抽奖规则
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:remove')")
    @Log(title = "幸运大抽奖规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctLuckyDrawRuleService.deleteCtLuckyDrawRuleByIds(ids))
    }
}