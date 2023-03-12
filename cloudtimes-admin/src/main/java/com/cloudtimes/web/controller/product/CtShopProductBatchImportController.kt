package com.cloudtimes.web.controller.product

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.product.domain.CtShopProductBatchImport
import com.cloudtimes.product.domain.dto.response.ImportProductResponse
import com.cloudtimes.product.service.ICtShopProductBatchImportService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * 店铺商品批量导入Controller
 *
 * @author 沈兵
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/product/import")
@Tag(name = "店铺商品批量导入")
class CtShopProductBatchImportController : BaseController() {
    @Autowired
    private lateinit var importService: ICtShopProductBatchImportService

    @Operation(summary = "查询店铺商品批量导入列表")
    @PreAuthorize("@ss.hasPermi('product:import:list')")
    @GetMapping("/list")
    fun list(ctShopProductBatchImport: CtShopProductBatchImport): TableDataInfo {
        startPage()
        val list: List<CtShopProductBatchImport> =
            importService.selectCtShopProductBatchImportList(
                ctShopProductBatchImport
            )
        return getDataTable(list)
    }

    @PostMapping("/upload")
    @Throws(Exception::class)
    @Operation(summary = "上传产品文件")
    fun uploadFile(file: MultipartFile): RestResult<ImportProductResponse> {
        return importService.uploadProductFile(file)
    }

    @Operation(summary = "获取店铺商品批量导入详细信息")
    @PreAuthorize("@ss.hasPermi('product:import:query')")
    @GetMapping(value = ["/{fileName}"])
    fun getInfo(@PathVariable("fileName") fileName: String): AjaxResult {
        return AjaxResult.success(importService.selectCtShopProductBatchImportByFileName(fileName))
    }

    @Operation(summary = "新增店铺商品批量导入")
    @PreAuthorize("@ss.hasPermi('product:import:add')")
    @Log(title = "店铺商品批量导入", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctShopProductBatchImport: CtShopProductBatchImport): AjaxResult {
        return toAjax(importService.insertCtShopProductBatchImport(ctShopProductBatchImport))
    }

    @Operation(summary = "修改店铺商品批量导入")
    @PreAuthorize("@ss.hasPermi('product:import:edit')")
    @Log(title = "店铺商品批量导入", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctShopProductBatchImport: CtShopProductBatchImport): AjaxResult {
        return toAjax(importService.updateCtShopProductBatchImport(ctShopProductBatchImport))
    }
}