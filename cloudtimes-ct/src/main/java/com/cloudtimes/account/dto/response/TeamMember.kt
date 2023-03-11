package com.cloudtimes.account.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.util.Date

@ApiModel(value = "TeamMember", description = "代理成员")
class TeamMember {
    @ApiModelProperty(value = "用户编号")
    var id: String = ""

    @ApiModelProperty(value = "电话号码")
    var mobile: String? = null

    @ApiModelProperty(value = "昵称")
    var nickName: String? = null

    @ApiModelProperty(value = "真实姓名")
    var realName: String? = null

    @ApiModelProperty(value = "微信头像")
    var wxAvatar: String? = null

    @ApiModelProperty(value = "性别")
    var sex: String? = null

    @ApiModelProperty(value = "注册日期")
    var createDate: Date? = null

    @ApiModelProperty(value = "代理状态")
    var agentState: String? = null

    @ApiModelProperty(value = "代理类型")
    var agentType: String? = null

    @ApiModelProperty(value = "累计销售提成(累计产品销售佣金)")
    var totalSalesReward: BigDecimal? = null

    @ApiModelProperty(value = "累计分润提成(应收分成)")
    var totalDividend: BigDecimal? = null

    @ApiModelProperty(value = "累计活动提成(平台活动奖励)")
    var totalActivityReward: BigDecimal? = null
}
