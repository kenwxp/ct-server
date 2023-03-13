package com.cloudtimes.promotion.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal

/**
 * 幸运大抽奖规则对象 ct_lucky_draw_rule
 *
 * @author tank
 * @date 2023-03-08
 */
@Schema(description = "抽奖规则")
class CtLuckyDrawRule : BaseEntity() {
    @Schema(description = "规则编号")
    var id: String? = null

    @Schema(description = "活动编号")
    @Excel(name = "活动编号")
    var activityId: String? = null

    @Schema(description = "顺序号,排序用")
    @Excel(name = "顺序号,排序用")
    var seqno: Long? = null

    @Schema(description = "奖项图片")
    @Excel(name = "奖项图片")
    var pic: String? = null

    @Schema(description = "中奖几率,1表示100%中奖,0.90表示90%中奖")
    @Excel(name = "中奖几率")
    var winRatio: BigDecimal? = null

    @Schema(description = "奖项名称")
    @Excel(name = "奖项名称")
    var name: String? = null

    @Schema(description = "奖励金额")
    @Excel(name = "奖励金额")
    var reward: BigDecimal? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("activityId", activityId)
            .append("seqno", seqno)
            .append("pic", pic)
            .append("winRatio", winRatio)
            .append("name", name)
            .append("reward", reward)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
