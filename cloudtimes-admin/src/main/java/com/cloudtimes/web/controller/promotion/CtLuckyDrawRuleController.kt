package com.cloudtimes.web.controller.promotion

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.service.ICtLuckyDrawRuleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 抽奖规则Controller
 *
 * @author tank
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/promotion/draw_rule")
@Tag(name = "抽奖规则")
class CtLuckyDrawRuleController : BaseController() {
    @Autowired
    private lateinit var ctLuckyDrawRuleService: ICtLuckyDrawRuleService

    /**
     * 查询抽奖规则列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:list')")
    @GetMapping("/list")
    @Operation(summary = "查询抽奖规则列表")
    fun list(ctLuckyDrawRule: CtLuckyDrawRule): TableDataInfo {
        startPage()
        val list: List<CtLuckyDrawRule> = ctLuckyDrawRuleService.selectCtLuckyDrawRuleList(
            ctLuckyDrawRule
        )
        return getDataTable(list)
    }

    /**
     * 导出抽奖规则列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:export')")
    @Log(title = "抽奖规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出抽奖规则列表")
    fun export(response: HttpServletResponse, ctLuckyDrawRule: CtLuckyDrawRule) {
        val list = ctLuckyDrawRuleService.selectCtLuckyDrawRuleList(ctLuckyDrawRule)
        val util = ExcelUtil(
            CtLuckyDrawRule::class.java
        )
        util.exportExcel(response, list, "抽奖规则数据")
    }

    /**
     * 获取抽奖规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:query')")
    @GetMapping(value = ["/{id}"])
    @Operation(summary = "获取抽奖规则详细信息")
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctLuckyDrawRuleService.selectCtLuckyDrawRuleById(id))
    }

    /**
     * 新增抽奖规则
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:add')")
    @Log(title = "抽奖规则", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增抽奖规则")
    fun add(@RequestBody ctLuckyDrawRule: CtLuckyDrawRule): AjaxResult {
        return toAjax(ctLuckyDrawRuleService.insertCtLuckyDrawRule(ctLuckyDrawRule))
    }

    /**
     * 修改抽奖规则
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:edit')")
    @Log(title = "抽奖规则", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改抽奖规则")
    fun edit(@RequestBody ctLuckyDrawRule: CtLuckyDrawRule): AjaxResult {
        return toAjax(ctLuckyDrawRuleService.updateCtLuckyDrawRule(ctLuckyDrawRule))
    }

    /**
     * 删除抽奖规则
     */
    @PreAuthorize("@ss.hasPermi('promotion:draw_rule:remove')")
    @Log(title = "抽奖规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除抽奖规则")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctLuckyDrawRuleService.deleteCtLuckyDrawRuleByIds(ids))
    }
}