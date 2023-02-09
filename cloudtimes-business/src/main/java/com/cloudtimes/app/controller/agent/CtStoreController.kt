package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.dto.request.AgentStoreRequest
import com.cloudtimes.account.service.ICtUserAgentService
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * 用户Controller
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/v1/agent/store")
@Api(tags = ["代理门店管理"])
class CtStoreController : BaseController() {
    @Autowired
    private lateinit var ctUserAgentService: ICtUserAgentService

    /**
     * 查询代理门店
     */
    @ApiOperation("查询代理门店")
    @GetMapping("/list")
    fun list(@Valid agentStoreRequest: AgentStoreRequest): TableDataInfo {
        startPage()
        val list = ctUserAgentService.selectCtAgentShopList(agentStoreRequest)
        return getDataTable(list)
    }

    /**
     * 查询代理门店上线统计
     */
    @ApiOperation("查询代理门店上线统计")
    @GetMapping("/stats")
    fun stats(): AjaxResult {
        // :TODO: 登陆接口好了后从登陆用户中取userId
        val userId = "e4011707-a691-11ed-8957-0242ac110003"
        val list = ctUserAgentService.selectCtAgentShopStats(userId)
        return AjaxResult.success(list)
    }
}