package com.cloudtimes.app.controller.mp

import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.domain.CtUserDrawRel
import com.cloudtimes.promotion.dto.request.DrawWinRequest
import com.cloudtimes.promotion.dto.response.UserDrawState
import com.cloudtimes.promotion.service.ICtLuckyDrawRuleService
import com.cloudtimes.promotion.service.ICtUserDrawRelService
import com.cloudtimes.system.service.ISysConfigService
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

class DrawRuleListResponse() : RestPageResult<CtLuckyDrawRule>()
class QueryDrawResponse(): RestResult<UserDrawState>()

@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/customer/draw")
@Tag(name = "客户-抽奖")
class CtCustomerDrawController : BaseController() {
    @Autowired
    private lateinit var ruleService: ICtLuckyDrawRuleService

    @Autowired
    private lateinit var configService: ISysConfigService

    @Autowired
    private lateinit var userDrawService: ICtUserDrawRelService

    @PostMapping("/rules")
    @Operation(summary = "查询抽奖规则配置")
    fun rules(@Valid @RequestBody request: QueryByUserIdRequest): DrawRuleListResponse {
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


    @PostMapping("/exchange")
    @Operation(summary = "中奖兑换")
    fun exchange(@Valid @RequestBody request: DrawWinRequest): RestResult<Any> {
        val existRule = ruleService.selectCtLuckyDrawRuleById(request.ruleId) ?:
            return RestResult<Any>().apply {
                code = HttpStatus.BAD_REQUEST.value()
                msg = "中奖规则不存在"
            }

        val existDraw = userDrawService.selectCtUserDrawRelById(existRule.activityId!!, request.userId)
        if (existDraw != null) {
            return RestResult<Any>().apply {
                code = HttpStatus.BAD_REQUEST.value()
                msg = "用户已经抽过将，不能再次抽奖"
            }
        }

        val newDraw = CtUserDrawRel().apply {
            activityId = existRule.activityId
            drawRuleId = existRule.id
            userId = request.userId
        }

        // :TODO: 发起兑奖交易
        userDrawService.insertCtUserDrawRel(newDraw)

        return RestResult()
    }

    @PostMapping("/query")
    @Operation(summary = "查询用户是否抽过将")
    fun query(@Valid @RequestBody request: QueryByUserIdRequest): QueryDrawResponse {
        val activityId: String? = configService.selectConfigByKey("lucky_draw_activity_id")
        if (activityId.isNullOrEmpty()) {
            return QueryDrawResponse().apply {
                code = HttpStatus.INTERNAL_SERVER_ERROR.value()
                msg = "抽奖活动参数未配置!"
            }
        }

        val existDraw = userDrawService.selectCtLuckyDrawRuleById(activityId, request.userId)
        val drawState = UserDrawState().apply{
            isAlreadyDraw = existDraw != null
            winRule = existDraw
        }

        return QueryDrawResponse().apply {
            data = drawState
        }
    }
}
