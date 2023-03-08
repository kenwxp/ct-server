package com.cloudtimes.web.controller.promotion

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.promotion.domain.CtActivityStoreRel
import com.cloudtimes.promotion.service.ICtActivityStoreRelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 活动店铺关系Controller
 *
 * @author tank
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/promotion/activity_store_rel")
class CtActivityStoreRelController : BaseController() {
    @Autowired
    private lateinit var ctActivityStoreRelService: ICtActivityStoreRelService

    /**
     * 查询活动店铺关系列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity_store_rel:list')")
    @GetMapping("/list")
    fun list(ctActivityStoreRel: CtActivityStoreRel): TableDataInfo {
        startPage()
        val list: List<CtActivityStoreRel> = ctActivityStoreRelService.selectCtActivityStoreRelList(
            ctActivityStoreRel
        )
        return getDataTable(list)
    }

    /**
     * 导出活动店铺关系列表
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity_store_rel:export')")
    @Log(title = "活动店铺关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctActivityStoreRel: CtActivityStoreRel) {
        val list = ctActivityStoreRelService.selectCtActivityStoreRelList(
            ctActivityStoreRel
        )
        val util = ExcelUtil(
            CtActivityStoreRel::class.java
        )
        util.exportExcel(response, list, "活动店铺关系数据")
    }

    /**
     * 获取活动店铺关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity_store_rel:query')")
    @GetMapping(value = ["/{activityId}"])
    fun getInfo(@PathVariable("activityId") activityId: String): AjaxResult {
        return AjaxResult.success(ctActivityStoreRelService.selectCtActivityStoreRelByActivityId(activityId))
    }

    /**
     * 新增活动店铺关系
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity_store_rel:add')")
    @Log(title = "活动店铺关系", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctActivityStoreRel: CtActivityStoreRel): AjaxResult {
        return toAjax(ctActivityStoreRelService.insertCtActivityStoreRel(ctActivityStoreRel))
    }

    /**
     * 修改活动店铺关系
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity_store_rel:edit')")
    @Log(title = "活动店铺关系", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctActivityStoreRel: CtActivityStoreRel): AjaxResult {
        return toAjax(ctActivityStoreRelService.updateCtActivityStoreRel(ctActivityStoreRel))
    }

    /**
     * 删除活动店铺关系
     */
    @PreAuthorize("@ss.hasPermi('promotion:activity_store_rel:remove')")
    @Log(title = "活动店铺关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    fun remove(@PathVariable activityIds: Array<String>): AjaxResult {
        return toAjax(ctActivityStoreRelService.deleteCtActivityStoreRelByActivityIds(activityIds))
    }
}