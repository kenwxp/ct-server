package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtUserAgent
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.dto.response.AgentActivity
import com.cloudtimes.account.service.ICtAgentActivityService
import com.cloudtimes.common.core.controller.BaseController
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

// 由于泛型没有推断出类型来，需要单独定义出返回类型来给swagger使用
// 原因是 ApiResponse 的 response 只支持 Class类型
class AgentActivityResponse(override var data: AgentActivity?) : RestResult<AgentActivity>(data)

/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/v1/agent/activity")
@Api(tags = ["代理活动"])
class CtAgentActivityController : BaseController() {
    @Autowired
    private lateinit var agentActivityService: ICtAgentActivityService

    @PostMapping(value = ["/list"])
    @ApiOperation(value = "查询代理参加的全部活动", response = CtUserAgent::class)
    fun listActivities(@Valid @RequestBody request: QueryByUserIdRequest): AgentActivityResponse {
        // :TODO: 替换请求用户
        // val userId = "e4011707-a691-11ed-8957-0242ac110003"
        val userId = request.userId!!
        return AgentActivityResponse(agentActivityService.selectAgentActivity(userId))
    }
}