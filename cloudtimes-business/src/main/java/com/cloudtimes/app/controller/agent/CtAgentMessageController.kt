package com.cloudtimes.app.controller.agent

import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.account.dto.request.ListMessageByTypeRequest
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
class AgentMessageListRequest(var pageNum: Int = 1, var pageSize: Int = 10) : QueryByUserIdRequest()

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

    @PostMapping(value = ["/summary"])
    @ApiOperation(value = "查询代理的消息摘要", response = CtUserAgent::class)
    fun summary(@Valid @RequestBody request: AgentMessageListRequest): AgentMessagePage {
        val messages = eventsService.selectSummaryByReceiver(request.userId)
        return AgentMessagePage().apply {
            total = messages.size.toLong()
            data = messages
        }
    }

    @PostMapping(value = ["/list_by_type"])
    @ApiOperation(value = "按消息类型查询代理消息列表", response = CtUserAgent::class)
    fun listMessageByType(@Valid @RequestBody request: ListMessageByTypeRequest): AgentMessagePage {
        startPage(request.pageNum, request.pageSize)
        val messages = eventsService.selectByReceiverAndMsgType(request.userId, request.msgType!!)
        val pageData = getDataTable(messages)
        return AgentMessagePage().apply {
            total = pageData.total
            data = messages
        }
    }

    @PostMapping(value = ["/list"])
    @ApiOperation(value = "查询代理的消息列表", response = CtUserAgent::class)
    fun listMessage(@Valid @RequestBody request: AgentMessageListRequest): AgentMessagePage {
        startPage(request.pageNum, request.pageSize)
        val messages = eventsService.selectByReceiver(request.userId)
        val pageData = getDataTable(messages)
        return AgentMessagePage().apply {
            total = pageData.total
            data = messages
        }
    }
}
