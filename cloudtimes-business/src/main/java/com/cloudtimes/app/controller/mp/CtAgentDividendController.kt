package com.cloudtimes.app.controller.mp

import com.cloudtimes.agent.domain.CtAgentDividend
import com.cloudtimes.account.dto.request.QueryBySubUserIdRequest
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.agent.dto.request.UpdateSubAgentDividendRequest
import com.cloudtimes.agent.service.ICtAgentDividendService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestPageResult
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

class AgentDividendPage() : RestPageResult<CtAgentDividend>()

/**
 * 分润配置Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/agent_dividend")
@Tag(name = "代理-分润配置")
class CtAgentDividendController : BaseController() {
    @Autowired
    private lateinit var ctAgentDividendService: ICtAgentDividendService

    @PostMapping("/list")
    @Operation(summary = "查询代理分润配置列表")
    fun list(@Valid @RequestBody request: QueryByUserIdRequest): AgentDividendPage {
        val list = ctAgentDividendService.selectManyByUserId(request.userId)
        return AgentDividendPage().apply {
                data = list
                total = list.size.toLong()
        }
    }

    @PostMapping("/sub_user_list")
    @Operation(summary = "查询下级代理分润配置列表")
    fun suerUserList(@Valid @RequestBody request: QueryBySubUserIdRequest): AgentDividendPage {
        val list = ctAgentDividendService.selectManyByUserId(request.subUserId)
        return AgentDividendPage().apply {
            data = list
            total = list.size.toLong()
        }
    }

    @PostMapping("/update_sub_agent_dividend")
    @Operation(summary = "更新下级代理分润配置")
    fun updateSubAgentDividend(@Valid @RequestBody request: UpdateSubAgentDividendRequest): AjaxResult {
        ctAgentDividendService.updateSubAgentDividend(request)
        return AjaxResult.success()
    }
}
