package com.cloudtimes.app.controller.agent

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.account.domain.CtSuggestion
import com.cloudtimes.account.service.ICtSuggestionService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

class CtSuggestionListResponse(var rows: List<CtSuggestion>) : RestResult<Any>()

@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/suggestion")
@Api(tags = ["代理-投诉建议"])
class CtSuggestionController : BaseController() {
    @Autowired
    private lateinit var ctSuggestionService: ICtSuggestionService

    /**
     * 查询投诉建议列表
     */
    @GetMapping("/list")
    @ApiOperation("查询投诉建议列表")
    @ApiResponse(
        code = 200,
        message = "查询成功",
        response = CtSuggestionListResponse::class
    )
    fun list(ctSuggestion: CtSuggestion): TableDataInfo {
        startPage()
        val list: List<CtSuggestion> = ctSuggestionService.selectCtSuggestionList(ctSuggestion)
        return getDataTable(list)
    }

    /**
     * 获取投诉建议详细信息
     */
    @ApiOperation("获取投诉建议详细信息")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctSuggestionService.selectCtSuggestionById(id))
    }

    /**
     * 新增投诉建议
     */
    @Log(title = "投诉建议", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增投诉建议")
    fun add(@Valid @RequestBody ctSuggestion: CtSuggestion): AjaxResult {
        return toAjax(ctSuggestionService.insertCtSuggestion(ctSuggestion))
    }

    /**
     * 修改投诉建议
     */
    @Log(title = "投诉建议", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改投诉建议")
    fun edit(@RequestBody ctSuggestion: CtSuggestion): AjaxResult {
        return toAjax(ctSuggestionService.updateCtSuggestion(ctSuggestion))
    }
}
