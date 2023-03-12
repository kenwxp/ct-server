package com.cloudtimes.agent.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "代理店铺详情")
class AgentStoreDetail {
    @Schema(description = "活动类型")
    var activityType: String? = null

    @Schema(description = "活动规则编号")
    var activityRuleId: String? = null

    @Schema(description = "门店编号")
    var id: String? = null

    @Schema(description = "店铺名称")
    var name: String? = null

    @Schema(description = "详细地址")
    var address: String? = null

    @Schema(description = "短地址")
    var shortAddress: String? = null

    @Schema(description = "开设状态")
    var buildState: String? = null

    @Schema(description = "门店状态")
    var state: String? = null

    @Schema(description = "门店上线日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var storeOnlineDate: LocalDate? = null

    @Schema(description = "门店地区")
    var regionCode: String? = null

    @Schema(description = "预计活动达成日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var fulfilDate: LocalDate? = null

    @Schema(description = "门店代理昵称")
    var nickName: String? = null

    @Schema(description = "门店代理实名")
    var realName: String? = null

    @Schema(description = "门店代理头像")
    var wxAvatar: String? = null
}
