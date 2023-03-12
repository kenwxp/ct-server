package com.cloudtimes.app.controller.mobile

import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.product.domain.dto.request.ImportProductRequest
import com.cloudtimes.product.domain.dto.response.ImportProductResponse
import com.cloudtimes.product.service.ICtShopProductBatchImportService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@Tag(name = "店家app业务相关接口")
@RestController
@RequestMapping(PrefixPathConstants.MOBILE_PATH_PREFIX + "/product")
class ShopProductController {
    @Autowired
    lateinit var importService: ICtShopProductBatchImportService

    @PostMapping("/upload")
    @Throws(Exception::class)
    @Operation(summary = "上传产品文件")
    fun uploadFile(file: MultipartFile): RestResult<ImportProductResponse> {
        return importService.uploadProductFile(file)
    }

    @PostMapping("/import")
    @Throws(Exception::class)
    @Operation(summary = "导入产品信息")
    fun import(@Valid @RequestBody request: ImportProductRequest): RestResult<Any> {
        return importService.importProductFile(request)
    }
}