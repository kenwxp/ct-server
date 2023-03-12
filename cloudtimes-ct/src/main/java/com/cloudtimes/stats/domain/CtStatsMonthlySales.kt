package com.cloudtimes.stats.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

/**
 * 门店月销售统计对象 ct_stats_monthly_sales
 *
 * @author 沈兵
 * @date 2023-02-09
 */
@Schema(description = "门店销售统计")
class CtStatsMonthlySales : BaseEntity() {
    /** 店铺编号  */
    @field:NotBlank(message = "门店编号不能为空")
    @field:NotEmpty(message = "门店编号不能为空")
    var storeId:  String? = null

    /** 年月  */
    var yearMonth: Int? = null

    /** 累计销售笔数  */
    @Excel(name = "累计销售笔数")
    var orderCount: Long? = null

    /** 累计销售金额  */
    @Excel(name = "累计销售金额")
    var salesMoneyTotals: BigDecimal? = null

    /** 日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    /** 是否删除  */
    var delFlag: String? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("storeId", storeId)
            .append("yearMonth", yearMonth)
            .append("orderCount", orderCount)
            .append("salesMoneyTotals", salesMoneyTotals)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .append("delFlag", delFlag)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
