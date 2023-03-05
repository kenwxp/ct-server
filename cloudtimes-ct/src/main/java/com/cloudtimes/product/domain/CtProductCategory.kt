package com.cloudtimes.product.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * 商品分类对象 ct_product_category
 *
 * @author 沈兵
 * @date 2023-03-05
 */
class CtProductCategory : BaseEntity() {
    /** 商品分类编号  */
    var categoryCode: String? = null

    /** 分类层级  */
    @Excel(name = "分类层级")
    var tier: String? = null

    /** 分类名称  */
    @Excel(name = "分类名称")
    var categoryName: String? = null

    /** 备注  */
    @Excel(name = "备注")
    var comment: String? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryCode", categoryCode)
            .append("tier", tier)
            .append("categoryName", categoryName)
            .append("comment", comment)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}