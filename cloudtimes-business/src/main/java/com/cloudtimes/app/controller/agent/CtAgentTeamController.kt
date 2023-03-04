package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.dto.request.QueryBySubUserIdRequest
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import javax.validation.Valid

import com.cloudtimes.account.dto.request.QueryByUserIdWithPageRequest
import com.cloudtimes.account.dto.response.TeamMember
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// 泛型具体化
class TeamMemberPage() : RestPageResult<TeamMember>()
class TeamMemberDetail() : RestResult<TeamMember>()

/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/team")
@Api(tags = ["代理-团队"])
class CtAgentTeamController : BaseController() {
    @Autowired
    private lateinit var agentService: ICtUserAgentService

    @Autowired
    private lateinit var userService: ICtUserService

    @PostMapping(value = ["/list_members"])
    @ApiOperation(value = "查询团队成员列表")
    fun listMembers(@Valid @RequestBody request: QueryByUserIdWithPageRequest): TeamMemberPage {
        startPage(request.pageNum, request.pageSize)
        val members = agentService.selectTeamMembers(request.userId!!)
        val pageData = getDataTable(members)
        return TeamMemberPage().apply {
            total = pageData.total
            data = members
        }
    }

    @PostMapping(value = ["/member_detail"])
    @ApiOperation(value = "查询团队成员详情")
    fun memberDetail(@Valid @RequestBody request: QueryBySubUserIdRequest): TeamMemberDetail {
        val member = agentService.selectTeamMember(request.subUserId)
        return TeamMemberDetail().apply {
            data = member
        }
    }

    @PostMapping(value = ["/invite_agent"])
    @ApiOperation("获取团队拓展邀请码和邀请地址")
    fun inviteAgent(@Valid @RequestBody request: QueryByUserIdRequest): InviteAgentResponse {
        return InviteAgentResponse().apply {
            data = userService.inviteAgent(request)
        }
    }

    @PostMapping(value = ["/invite_store"])
    @ApiOperation("获取门店拓展邀请码和邀请地址")
    fun inviteStore(@Valid @RequestBody request: QueryByUserIdRequest): InviteStoreResponse {
        return InviteStoreResponse().apply {
            data = userService.inviteStore(request)
        }
    }
}
