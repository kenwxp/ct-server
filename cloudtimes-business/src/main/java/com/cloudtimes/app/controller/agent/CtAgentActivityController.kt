package com.cloudtimes.app.controller.agent

import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.dto.request.QueryActivityDetailRequest
import com.cloudtimes.agent.dto.request.QueryActivityRequest
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.agent.domain.CtAgentActivity
import com.cloudtimes.agent.service.ICtAgentActivityService
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

// 泛型具体化
class AgentActivityResponse() : RestPageResult<CtAgentActivity>()
//class Activity1DetailResponse() : RestResult<CtAgentActivity1>()
//class Activity2DetailResponse() : RestResult<CtAgentActivity2>()


/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/agent/activity")
@Api(tags = ["代理-活动"])
class CtAgentActivityController(
    private val agentActivityService: ICtAgentActivityService,
//    private val activity1Service: ICtAgentActivity1Service,
//    private val activity2Service: ICtAgentActivity2Service,
) : BaseController() {

    @PostMapping(value = ["/list"])
    @ApiOperation(value = "查询代理参加的全部活动", response = CtUserAgent::class)
    fun listActivities(@Valid @RequestBody request: QueryActivityRequest): AgentActivityResponse {
        return AgentActivityResponse().apply {
            data = agentActivityService.selectAgentActivity(request)
        }
    }

//    @PostMapping(value = ["/activity1_detail"])
//    @ApiOperation(value = "查询活动1详情", response = CtUserAgent::class)
//    fun activity1Detail(@Valid @RequestBody request: QueryActivityDetailRequest): Activity1DetailResponse {
//        return Activity1DetailResponse().apply {
//            data = activity1Service.selectCtAgentActivity1ById(request.activityId!!)
//        }
//    }
//
//    @PostMapping(value = ["/activity2_detail"])
//    @ApiOperation(value = "查询活动2详情", response = CtUserAgent::class)
//    fun activity2Detail(@Valid @RequestBody request: QueryActivityDetailRequest): Activity2DetailResponse {
//        return Activity2DetailResponse().apply {
//            data = activity2Service.selectCtAgentActivity2ById(request.activityId!!)
//        }
//    }
}
