package com.cloudtimes.web.controller.product

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.service.ICtProductThirdService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 第三方商品Controller
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@RestController
@RequestMapping("/product/product_third")
@Tag(name = "第三方商品")
class CtProductThirdController : BaseController() {
    @Autowired
    private lateinit var ctProductThirdService: ICtProductThirdService

    /**
     * 查询第三方商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_third:list')")
    @GetMapping("/list")
    @Operation(summary = "查询第三方商品列表")
    fun list(ctProductThird: CtProductThird): TableDataInfo {
        startPage()
        val list: List<CtProductThird> = ctProductThirdService.selectCtProductThirdList(
            ctProductThird
        )
        return getDataTable(list)
    }

    /**
     * 导出第三方商品列表
     */
    @PreAuthorize("@ss.hasPermi('product:product_third:export')")
    @Log(title = "第三方商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出第三方商品列表")
    fun export(response: HttpServletResponse, ctProductThird: CtProductThird) {
        val list = ctProductThirdService.selectCtProductThirdList(ctProductThird)
        val util = ExcelUtil(
            CtProductThird::class.java
        )
        util.exportExcel(response, list, "第三方商品数据")
    }

    /**
     * 获取第三方商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:product_third:query')")
    @GetMapping(value = ["/{thirdCode}"])
    @Operation(summary = "获取第三方商品详细信息")
    fun getInfo(@PathVariable("thirdCode") thirdCode: String): AjaxResult {
        return AjaxResult.success(ctProductThirdService.selectCtProductThirdByThirdCode(thirdCode))
    }

    /**
     * 新增第三方商品
     */
    @PreAuthorize("@ss.hasPermi('product:product_third:add')")
    @Log(title = "第三方商品", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增第三方商品")
    fun add(@RequestBody ctProductThird: CtProductThird): AjaxResult {
        return toAjax(ctProductThirdService.insertCtProductThird(ctProductThird))
    }

    /**
     * 修改第三方商品
     */
    @PreAuthorize("@ss.hasPermi('product:product_third:edit')")
    @Log(title = "第三方商品", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改第三方商品")
    fun edit(@RequestBody ctProductThird: CtProductThird): AjaxResult {
        return toAjax(ctProductThirdService.updateCtProductThird(ctProductThird))
    }
}