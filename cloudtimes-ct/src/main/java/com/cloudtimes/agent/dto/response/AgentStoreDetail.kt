package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(value = "AgentStoreDetail", description = "代理店铺详情")
class AgentStoreDetail {
    @ApiModelProperty(value = "活动类型")
    var activityType: String? = null

    @ApiModelProperty(value = "活动规则编号")
    var activityRuleId: String? = null

    @ApiModelProperty(value = "门店编号")
    var id: String? = null

    @ApiModelProperty(value = "店铺名称")
    var name: String? = null

    @ApiModelProperty(value = "详细地址")
    var address: String? = null

    @ApiModelProperty(value = "短地址")
    var shortAddress: String? = null

    @ApiModelProperty(value = "开设状态")
    var buildState: String? = null

    @ApiModelProperty(value = "门店状态")
    var state: String? = null

    @ApiModelProperty(value = "门店上线日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var storeOnlineDate: LocalDate? = null

    @ApiModelProperty(value = "门店地区")
    var regionCode: String? = null

    @ApiModelProperty(value = "预计活动达成日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var fulfilDate: LocalDate? = null

    @ApiModelProperty(value = "门店代理昵称")
    var nickName: String? = null

    @ApiModelProperty(value = "门店代理实名")
    var realName: String? = null

    @ApiModelProperty(value = "门店代理头像")
    var wxAvatar: String? = null
}