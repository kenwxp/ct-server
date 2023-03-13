package com.cloudtimes.web.controller.promotion

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.promotion.domain.CtUserDrawRel
import com.cloudtimes.promotion.service.ICtUserDrawRelService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 用户中奖Controller
 *
 * @author tank
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/promotion/user_draw")
@Tag(name = "用户中奖")
class CtUserDrawRelController : BaseController() {
    @Autowired
    private lateinit var ctUserDrawRelService: ICtUserDrawRelService

    /**
     * 查询用户中奖列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:user_draw:list')")
    @GetMapping("/list")
    @Operation(summary = "查询用户中奖列表")
    fun list(ctUserDrawRel: CtUserDrawRel): TableDataInfo {
        startPage()
        val list: List<CtUserDrawRel> = ctUserDrawRelService.selectCtUserDrawRelList(
            ctUserDrawRel
        )
        return getDataTable(list)
    }

    /**
     * 导出用户中奖列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:user_draw:export')")
    @Log(title = "用户中奖", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出用户中奖列表")
    fun export(response: HttpServletResponse, ctUserDrawRel: CtUserDrawRel) {
        val list = ctUserDrawRelService.selectCtUserDrawRelList(ctUserDrawRel)
        val util = ExcelUtil(
            CtUserDrawRel::class.java
        )
        util.exportExcel(response, list, "用户中奖数据")
    }

    /**
     * 获取用户中奖详细信息
     */
    @PreAuthorize("@ss.hasPermi('promotion:user_draw:query')")
    @GetMapping(value = ["/{activityId}/{userId}"])
    @Operation(summary = "获取用户中奖详细信息")
    fun getInfo(@PathVariable("activityId") activityId: String, @PathVariable("userId") userId: String): AjaxResult {
        return AjaxResult.success(ctUserDrawRelService.selectCtUserDrawRelById(activityId, userId))
    }
}