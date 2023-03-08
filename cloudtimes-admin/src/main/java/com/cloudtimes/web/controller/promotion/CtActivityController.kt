package com.cloudtimes.web.controller.promotion

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.promotion.domain.CtActivity
import com.cloudtimes.promotion.service.ICtActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 营销活动Controller
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/promotion/activity")
class CtActivityController : BaseController() {
    @Autowired
    private lateinit var ctActivityService: ICtActivityService

    /**
     * 查询营销活动列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity:list')")
    @GetMapping("/list")
    fun list(ctActivity: CtActivity): TableDataInfo {
        startPage()
        val list: List<CtActivity> = ctActivityService.selectCtActivityList(ctActivity)
        return getDataTable(list)
    }

    /**
     * 导出营销活动列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity:export')")
    @Log(title = "营销活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctActivity: CtActivity) {
        val list = ctActivityService.selectCtActivityList(ctActivity)
        val util = ExcelUtil(
            CtActivity::class.java
        )
        util.exportExcel(response, list, "营销活动数据")
    }

    /**
     * 获取营销活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctActivityService.selectCtActivityById(id))
    }

    /**
     * 新增营销活动
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity:add')")
    @Log(title = "营销活动", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctActivity: CtActivity): AjaxResult {
        return toAjax(ctActivityService.insertCtActivity(ctActivity))
    }

    /**
     * 修改营销活动
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity:edit')")
    @Log(title = "营销活动", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctActivity: CtActivity): AjaxResult {
        return toAjax(ctActivityService.updateCtActivity(ctActivity))
    }

    /**
     * 删除营销活动
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity:remove')")
    @Log(title = "营销活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctActivityService.deleteCtActivityByIds(ids))
    }
}