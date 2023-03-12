package com.cloudtimes.app.controller.mp

import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.service.ICtSuggestionService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.service.ICtLuckyDrawRuleService
import com.cloudtimes.system.service.ISysConfigService
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

class DrawRuleListResponse() : RestPageResult<CtLuckyDrawRule>()

@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/customer/draw")
@Tag(name = "客户-抽奖")
class CtCustomerDrawController : BaseController() {
    @Autowired
    private lateinit var ruleService: ICtLuckyDrawRuleService

    @Autowired
    private lateinit var ctSuggestionService: ICtSuggestionService

    @Autowired
    private lateinit var configService: ISysConfigService
    @PostMapping("/rules")
    @Operation(summary = "查询抽奖规则配置")
    fun rules( request: QueryByUserIdRequest): DrawRuleListResponse {
        val activityId: String? = configService.selectConfigByKey("lucky_draw_activity_id")
        if (activityId.isNullOrEmpty()) {
            throw ServiceException("抽奖活动参数未配置!")
        }

        val ruleList = ruleService.selectCtLuckyDrawRuleListByActivityId(
            activityId
        )

        return DrawRuleListResponse().apply {
            data = ruleList
            total = ruleList.size.toLong()
        }
    }
}
