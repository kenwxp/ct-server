package com.cloudtimes.promotion.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * 用户中奖对象 ct_user_draw_rel
 *
 * @author tank
 * @date 2023-03-08
 */
class CtUserDrawRel : BaseEntity() {
    /** 用户编号  */
    var userId: String? = null

    /** 活动编号  */
    @Excel(name = "活动编号")
    var activityId: String? = null

    /** 中奖规则编号  */
    @Excel(name = "中奖规则编号")
    var drawRuleId: String? = null

    /** 奖金兑换状态  */
    @Excel(name = "奖金兑换状态")
    var exchangeState: String? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", userId)
            .append("activityId", activityId)
            .append("drawRuleId", drawRuleId)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}