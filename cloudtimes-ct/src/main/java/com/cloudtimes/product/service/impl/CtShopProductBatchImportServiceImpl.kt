package com.cloudtimes.product.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.config.CloudTimesConfig
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.common.utils.file.FileUploadUtils
import com.cloudtimes.product.domain.CtShopProductBatchImport
import com.cloudtimes.product.domain.dto.request.ImportProductRequest
import com.cloudtimes.product.domain.dto.response.ImportProductResponse
import com.cloudtimes.product.mapper.CtShopProductBatchImportMapper
import com.cloudtimes.product.service.ICtShopProductBatchImportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.regex.Pattern


private val fileNamePattern: Pattern = Pattern.compile("^(CT\\d{10,20})_(\\d{8})_(\\d{1,3}).xlsx$")

/**
 * 店铺商品批量导入Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-12
 */
@DataSource(DataSourceType.CT)
@Service
class CtShopProductBatchImportServiceImpl : ICtShopProductBatchImportService {
    @Autowired
    private lateinit var ctShopProductBatchImportMapper: CtShopProductBatchImportMapper

    /** 解析文件名 */
    override fun parseFilename(uploadFileName: String): Pair<Boolean, CtShopProductBatchImport?> {
        val matcher = fileNamePattern.matcher(uploadFileName);
        return if (matcher.find()) {
            Pair(
                true,
                CtShopProductBatchImport().apply {
                    fileName = uploadFileName
                    shopNo = matcher.group(1)
                    batchNo = matcher.group(3)
                }
            )
        } else {
            Pair(false, null)
        }
    }

    /** 上传产品文件 */
    override fun uploadProductFile(file: MultipartFile): RestResult<ImportProductResponse> {
        try {
            // Step 1. 校验文件名
            val originalFilename = file.originalFilename ?:
            return RestResult<ImportProductResponse>().apply {
                code = HttpStatus.BAD_REQUEST.value()
                msg = "文件名不能为空"
            }

            val (isOk, productImport) = parseFilename(originalFilename)
            if (!isOk) {
                return RestResult<ImportProductResponse>().apply {
                    code = HttpStatus.BAD_REQUEST.value()
                    msg = "文件名规则校验错误"
                }
            }

            // Step 2. 检查文件是否已经上传
            val existProductImport = selectCtShopProductBatchImportByFileName(originalFilename)
            if (existProductImport != null) {
                return RestResult<ImportProductResponse>().apply {
                    code = HttpStatus.BAD_REQUEST.value()
                    msg = "同名文件已经上传过"
                }
            }

            // Step 3. 保存文件
            val filePath = CloudTimesConfig.getUploadPath()
            val newFilePath = FileUploadUtils.upload(filePath, file)
            productImport!!.filePath = newFilePath

            // Step 4. 登记上传记录
            insertCtShopProductBatchImport(productImport)

            return RestResult<ImportProductResponse>().apply {
                data = ImportProductResponse(originalFilename)
            }
        } catch (e: Exception) {
            return RestResult<ImportProductResponse>().apply {
                code = HttpStatus.INTERNAL_SERVER_ERROR.value()
                msg = e.message ?: ""
            }
        }
    }

    /** 导入产品文件 */
    override fun importProductFile(request: ImportProductRequest): RestResult<Any> {
        return RestResult()
    }

    /**
     * 查询店铺商品批量导入
     *
     * @param fileName 店铺商品批量导入主键
     * @return 店铺商品批量导入
     */
    override fun selectCtShopProductBatchImportByFileName(fileName: String): CtShopProductBatchImport? {
        return ctShopProductBatchImportMapper.selectCtShopProductBatchImportByFileName(fileName)
    }

    /**
     * 查询店铺商品批量导入列表
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 店铺商品批量导入
     */
    override fun selectCtShopProductBatchImportList(ctShopProductBatchImport: CtShopProductBatchImport): List<CtShopProductBatchImport> {
        return ctShopProductBatchImportMapper.selectCtShopProductBatchImportList(ctShopProductBatchImport)
    }

    /**
     * 新增店铺商品批量导入
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 结果
     */
    override fun insertCtShopProductBatchImport(ctShopProductBatchImport: CtShopProductBatchImport): Int {
        ctShopProductBatchImport.createTime = DateUtils.getNowDate()
        return ctShopProductBatchImportMapper.insertCtShopProductBatchImport(ctShopProductBatchImport)
    }

    /**
     * 修改店铺商品批量导入
     *
     * @param ctShopProductBatchImport 店铺商品批量导入
     * @return 结果
     */
    override fun updateCtShopProductBatchImport(ctShopProductBatchImport: CtShopProductBatchImport): Int {
        ctShopProductBatchImport.updateTime = DateUtils.getNowDate()
        return ctShopProductBatchImportMapper.updateCtShopProductBatchImport(ctShopProductBatchImport)
    }
}