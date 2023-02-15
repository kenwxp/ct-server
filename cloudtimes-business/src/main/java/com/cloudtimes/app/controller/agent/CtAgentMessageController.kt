package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtUserAgent
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.supervise.domain.CtEvents
import com.cloudtimes.supervise.service.ICtEventsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

// 泛型具体化
class AgentMessagePage() : RestPageResult<CtEvents>()

@ApiModel(value = "AgentMessageListRequest", description = "查询代理的消息列表请求体")
class AgentMessageListRequest(var pageNum: Int = 1, var pageSize: Int = 2) : QueryByUserIdRequest()

/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/agent/message")
@Api(tags = ["代理-消息"])
class CtAgentMessageController : BaseController() {
    @Autowired
    private lateinit var eventsService: ICtEventsService

    @PostMapping(value = ["/list"])
    @ApiOperation(value = "查询代理的消息列表", response = CtUserAgent::class)
    fun listMessages(@Valid @RequestBody request: AgentMessageListRequest): AgentMessagePage {
        startPage(request.pageNum, request.pageSize)
        val messages = eventsService.selectEventsByReceiver(request.userId!!)
        val pageData = getDataTable(messages)
        return AgentMessagePage().apply {
            total = pageData.total
            rows = messages
        }
    }
}
