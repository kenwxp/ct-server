package com.cloudtimes.thirdpart.dto.request

import com.cloudtimes.product.domain.CtShopProduct
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
{
"createTime": "2022-10-17 16:06:00",
"goodName": "优选到家YX-901居家男士棉拖42-43码（运动鞋41码左右）",
"goodsId": "21471",
"goodsModel": "1件=20双",
"isGrounding": "Y",
"packingAmount": "20",
"scPrice": "9.900000",
"shelfLifeDays": 1800,
"smallLineCode": "6937826890116",
"smallPrice": "7.650000",
"smallUnit": "双"
}
*/

@Schema(description = "蓉城易购商品记录")
class RcygProductRecord {
    @Schema(description = "商品条码")
    @JsonProperty("smallLineCode")
    var barcode: String? = null

    @Schema(description = "蓉城易购商品唯一ID")
    @JsonProperty("goodsId")
    @field:NotEmpty(message = "蓉城易购商品唯一ID")
    @field:NotNull(message = "蓉城易购商品唯一ID")
    var thirdProductId: String = ""

    @Schema(description = "商品名称")
    @JsonProperty("goodName")
    var productName: String = ""

    @Schema(description = "规格")
    @JsonProperty("goodsModel")
    var specification: String? = null

    @Schema(description = "单位")
    @JsonProperty("smallUnit")
    var unit: String? = null

    @Schema(description = "给商户的供货价格")
    @JsonProperty("smallPrice")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var retailPrice: BigDecimal? = null

    @Schema(description = "批发价格")
    @JsonProperty("scPrice")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var wholesalePrice: BigDecimal? = null

    @Schema(description = "物流包装数")
    @JsonProperty("packingAmount")
    var packingTotal: String? = null

    @Schema(description = "是否上架, Y: 上架, N: 下架")
    @JsonProperty("isGrounding")
    var isAvailable: String? = null

    @Schema(description = "创建时间")
    @JsonProperty("createTime")
    var createTime: String? = null

    @Schema(description = "保质期天数")
    @JsonProperty("shelfLifeDays")
    var lifeSpanDays: Int? = 0

    override fun toString(): String {
        return "RcygProductRecord(barCode='$barcode', thirdProductId='$thirdProductId', productName='$productName', specification='$specification', unit='$unit', retailPrice=$retailPrice, wholeSalePrice=$wholesalePrice, packingTotal='$packingTotal', isAvailable='$isAvailable', createTime='$createTime', lifeSpanDays=$lifeSpanDays)"
    }

    fun toShopProduct(shopNo: String): CtShopProduct {
        val purchasePrice = retailPrice?.multiply(BigDecimal("100"))?.toInt() ?: 0

        return CtShopProduct().also {
            it.shopNo = shopNo
            it.barcode = barcode
            it.productName = productName
            it.purchasePrice = purchasePrice.toLong()
            it.retailPrice = 999900
            it.specification = specification
            it.unit = unit
        }
    }
}
