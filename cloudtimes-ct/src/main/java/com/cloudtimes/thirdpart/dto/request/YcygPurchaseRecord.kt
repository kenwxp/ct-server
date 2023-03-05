package com.cloudtimes.thirdpart.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotEmpty

@ApiModel(value = "PurchaseRecord", description = "采购记录")
class YcygPurchaseRecord {
    @ApiModelProperty(value = "生产日期")
    @JsonProperty("scrq")
    var manufacturingDate: String = ""

    @ApiModelProperty(value = "保质截止日期")
    @JsonProperty("bzjzrq")
    var expireDate: String = ""

    @ApiModelProperty(value = "商品类型 是否为赠品 T否 S是")
    @JsonProperty("splx")
    var giftType: String = ""

    @ApiModelProperty(value = "商品条码")
    @JsonProperty("spsmm")
    var barCode: String = ""

    @ApiModelProperty(value = "收银系统商品唯一ID")
    @JsonProperty("spbm")
    var casherProductId: String = ""

    @ApiModelProperty(value = "蓉城易购商品唯一ID")
    @JsonProperty("factorycode")
    @NotEmpty(message = "蓉城易购商品唯一ID")
    var thirdProductId: String = ""

    @ApiModelProperty(value = "商品名称")
    @JsonProperty("spmc")
    var productName: String = ""

    @ApiModelProperty(value = "入库价格")
    @JsonProperty("hsjj")
    var hsjj: String = ""

    @ApiModelProperty(value = "入库数量（基本包装）")
    @JsonProperty("rksl")
    var total: Int = 0

    @ApiModelProperty(value = "特供状态ID(DM特供,00正常)")
    @JsonProperty("cxtype")
    var specialSupplyType: Int = 0
}
