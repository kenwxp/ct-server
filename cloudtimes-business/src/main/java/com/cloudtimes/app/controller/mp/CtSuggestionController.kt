package com.cloudtimes.app.controller.mp

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.account.domain.CtSuggestion
import com.cloudtimes.account.service.ICtSuggestionService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

class CtSuggestionListResponse(var rows: List<CtSuggestion>) : RestResult<Any>()

@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/suggestion")
@Tag(name = "代理-投诉建议")
class CtSuggestionController : BaseController() {
    @Autowired
    private lateinit var ctSuggestionService: ICtSuggestionService

    /**
     * 查询投诉建议列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询投诉建议列表")
    fun list(ctSuggestion: CtSuggestion): TableDataInfo {
        startPage()
        val list: List<CtSuggestion> = ctSuggestionService.selectCtSuggestionList(ctSuggestion)
        return getDataTable(list)
    }

    /**
     * 获取投诉建议详细信息
     */
    @Operation(summary = "获取投诉建议详细信息")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctSuggestionService.selectCtSuggestionById(id))
    }

    /**
     * 新增投诉建议
     */
    @Log(title = "投诉建议", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增投诉建议")
    fun add(@Valid @RequestBody ctSuggestion: CtSuggestion): AjaxResult {
        return toAjax(ctSuggestionService.insertCtSuggestion(ctSuggestion))
    }

    /**
     * 修改投诉建议
     */
    @Log(title = "投诉建议", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改投诉建议")
    fun edit(@RequestBody ctSuggestion: CtSuggestion): AjaxResult {
        return toAjax(ctSuggestionService.updateCtSuggestion(ctSuggestion))
    }
}
