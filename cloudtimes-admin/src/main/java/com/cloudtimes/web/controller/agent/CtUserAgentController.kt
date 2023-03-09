package com.cloudtimes.web.controller.agent

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.response.CtUserDto
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.AgentState
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.common.utils.poi.ExcelUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/agent/agent")
class CtUserAgentController : BaseController() {
    @Autowired
    private lateinit var userAgentService: ICtUserAgentService

    @Autowired
    private lateinit var userService: ICtUserService

    /**
     * 查询用户列表
     */
    // @PreAuthorize("@ss.hasPermi('agent:agent:agentUserlist')")
    @GetMapping("/agentUserlist")
    fun listUser(ctUser: CtUser?): TableDataInfo? {
        startPage()
        val list: List<CtUserDto?> = userService.selectCtUserListPlus(ctUser!!)
        return getDataTable(list)
    }


    /**
     * 获取代理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('agent:agent:query')")
    @GetMapping(value = ["/getAgentUser/{userId}"])
    fun getAgentUser(@PathVariable("userId") userId: String): AjaxResult {
        return AjaxResult.success(userService.selectCtUserById(userId))
    }


    /**
     * 导出代理列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:export')")
    @Log(title = "代理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctUserAgent: CtUserAgent) {
        val list = userAgentService.selectCtUserAgentList(ctUserAgent)
        val util = ExcelUtil(
            CtUserAgent::class.java
        )
        util.exportExcel(response, list, "代理数据")
    }

    /**
     * 获取代理详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:query')")
    @GetMapping(value = ["/{userId}"])
    fun getInfo(@PathVariable("userId") userId: String): AjaxResult {
        return AjaxResult.success(userAgentService.selectCtUserAgentByUserId(userId))
    }

    /**
     * 新增代理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:add')")
    @Log(title = "代理", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctUserAgent: CtUserAgent): AjaxResult {
        return toAjax(userAgentService.insertCtUserAgent(ctUserAgent))
    }


    //  @PreAuthorize("@ss.hasPermi('agent:agent:edit')")
    @Log(title = "代理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateAgentUser")
    fun updateAgentUser(@RequestBody ctUser: CtUser): AjaxResult? {
        if (AgentState.None.code != ctUser.agentState) {
            ctUser.createAgentTime = DateUtils.getNowDate()
        }
        return toAjax(userAgentService.updateAgentStatus(ctUser))
    }
}