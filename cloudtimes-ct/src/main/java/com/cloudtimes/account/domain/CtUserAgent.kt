package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 代理对象 ct_user_agent
 *
 * @author 沈兵
 * @date 2023-02-07
 */
class CtUserAgent : BaseEntity() {
    /** 用户编号  */
    var userId: String? = null

    /** 用户昵称  */
    @Excel(name = "用户昵称")
    var nickName: String? = null

    /** 代理类型  */
    @Excel(name = "代理类型")
    var agentType: String? = null

    /** 上级代理编号  */
    @Excel(name = "上级代理编号")
    var parentUserId: String? = null

    /** 代理区域  */
    @Excel(name = "代理区域")
    var agentArea: String? = null

    /** 代理现金余额  */
    @Excel(name = "代理现金余额")
    var cashAmount: BigDecimal? = null

    /** 代理累计已提现  */
    @Excel(name = "代理累计已提现")
    var totalWithdrawal: BigDecimal? = null

    /** 累计销售提成(累计产品销售佣金)  */
    @Excel(name = "累计销售提成(累计产品销售佣金)")
    var totalSalesReward: BigDecimal? = null

    /** 累计分润提成(应收分成)  */
    @Excel(name = "累计分润提成(应收分成)")
    var totalDividend: BigDecimal? = null

    /** 累计活动提成(平台活动奖励)  */
    @Excel(name = "累计活动提成(平台活动奖励)")
    var totalActivityReward: BigDecimal? = null

    /** 累计下级贡献提成  */
    @Excel(name = "累计下级贡献提成")
    var totalTributes: BigDecimal? = null

    /** 累计下级贡献分润 */
    val totalTributesDividend: BigDecimal? = null

    /** 累计下级贡献佣金 */
    val totalTributesCommission: BigDecimal? = null

    /** 创建日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    /** 是否删除  */
    var delFlag: String? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", userId)
            .append("nickName", nickName)
            .append("agentType", agentType)
            .append("parentUserId", parentUserId)
            .append("agentArea", agentArea)
            .append("cashAmount", cashAmount)
            .append("totalWithdrawal", totalWithdrawal)
            .append("totalSalesReward", totalSalesReward)
            .append("totalDividend", totalDividend)
            .append("totalActivityReward", totalActivityReward)
            .append("totalTributes", totalTributes)
            .append("remark", remark)
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