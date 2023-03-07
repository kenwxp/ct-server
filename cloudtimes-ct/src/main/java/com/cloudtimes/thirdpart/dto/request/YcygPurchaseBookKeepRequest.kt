package com.cloudtimes.thirdpart.dto.request

import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.hardwaredevice.domain.CtStore
import com.cloudtimes.product.domain.CtShopPurchase
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.Date
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "PurchaseBookKeepRequest", description = "采购入库请求")
class YcygPurchaseBookKeepRequest {
    @ApiModelProperty(value = "入库单号", required = true)
    @field:NotEmpty(message =  "入库单号不能为空")
    @JsonProperty("djbh")
    var orderId: String = ""

    @ApiModelProperty(value = "入库门店", required = true)
    @JsonProperty("customerid")
    @field:NotEmpty(message =  "入库门店不能为空")
    @field:NotNull(message =  "入库门店不能为空")
    var customerId: String = ""

    @ApiModelProperty(value = "备注说明")
    var remark: String = ""

    @ApiModelProperty(value = "商品列表")
    @field:NotEmpty(message = "商品列表不能为空")
    var list: List<YcygPurchaseRecord> = emptyList()

    override fun toString(): String {
        return "YcygPurchaseBookKeepRequest(orderId='$orderId', customerId='$customerId', remark='$remark', list=$list)"
    }

    fun toStorePurchase(store: CtStore): CtShopPurchase {
        return CtShopPurchase().also {
            it.shopNo = store.storeNo
            it.supplier = ChannelType.THIRD_RCYG.code
            it.orderingNumber = orderId
            it.takeTime = Date()
        }
    }


}