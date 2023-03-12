package com.cloudtimes.product.controller

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.product.domain.CtShopProduct
import com.cloudtimes.product.service.ICtShopProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 店铺商品Controller
 *
 * @author 沈兵
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/product/shop_product")
class CtShopProductController : BaseController() {
    @Autowired
    private lateinit var ctShopProductService: ICtShopProductService

    /**
     * 查询店铺商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:shop_product:list')")
    @GetMapping("/list")
    fun list(ctShopProduct: CtShopProduct): TableDataInfo {
        startPage()
        val list: List<CtShopProduct> = ctShopProductService.selectCtShopProductList(
            ctShopProduct
        )
        return getDataTable(list)
    }

    /**
     * 导出店铺商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:shop_product:export')")
    @Log(title = "店铺商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctShopProduct: CtShopProduct) {
        val list = ctShopProductService.selectCtShopProductList(ctShopProduct)
        val util = ExcelUtil(
            CtShopProduct::class.java
        )
        util.exportExcel(response, list, "店铺商品数据")
    }

    /**
     * 获取店铺商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:shop_product:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        return AjaxResult.success(ctShopProductService.selectCtShopProductById(id))
    }

    /**
     * 新增店铺商品
     */
    @PreAuthorize("@ss.hasPermi('product:shop_product:add')")
    @Log(title = "店铺商品", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctShopProduct: CtShopProduct): AjaxResult {
        return toAjax(ctShopProductService.insertCtShopProduct(ctShopProduct))
    }

    /**
     * 修改店铺商品
     */
    @PreAuthorize("@ss.hasPermi('product:shop_product:edit')")
    @Log(title = "店铺商品", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctShopProduct: CtShopProduct): AjaxResult {
        return toAjax(ctShopProductService.updateCtShopProduct(ctShopProduct))
    }
}