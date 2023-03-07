package com.cloudtimes.product.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import java.util.*

/**
 * 店铺商品对象 ct_shop_product
 *
 * @author tank
 * @date 2023-02-15
 */
@Data
@ApiModel(value = "CtShopProduct", description = "店铺商品")
class CtShopProduct : BaseEntity() {
    @ApiModelProperty(value = "店铺商品编号")
    var id: String? = null

    @ApiModelProperty(value = "店铺编号")
    @Excel(name = "店铺编号")
    var shopNo: String? = null

    @ApiModelProperty(value = "商品分类编码（冗余）")
    @Excel(name = "商品分类编码（冗余")
    var categoryCode: String? = null

    @ApiModelProperty(value = "商品条形码")
    @Excel(name = "商品条形码")
    var barcode: String? = null

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    var productName: String? = null

    @ApiModelProperty(value = "商品英文名称")
    @Excel(name = "商品英文名称")
    var englishName: String? = null

    @ApiModelProperty(value = "品牌名称")
    @Excel(name = "品牌名称")
    var brand: String? = null

    @ApiModelProperty(value = "标签")
    @Excel(name = "标签")
    var label: String? = null

    @ApiModelProperty(value = "供货商编号")
    @Excel(name = "供货商编号")
    var supplierId: String? = null

    @ApiModelProperty(value = "供货商")
    @Excel(name = "供货商")
    var supplier: String? = null

    @ApiModelProperty(value = "规格")
    @Excel(name = "规格")
    var specification: String? = null

    @ApiModelProperty(value = "单位")
    @Excel(name = "单位")
    var unit: String? = null

    @ApiModelProperty(value = "重量/净含量")
    @Excel(name = "重量/净含量")
    var weight: String? = null

    @ApiModelProperty(value = "颜色")
    @Excel(name = "颜色")
    var color: String? = null

    @ApiModelProperty(value = "尺寸")
    @Excel(name = "尺寸")
    var size: String? = null

    @ApiModelProperty(value = "样式/款式")
    @Excel(name = "样式/款式")
    var style: String? = null

    @ApiModelProperty(value = "进货价")
    @Excel(name = "进货价")
    var purchasePrice: Long? = null

    @ApiModelProperty(value = "零售价")
    @Excel(name = "零售价")
    var retailPrice: Long? = null

    @ApiModelProperty(value = "批发价")
    @Excel(name = "批发价")
    var wholesalePrice: Long? = null

    @ApiModelProperty(value = "会员价")
    @Excel(name = "会员价")
    var vipPrice: Long? = null

    @ApiModelProperty(value = "库存")
    @Excel(name = "库存")
    var stock: Long? = null

    /** 库存增量 */
    @JsonIgnore
    var stockDelta: Long = 0

    @ApiModelProperty(value = "库存上限")
    @Excel(name = "库存上限")
    var maxStock: Long? = null

    @ApiModelProperty(value = "库存下限")
    @Excel(name = "库存下限")
    var minStock: Long? = null

    @ApiModelProperty(value = "累计售出")
    @Excel(name = "累计售出")
    var totalSold: Long? = null

    @ApiModelProperty(value = "累计供货")
    @Excel(name = "累计供货")
    var totalSupplied: Long? = null

    @ApiModelProperty(value = "商品图片地址")
    @Excel(name = "商品图片地址")
    var pictureUrl: String? = null

    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var productionDate: Date? = null

    @ApiModelProperty(value = "保质期")
    @Excel(name = "保质期")
    var qualityPeriod: String? = null

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    var remarks: String? = null

    @ApiModelProperty(value = "商品是否启用")
    @Excel(name = "商品是否启用")
    @get:JvmName("getIsEnable")
    @set:JvmName("setIsEnable")
    var isEnable: String? = null

    @ApiModelProperty(value = "是否启用会员")
    @Excel(name = "是否启用会员")
    @get:JvmName("getIsEnableVipPrice")
    @set:JvmName("setIsEnableVipPrice")
    var isEnableVipPrice: String? = null

    @ApiModelProperty(value = "是否积分商品")
    @Excel(name = "是否积分商品")
    @get:JvmName("getIsEnablePoints")
    @set:JvmName("setIsEnablePoints")
    var isEnablePoints: String? = null

    @ApiModelProperty(value = "是否已删除")
    @Excel(name = "是否已删除")
    @get:JvmName("getIsDeleted")
    @set:JvmName("setIsDeleted")
    var isDeleted: String? = null

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "CtShopProduct(id=$id, shopNo=$shopNo, categoryCode=$categoryCode, barcode=$barcode, productName=$productName, englishName=$englishName, brand=$brand, label=$label, supplierId=$supplierId, supplier=$supplier, specification=$specification, unit=$unit, weight=$weight, color=$color, size=$size, style=$style, purchasePrice=$purchasePrice, retailPrice=$retailPrice, wholesalePrice=$wholesalePrice, vipPrice=$vipPrice, stock=$stock, maxStock=$maxStock, minStock=$minStock, totalSold=$totalSold, totalSupplied=$totalSupplied, pictureUrl=$pictureUrl, productionDate=$productionDate, qualityPeriod=$qualityPeriod, remarks=$remarks)"
    }
}
