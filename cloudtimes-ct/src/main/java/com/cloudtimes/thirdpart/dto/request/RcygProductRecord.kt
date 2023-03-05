package com.cloudtimes.thirdpart.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

@ApiModel(value = "RcygProductRecord", description = "蓉城易购商品记录")
class RcygProductRecord {
    @ApiModelProperty(value = "商品条码")
    @JsonProperty("smallLineCode")
    var barCode: String = ""

    @ApiModelProperty(value = "蓉城易购商品唯一ID")
    @JsonProperty("goodsId")
    @NotEmpty(message = "蓉城易购商品唯一ID")
    var thirdProductId: String = ""

    @ApiModelProperty(value = "商品名称")
    @JsonProperty("goodName")
    var productName: String = ""

    @ApiModelProperty(value = "规格")
    @JsonProperty("goodsModel")
    var specification: String = ""

    @ApiModelProperty(value = "单位")
    @JsonProperty("smallUnit")
    var unit: String = ""

    @ApiModelProperty(value = "给商户的供货价格")
    @JsonProperty("smallPrice")
    var retailPrice: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "批发价格")
    @JsonProperty("scPrice")
    var wholeSalePrice: BigDecimal = BigDecimal("0.00")

    @ApiModelProperty(value = "物流包装数")
    @JsonProperty("packingAmount")
    var packingTotal: String = ""

    @ApiModelProperty(value = "是否上架, Y: 上架, N: 下架")
    @JsonProperty("isGrounding")
    var isAvailable: String = ""

    @ApiModelProperty(value = "创建时间")
    @JsonProperty("createTime")
    var createTime: String = ""

    @ApiModelProperty(value = "保质期天数")
    @JsonProperty("shelfLifeDays")
    var lifeSpanDays: Int = 0
}