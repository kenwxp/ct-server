package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

/**
 * 转账登记簿对象 ct_transfer_book
 *
 * @author 沈兵
 * @date 2023-02-16
 */
@Schema(description = "转账登记簿")
class CtTransferBook {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "付款人")
    @Excel(name = "付款人")
    var payer: String? = null

    @Schema(description = "收款人")
    @Excel(name = "收款人")
    var payee: String? = null

    @Schema(description = "转账金额")
    @Excel(name = "转账金额")
    var amount: BigDecimal? = null

    @Schema(description = "簿记年月")
    @Excel(name = "簿记年月")
    var yearMonth: Int? = null

    @Schema(description = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDate? = null

    @Schema(description = "备注")
    var remark: String? = null;

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("payer", payer)
            .append("payee", payee)
            .append("amount", amount)
            .append("yearMonth", yearMonth)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("remark", remark)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
