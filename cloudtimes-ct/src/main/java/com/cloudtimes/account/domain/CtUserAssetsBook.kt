package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import io.swagger.annotations.ApiModelProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 用户资产簿记对象 ct_user_assets_book
 *
 * @author 沈兵
 * @date 2023-02-07
 */
open class CtUserAssetsBook : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "用户编号")
    @Excel(name = "用户编号")
    var userId: String? = null

    @ApiModelProperty(value = "卡劵编号")
    @Excel(name = "卡劵编号")
    var cardId: String? = null

    @ApiModelProperty(value = "簿记类型")
    @Excel(name = "簿记类型")
    var bookType: String? = null

    @ApiModelProperty(value = "操作类型")
    @Excel(name = "操作类型")
    var operateType: String? = null

    @ApiModelProperty(value = "资产类型")
    @Excel(name = "资产类型")
    var assetsType: String? = null

    @ApiModelProperty(value = "操作前额度")
    @Excel(name = "操作前额度")
    var beforeAmount: BigDecimal? = null

    @ApiModelProperty(value = "操作后额度")
    @Excel(name = "操作后额度")
    var alterAmount: BigDecimal? = null

    @ApiModelProperty(value = "操作额度")
    @Excel(name = "操作额度")
    var amount: BigDecimal? = null

    @ApiModelProperty(value = "手续费费率")
    @Excel(name = "手续费费率")
    var taxRatio: BigDecimal? = null

    @ApiModelProperty(value = "手续费金额")
    @Excel(name = "手续费金额")
    var taxAmount: BigDecimal? = null

    @ApiModelProperty(value = "稅前金额")
    @Excel(name = "稅前金额")
    var beforeTaxAmount: BigDecimal? = null

    @ApiModelProperty(value = "转账ID")
    @Excel(name = "转账ID")
    var transferId: String? = null

    @ApiModelProperty(value = "年月")
    var yearMonth: Int? = null

    @ApiModelProperty(value = "创建日期")
    var createDate: LocalDate? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

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
            .append("yearMonth", yearMonth)
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
