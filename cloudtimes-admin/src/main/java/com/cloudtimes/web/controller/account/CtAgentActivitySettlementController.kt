package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtAgentActivitySettlement
import com.cloudtimes.account.service.ICtAgentActivitySettlementService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 代理活动结算Controller
 *
 * @author 沈兵
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/account/activity_settlement")
class CtAgentActivitySettlementController : BaseController() {
    @Autowired
    private val ctAgentActivitySettlementService: ICtAgentActivitySettlementService? = null

    /**
     * 查询代理活动结算列表
     */
    @PreAuthorize("@ss.hasPermi('account:activity_settlement:list')")
    @GetMapping("/list")
    fun list(ctAgentActivitySettlement: CtAgentActivitySettlement?): TableDataInfo {
        startPage()
        val list: List<CtAgentActivitySettlement?> =
            ctAgentActivitySettlementService!!.selectCtAgentActivitySettlementList(
                ctAgentActivitySettlement!!
            )
        return getDataTable(list)
    }

    /**
     * 导出代理活动结算列表
     */
    @PreAuthorize("@ss.hasPermi('account:activity_settlement:export')")
    @Log(title = "代理活动结算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse?, ctAgentActivitySettlement: CtAgentActivitySettlement?) {
        val list = ctAgentActivitySettlementService!!.selectCtAgentActivitySettlementList(
            ctAgentActivitySettlement!!
        )
        val util = ExcelUtil(
            CtAgentActivitySettlement::class.java
        )
        util.exportExcel(response, list, "代理活动结算数据")
    }

    /**
     * 获取代理活动结算详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:activity_settlement:query')")
    @GetMapping(value = ["/{id}"])
    fun getInfo(@PathVariable("id") id: String?): AjaxResult {
        return AjaxResult.success(ctAgentActivitySettlementService!!.selectCtAgentActivitySettlementById(id!!))
    }

    /**
     * 新增代理活动结算
     */
    @PreAuthorize("@ss.hasPermi('account:activity_settlement:add')")
    @Log(title = "代理活动结算", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctAgentActivitySettlement: CtAgentActivitySettlement?): AjaxResult {
        return toAjax(ctAgentActivitySettlementService!!.insertCtAgentActivitySettlement(ctAgentActivitySettlement!!))
    }

    /**
     * 修改代理活动结算
     */
    @PreAuthorize("@ss.hasPermi('account:activity_settlement:edit')")
    @Log(title = "代理活动结算", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctAgentActivitySettlement: CtAgentActivitySettlement?): AjaxResult {
        return toAjax(ctAgentActivitySettlementService!!.updateCtAgentActivitySettlement(ctAgentActivitySettlement!!))
    }
}