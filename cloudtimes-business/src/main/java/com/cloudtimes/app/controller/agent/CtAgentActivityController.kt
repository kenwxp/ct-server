package com.cloudtimes.app.controller.agent

import com.cloudtimes.agent.domain.CtAgentActivity
import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.dto.request.ActivityListRequest
import com.cloudtimes.agent.dto.request.ActivityRuleRequest
import com.cloudtimes.agent.dto.request.ActivityStoreRequest
import com.cloudtimes.agent.dto.response.AgentActivity1Detail
import com.cloudtimes.agent.dto.response.AgentActivity2Detail
import com.cloudtimes.agent.dto.response.AgentStoreDetail
import com.cloudtimes.agent.service.ICtAgentActivity1RuleService
import com.cloudtimes.agent.service.ICtAgentActivity2RuleService
import com.cloudtimes.agent.service.ICtAgentActivityService
import com.cloudtimes.agent.service.ICtAgentActivitySettlementService
import com.cloudtimes.app.controller.auth.UserLoginAndRegisterController
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

// 泛型具体化
class AgentActivityListResponse() : RestPageResult<CtAgentActivity>()
class AgentActivityResponse() : RestResult<CtAgentActivity>()
class AgentActivity1ListResponse() : RestPageResult<AgentActivity1Detail>()
class AgentActivity2ListResponse() : RestPageResult<AgentActivity2Detail>()
class ActivityStoreListResponse() : RestPageResult<AgentStoreDetail>()


/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/agent/activity")
@Api(tags = ["代理-活动"])
class CtAgentActivityController : BaseController() {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var activityService: ICtAgentActivityService

    @Autowired
    private lateinit var activity1RuleService: ICtAgentActivity1RuleService

    @Autowired
    private lateinit var activity2RuleService: ICtAgentActivity2RuleService

    @Autowired
    private lateinit var settlementService: ICtAgentActivitySettlementService

    @PostMapping(value = ["/list"])
    @ApiOperation(value = "查询代理参加的全部活动")
    fun listActivities(@Valid @RequestBody request: ActivityListRequest): AgentActivityListResponse {
        return AgentActivityListResponse().apply {
            data = activityService.selectAgentActivity(request)
        }
    }

    @PostMapping(value = ["/detail"])
    @ApiOperation(value = "查询活动详情")
    fun activityDetail(@Valid @RequestBody request: ActivityDetailRequest): AgentActivityResponse {
        return AgentActivityResponse().apply {
            data = activityService.selectActivityById(request.activityId!!)
        }
    }

    @PostMapping(value = ["/list_activity1"])
    @ApiOperation(value = "查询活动1规则完成情况")
    fun listActivity1(@Valid @RequestBody request: ActivityDetailRequest): AgentActivity1ListResponse {
        return AgentActivity1ListResponse().apply {
            data = activity1RuleService.listAgentActivityDetail(request)
        }
    }

    @PostMapping(value = ["/confirm_activity1"])
    @ApiOperation(value = "代理确认活动1规则达成")
    fun confirmActivity1(@Valid @RequestBody request: ActivityRuleRequest): AjaxResult {
        settlementService.agentConfirm(request)
        return AjaxResult.success()
    }

    @PostMapping(value = ["/list_activity2"])
    @ApiOperation(value = "查询活动2规则完成情况")
    fun listActivity2(@Valid @RequestBody request: ActivityDetailRequest): AgentActivity2ListResponse {
        return AgentActivity2ListResponse().apply {
            data = activity2RuleService.listAgentActivityDetail(request)
        }
    }

    @PostMapping(value = ["/list_activity_store"])
    @ApiOperation(value = "查询满足活动规则的店铺")
    fun listActivityStore(@Valid @RequestBody request: ActivityStoreRequest) : ActivityStoreListResponse {
        logger.info("list_activity_store: pageNum ${request.pageNum}, pageSize ${request.pageSize}")
        startPage(request.pageNum, request.pageSize)
        val stores = activityService.selectActivityStores(request)
        val page = getDataTable(stores)
        return ActivityStoreListResponse().apply {
            data = stores
            total = page.total
        }
    }
}
