package com.cloudtimes.promotion.dto.response

import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "用户抽奖状态")
class UserDrawState {
    @Schema(description = "是否抽过奖")
    var isAlreadyDraw: Boolean = false

    @Schema(description = "中奖规则")
    var winRule: CtLuckyDrawRule? = null
}