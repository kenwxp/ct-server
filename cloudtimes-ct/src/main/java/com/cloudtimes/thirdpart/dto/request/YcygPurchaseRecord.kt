package com.cloudtimes.thirdpart.dto.request

import com.cloudtimes.product.domain.CtShopProduct
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "PurchaseRecord", description = "采购记录")
class YcygPurchaseRecord {
    @ApiModelProperty(value = "生产日期")
    @JsonProperty("scrq")
    var manufacturingDate: String? = null

    @ApiModelProperty(value = "保质截止日期")
    @JsonProperty("bzjzrq")
    var expireDate: String? = null

    @ApiModelProperty(value = "商品类型 是否为赠品 T否 S是")
    @JsonProperty("splx")
    var giftType: String? = null

    @ApiModelProperty(value = "商品条码")
    @JsonProperty("spsmm")
    var barCode: String = ""

    @ApiModelProperty(value = "收银系统商品唯一ID")
    @JsonProperty("spbm")
    var casherProductId: String? = ""

    @ApiModelProperty(value = "蓉城易购商品唯一ID")
    @JsonProperty("factorycode")
    @NotEmpty(message = "蓉城易购商品唯一ID")
    var thirdProductId: String = ""

    @ApiModelProperty(value = "商品名称")
    @JsonProperty("spmc")
    var productName: String = ""

    @ApiModelProperty(value = "入库价格")
    @JsonProperty("hsjj")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var wholesalePrice: BigDecimal? = null

    @ApiModelProperty(value = "入库数量（基本包装）")
    @JsonProperty("rksl")
    @field:NotNull(message = "入库数量不能为空")
    var total: Int = 0

    @ApiModelProperty(value = "特供状态ID(DM特供,00正常)")
    @JsonProperty("cxtype")
    var specialSupplyType: String? = null

    override fun toString(): String {
        return "YcygPurchaseRecord(manufacturingDate='$manufacturingDate', expireDate='$expireDate', giftType='$giftType', barCode='$barCode', casherProductId='$casherProductId', thirdProductId='$thirdProductId', productName='$productName', wholesalePrice='$wholesalePrice', total=$total, specialSupplyType=$specialSupplyType)"
    }

    fun toRcygProduct() = RcygProductRecord().also {
        it.barcode = barCode
        it.productName = productName
        it.thirdProductId = thirdProductId
        it.retailPrice = wholesalePrice
        it.wholesalePrice = wholesalePrice
    }

    fun toShopProduct(storeNo: String) = CtShopProduct().also {
        it.shopNo = storeNo
        it.barcode = barCode
        it.stockDelta = total.toLong()
    }
}

