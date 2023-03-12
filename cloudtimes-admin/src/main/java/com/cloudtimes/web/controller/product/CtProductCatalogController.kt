package com.cloudtimes.web.controller.product

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.product.domain.CtProductCatalog
import com.cloudtimes.product.service.ICtProductCatalogService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "商品目录")
class CtProductCatalogController : BaseController() {
    @Autowired
    private lateinit var ctProductCatalogService: ICtProductCatalogService

    /**
     * 查询商品目录列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:list')")
    @GetMapping("/list")
    @Operation(summary = "查询商品目录列表")
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
    @Operation(summary = "导出商品目录列表")
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
    @GetMapping(value = ["/{barcode}"])
    @Operation(summary = "获取商品目录详细信息")
    fun getInfo(@PathVariable("barcode") barcode: String): AjaxResult {
        return AjaxResult.success(ctProductCatalogService.selectCtProductCatalogByBarcode(barcode))
    }

    /**
     * 新增商品目录
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:add')")
    @Log(title = "商品目录", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增商品目录")
    fun add(@RequestBody ctProductCatalog: CtProductCatalog): AjaxResult {
        return toAjax(ctProductCatalogService.insertCtProductCatalog(ctProductCatalog))
    }

    /**
     * 修改商品目录
     */
    @PreAuthorize("@ss.hasPermi('product:product_catalog:edit')")
    @Log(title = "商品目录", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改商品目录")
    fun edit(@RequestBody ctProductCatalog: CtProductCatalog): AjaxResult {
        return toAjax(ctProductCatalogService.updateCtProductCatalog(ctProductCatalog))
    }
}