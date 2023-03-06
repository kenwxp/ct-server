package com.cloudtimes.web.controller.agent

import com.cloudtimes.account.domain.CtUser
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
    private lateinit var ctUserAgentService: ICtUserAgentService

    @Autowired
    private lateinit var ctUserService: ICtUserService

//    /**
//     * 查询代理列表
//     */
//    @PreAuthorize("@ss.hasPermi('agent:agent:list')")
//    @GetMapping("/list")
//    fun list(ctUserAgent: CtUserAgent): TableDataInfo {
//        startPage()
//        val list: List<CtUserAgent?> = ctUserAgentService.selectCtUserAgentList(ctUserAgent)
//        return getDataTable(list)
//    }

    /**
     * 查询用户列表
     */
   // @PreAuthorize("@ss.hasPermi('agent:agent:agentUserlist')")
    @GetMapping("/agentUserlist")
    fun listUser(ctUser: CtUser?): TableDataInfo? {
        startPage()
        val list: List<CtUser?> = ctUserService.selectCtUserList(ctUser!!)
        return getDataTable(list)
    }


    /**
     * 获取代理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('agent:agent:query')")
    @GetMapping(value = ["/getAgnetUser/{userId}"])
    fun getAgnetUser(@PathVariable("userId") userId: String): AjaxResult {
        return AjaxResult.success(ctUserService.selectCtUserById(userId))
    }


    /**
     * 导出代理列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:export')")
    @Log(title = "代理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctUserAgent: CtUserAgent) {
        val list = ctUserAgentService.selectCtUserAgentList(ctUserAgent)
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
        return AjaxResult.success(ctUserAgentService.selectCtUserAgentByUserId(userId))
    }

    /**
     * 新增代理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:add')")
    @Log(title = "代理", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctUserAgent: CtUserAgent): AjaxResult {
        return toAjax(ctUserAgentService.insertCtUserAgent(ctUserAgent))
    }


  //  @PreAuthorize("@ss.hasPermi('agent:agent:edit')")
    @Log(title = "代理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateAgnetUser")
    fun updateAgnetUser(@RequestBody ctUser: CtUser): AjaxResult? {
        if (AgentState.None.code != ctUser.agentState) {
            ctUser.createAgentTime = DateUtils.getNowDate()
        }
        return toAjax(ctUserService.updateCtUser(ctUser))
    }

//    /**
//     * 修改代理
//     */
//    @PreAuthorize("@ss.hasPermi('agent:agent:edit')")
//    @Log(title = "代理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    fun edit(@RequestBody ctUserAgent: CtUserAgent): AjaxResult {
//        return toAjax(ctUserAgentService.updateCtUserAgent(ctUserAgent))
//    }
}