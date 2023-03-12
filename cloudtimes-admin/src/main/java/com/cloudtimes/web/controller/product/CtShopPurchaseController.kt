package com.cloudtimes.web.controller.product

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.product.domain.CtShopPurchase
import com.cloudtimes.product.service.ICtShopPurchaseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 店铺商品采购Controller
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@RestController
@RequestMapping("/product/shop_purchase")
@Tag(name = "店铺商品采购")
class CtShopPurchaseController : BaseController() {
    @Autowired
    private lateinit var ctShopPurchaseService: ICtShopPurchaseService

    /**
     * 查询店铺商品采购列表
     */
    @PreAuthorize("@ss.hasPermi('product:shop_purchase:list')")
    @GetMapping("/list")
    @Operation(summary = "查询店铺商品采购列表")
    fun list(ctShopPurchase: CtShopPurchase): TableDataInfo {
        startPage()
        val list: List<CtShopPurchase> = ctShopPurchaseService.selectCtShopPurchaseList(
            ctShopPurchase
        )
        return getDataTable(list)
    }

    /**
     * 导出店铺商品采购列表
     */
    @PreAuthorize("@ss.hasPermi('product:shop_purchase:export')")
    @Log(title = "店铺商品采购", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary =  "导出店铺商品采购列表")
    fun export(response: HttpServletResponse, ctShopPurchase: CtShopPurchase) {
        val list = ctShopPurchaseService.selectCtShopPurchaseList(ctShopPurchase)
        val util = ExcelUtil(
            CtShopPurchase::class.java
        )
        util.exportExcel(response, list, "店铺商品采购数据")
    }

    /**
     * 获取店铺商品采购详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:shop_purchase:query')")
    @GetMapping(value = ["/{id}"])
    @Operation(summary = "获取店铺商品采购详细信息")
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctShopPurchaseService.selectCtShopPurchaseById(id))
    }

    /**
     * 新增店铺商品采购
     */
    @PreAuthorize("@ss.hasPermi('product:shop_purchase:add')")
    @Log(title = "店铺商品采购", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增店铺商品采购")
    fun add(@RequestBody ctShopPurchase: CtShopPurchase): AjaxResult {
        return toAjax(ctShopPurchaseService.insertCtShopPurchase(ctShopPurchase))
    }

    /**
     * 修改店铺商品采购
     */
    @PreAuthorize("@ss.hasPermi('product:shop_purchase:edit')")
    @Log(title = "店铺商品采购", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改店铺商品采购")
    fun edit(@RequestBody ctShopPurchase: CtShopPurchase): AjaxResult {
        return toAjax(ctShopPurchaseService.updateCtShopPurchase(ctShopPurchase))
    }

    /**
     * 删除店铺商品采购
     */
    @PreAuthorize("@ss.hasPermi('product:shop_purchase:remove')")
    @Log(title = "店铺商品采购", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除店铺商品采购")
    fun remove(@PathVariable ids: Array<String>): AjaxResult {
        return toAjax(ctShopPurchaseService.deleteCtShopPurchaseByIds(ids))
    }
}