package com.cloudtimes.web.controller.stats

import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import com.cloudtimes.stats.domain.CtStatsMonthlySales
import com.cloudtimes.stats.service.ICtAgentStatsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 门店月销售统计Controller
 *
 * @author 沈兵
 * @date 2023-02-09
 */
@RestController
@RequestMapping("/stats/monthly_sales")
class CtStatsMonthlySalesController : BaseController() {
    @Autowired
    private lateinit var ctStatsMonthlySalesService: ICtAgentStatsService

    /**
     * 查询门店月销售统计列表
     */
    @PreAuthorize("@ss.hasPermi('stats:monthly_sales:list')")
    @GetMapping("/")
    fun list(ctStatsMonthlySales: CtStatsMonthlySales): TableDataInfo {
        startPage()
        val list = ctStatsMonthlySalesService.selectCtStatsMonthlySalesList(ctStatsMonthlySales)
        return getDataTable(list)
    }

    /**
     * 导出门店月销售统计列表
     */
    @PreAuthorize("@ss.hasPermi('stats:monthly_sales:export')")
    @Log(title = "门店月销售统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctStatsMonthlySales: CtStatsMonthlySales) {
        val list = ctStatsMonthlySalesService.selectCtStatsMonthlySalesList(ctStatsMonthlySales)
        val util = ExcelUtil(
            CtStatsMonthlySales::class.java
        )
        util.exportExcel(response, list, "门店月销售统计数据")
    }
}