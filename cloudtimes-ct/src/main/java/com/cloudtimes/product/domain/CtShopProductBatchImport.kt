package com.cloudtimes.product.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 店铺商品批量导入对象 ct_shop_product_batch_import
 *
 * @author 沈兵
 * @date 2023-03-12
 */
class CtShopProductBatchImport : BaseEntity() {
    /** 文件名  */
    var fileName: String? = null

    /** 店铺编号  */
    @Excel(name = "店铺编号")
    var shopNo: String? = null

    /** 批次号  */
    @Excel(name = "批次号")
    var batchNo: String? = null

    /** 文件路径  */
    @Excel(name = "文件路径")
    var filePath: String? = null

    /** 总笔数  */
    @Excel(name = "总笔数")
    var totalNum: Long? = null

    /** 成功笔数  */
    @Excel(name = "成功笔数")
    var successNum: Long? = null

    /** 失败笔数  */
    @Excel(name = "失败笔数")
    var failNum: Long? = null

    /** 是否已经导入  */
    @Excel(name = "是否已经导入")
    var isImported: String? = null

    /** 是否导入成功  */
    @Excel(name = "是否导入成功")
    var isSuccess: String? = null

    /** 上传日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var uploadDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("fileName", fileName)
            .append("shopNo", shopNo)
            .append("batchNo", batchNo)
            .append("filePath", filePath)
            .append("totalNum", totalNum)
            .append("successNum", successNum)
            .append("failNum", failNum)
            .append("isImported", isImported)
            .append("isSuccess", isSuccess)
            .append("remark", remark)
            .append("uploadDate", uploadDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}