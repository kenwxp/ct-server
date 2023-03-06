package com.cloudtimes.product.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * 第三方商品对象 ct_product_third
 *
 * @author 沈兵
 * @date 2023-03-05
 */
class CtProductThird : BaseEntity() {
    /** 第三方编码  */
    var thirdCode: String? = null

    /** 第三方商品ID  */
    var thirdProductId: String? = null

    /** 商品条码  */
    @Excel(name = "商品条码")
    var barcode: String? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("thirdCode", thirdCode)
            .append("thirdProductId", thirdProductId)
            .append("barcode", barcode)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}