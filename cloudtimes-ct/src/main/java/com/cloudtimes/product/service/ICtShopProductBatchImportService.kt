package com.cloudtimes.product.service

import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.product.domain.CtShopProductBatchImport
import com.cloudtimes.product.domain.dto.request.ImportProductRequest
import com.cloudtimes.product.domain.dto.response.ImportProductResponse
import org.springframework.web.multipart.MultipartFile

/**
 * 店铺商品批量导入Service接口
 *
 * @author 沈兵
 * @date 2023-03-12
 */
interface ICtShopProductBatchImportService {
    /** 解析文件名 */
    fun parseFilename(filename: String): Pair<Boolean, CtShopProductBatchImport?>

    /** 上传产品文件 */
    fun uploadProductFile(file: MultipartFile): RestResult<ImportProductResponse>

    /** 导入产品文件 */
    fun importProductFile(request: ImportProductRequest): RestResult<Any>

    /**
     * 查询店铺商品批量导入
     *
     * @param fileName 店铺商品批量导入主键
     * @return 店铺商品批量导入
     */
    fun selectCtShopProductBatchImportByFileName(fileName: String): CtShopProductBatchImport?

    /**
     * 查询店铺商品批量导入列表
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 店铺商品批量导入集合
     */
    fun selectCtShopProductBatchImportList(ctShopProductBatchImport: CtShopProductBatchImport): List<CtShopProductBatchImport>

    /**
     * 新增店铺商品批量导入
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 结果
     */
    fun insertCtShopProductBatchImport(ctShopProductBatchImport: CtShopProductBatchImport): Int

    /**
     * 修改店铺商品批量导入
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 结果
     */
    fun updateCtShopProductBatchImport(ctShopProductBatchImport: CtShopProductBatchImport): Int
}