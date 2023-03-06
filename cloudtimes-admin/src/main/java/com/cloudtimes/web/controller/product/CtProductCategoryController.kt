package com.cloudtimes.web.controller.product

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.product.domain.CtProductCategory
import com.cloudtimes.product.service.ICtProductCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 商品分类Controller
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@RestController
@RequestMapping("/product/product_category")
class CtProductCategoryController : BaseController() {
    @Autowired
    private lateinit var ctProductCategoryService: ICtProductCategoryService

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_category:list')")
    @GetMapping("/list")
    fun list(ctProductCategory: CtProductCategory): TableDataInfo {
        startPage()
        val list: List<CtProductCategory> = ctProductCategoryService.selectCtProductCategoryList(
            ctProductCategory
        )
        return getDataTable(list)
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctProductCategory: CtProductCategory) {
        val list = ctProductCategoryService.selectCtProductCategoryList(
            ctProductCategory
        )
        val util = ExcelUtil(
            CtProductCategory::class.java
        )
        util.exportExcel(response, list, "商品分类数据")
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:product_category:query')")
    @GetMapping(value = ["/{categoryCode}"])
    fun getInfo(@PathVariable("categoryCode") categoryCode: String): AjaxResult {
        return AjaxResult.success(ctProductCategoryService.selectCtProductCategoryByCategoryCode(categoryCode))
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('product:product_category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctProductCategory: CtProductCategory): AjaxResult {
        return toAjax(ctProductCategoryService.insertCtProductCategory(ctProductCategory))
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('product:product_category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctProductCategory: CtProductCategory): AjaxResult {
        return toAjax(ctProductCategoryService.updateCtProductCategory(ctProductCategory))
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('product:product_category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryCodes}")
    fun remove(@PathVariable categoryCodes: Array<String>): AjaxResult {
        return toAjax(ctProductCategoryService.deleteCtProductCategoryByCategoryCodes(categoryCodes))
    }
}