package com.cloudtimes.web.controller.product

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.product.domain.CtProductCatalog
import com.cloudtimes.product.service.ICtProductCatalogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 商品目录Controller
 *
 * @author tank
 * @date 2023-03-06
 */
@RestController
@RequestMapping("/product/product_catalog")
class CtProductCatalogController : BaseController() {
    @Autowired
    private lateinit var ctProductCatalogService: ICtProductCatalogService

    /**
     * 查询商品目录列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:list')")
    @GetMapping("/list")
    fun list(ctProductCatalog: CtProductCatalog): TableDataInfo {
        startPage()
        val list: List<CtProductCatalog> = ctProductCatalogService.selectCtProductCatalogList(
            ctProductCatalog
        )
        return getDataTable(list)
    }

    /**
     * 导出商品目录列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:export')")
    @Log(title = "商品目录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctProductCatalog: CtProductCatalog) {
        val list = ctProductCatalogService.selectCtProductCatalogList(ctProductCatalog)
        val util = ExcelUtil(
            CtProductCatalog::class.java
        )
        util.exportExcel(response, list, "商品目录数据")
    }

    /**
     * 获取商品目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctProductCatalogService.selectCtProductCatalogById(id))
    }

    /**
     * 新增商品目录
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:add')")
    @Log(title = "商品目录", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctProductCatalog: CtProductCatalog): AjaxResult {
        return toAjax(ctProductCatalogService.insertCtProductCatalog(ctProductCatalog))
    }

    /**
     * 修改商品目录
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:edit')")
    @Log(title = "商品目录", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctProductCatalog: CtProductCatalog): AjaxResult {
        return toAjax(ctProductCatalogService.updateCtProductCatalog(ctProductCatalog))
    }

    /**
     * 删除商品目录
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:remove')")
    @Log(title = "商品目录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctProductCatalogService.deleteCtProductCatalogByIds(ids))
    }
}