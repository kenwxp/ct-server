package com.cloudtimes.account.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.util.Date

@Schema(description = "代理成员")
class TeamMember {
    @Schema(description = "用户编号")
    var id: String = ""

    @Schema(description = "电话号码")
    var mobile: String? = null

    @Schema(description = "昵称")
    var nickName: String? = null

    @Schema(description = "真实姓名")
    var realName: String? = null

    @Schema(description = "微信头像")
    var wxAvatar: String? = null

    @Schema(description = "性别")
    var sex: String? = null

    @Schema(description = "注册日期")
    var createDate: Date? = null

    @Schema(description = "代理状态")
    var agentState: String? = null

    @Schema(description = "代理类型")
    var agentType: String? = null

    @Schema(description = "累计销售提成(累计产品销售佣金)")
    var totalSalesReward: BigDecimal? = null

    @Schema(description = "累计分润提成(应收分成)")
    var totalDividend: BigDecimal? = null

    @Schema(description = "累计活动提成(平台活动奖励)")
    var totalActivityReward: BigDecimal? = null
}
