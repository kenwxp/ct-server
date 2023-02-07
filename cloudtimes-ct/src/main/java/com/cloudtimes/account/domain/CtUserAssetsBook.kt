package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 用户资产簿记对象 ct_user_assets_book
 *
 * @author 沈兵
 * @date 2023-02-07
 */
data class CtUserAssetsBook (
    /** 编号  */
    var id: String? = null,

    /** 用户编号  */
    @Excel(name = "用户编号")
    var userId: String? = null,

    /** 卡劵编号  */
    @Excel(name = "卡劵编号")
    var cardId: String? = null,

    /** 簿记类型  */
    @Excel(name = "簿记类型")
    var bookType: String? = null,

    /** 操作类型  */
    @Excel(name = "操作类型")
    var operateType: String? = null,

    /** 资产类型  */
    @Excel(name = "资产类型")
    var assetsType: String? = null,

    /** 操作前额度  */
    @Excel(name = "操作前额度")
    var beforeAmount: BigDecimal? = null,

    /** 操作后额度  */
    @Excel(name = "操作后额度")
    var alterAmount: BigDecimal? = null,

    /** 操作额度  */
    @Excel(name = "操作额度")
    var amount: BigDecimal? = null,

    /** 创建日期  */
    var createDate: Date? = null,

    /** 是否删除  */
    var delFlag: String? = null,
) : BaseEntity() {
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("cardId", cardId)
            .append("bookType", bookType)
            .append("operateType", operateType)
            .append("assetsType", assetsType)
            .append("beforeAmount", beforeAmount)
            .append("alterAmount", alterAmount)
            .append("amount", amount)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .append("delFlag", delFlag)
            .append("remark", remark)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}