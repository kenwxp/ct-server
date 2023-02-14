package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtSuggestion
import com.cloudtimes.account.service.ICtSuggestionService
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
 * 投诉建议Controller
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@RestController
@RequestMapping("/account/suggestion")
class CtSuggestionController : BaseController() {
    @Autowired
    private lateinit var ctSuggestionService: ICtSuggestionService

    /**
     * 查询投诉建议列表
     */
    @PreAuthorize("@ss.hasPermi('account:suggestion:list')")
    @GetMapping("/list")
    fun list(ctSuggestion: CtSuggestion): TableDataInfo {
        startPage()
        val list: List<CtSuggestion> = ctSuggestionService.selectCtSuggestionList(ctSuggestion)
        return getDataTable(list)
    }

    /**
     * 导出投诉建议列表
     */
    @PreAuthorize("@ss.hasPermi('account:suggestion:export')")
    @Log(title = "投诉建议", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctSuggestion: CtSuggestion) {
        val list = ctSuggestionService.selectCtSuggestionList(ctSuggestion)
        val util = ExcelUtil(
            CtSuggestion::class.java
        )
        util.exportExcel(response, list, "投诉建议数据")
    }

    /**
     * 获取投诉建议详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:suggestion:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctSuggestionService.selectCtSuggestionById(id))
    }

    /**
     * 新增投诉建议
     */
    @PreAuthorize("@ss.hasPermi('account:suggestion:add')")
    @Log(title = "投诉建议", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctSuggestion: CtSuggestion): AjaxResult {
        return toAjax(ctSuggestionService.insertCtSuggestion(ctSuggestion))
    }

    /**
     * 修改投诉建议
     */
    @PreAuthorize("@ss.hasPermi('account:suggestion:edit')")
    @Log(title = "投诉建议", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctSuggestion: CtSuggestion): AjaxResult {
        return toAjax(ctSuggestionService.updateCtSuggestion(ctSuggestion))
    }
}