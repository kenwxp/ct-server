package com.cloudtimes.product.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 商品目录对象 ct_product_catalog
 *
 * @author tank
 * @date 2023-03-06
 */
class CtProductCatalog : BaseEntity() {
    /** 商品条形码  */
    @Excel(name = "商品条形码")
    var barcode: String? = null

    /** 商品名称  */
    @Excel(name = "商品名称")
    var productName: String? = null

    /** 商品英文名称  */
    @Excel(name = "商品英文名称")
    var englishName: String? = null

    /** 品牌名称  */
    @Excel(name = "品牌名称")
    var productBrand: String? = null

    /** 商品分类编码  */
    @Excel(name = "商品分类编码")
    var productCode: String? = null

    /** 生产商  */
    @Excel(name = "生产商")
    var manufacturer: String? = null

    /** 产地  */
    @Excel(name = "产地")
    var originCountry: String? = null

    /** 规格  */
    @Excel(name = "规格")
    var specification: String? = null

    /** 单位  */
    @Excel(name = "单位")
    var unit: String? = null

    /** 单位  */
    @Excel(name = "单位")
    var weight: String? = null

    /** 上市日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上市日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var listingDate: Date? = null


    /** 建议零售价  */
    @Excel(name = "建议零售价")
    var lifeSpanDays: Int? = null

    /** 建议零售价  */
    @Excel(name = "建议零售价")
    var retailPrice: Long? = null

    /** 商品图片地址  */
    @Excel(name = "商品图片地址")
    var pictureUrl: String? = null

    /** 备注  */
    @Excel(name = "备注")
    var remarks: String? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("barcode", barcode)
            .append("productName", productName)
            .append("englishName", englishName)
            .append("productBrand", productBrand)
            .append("productCode", productCode)
            .append("manufacturer", manufacturer)
            .append("originCountry", originCountry)
            .append("specification", specification)
            .append("unit", unit)
            .append("weight", weight)
            .append("listingDate", listingDate)
            .append("lifeSpanDays", lifeSpanDays)
            .append("retailPrice", retailPrice)
            .append("pictureUrl", pictureUrl)
            .append("remarks", remarks)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}