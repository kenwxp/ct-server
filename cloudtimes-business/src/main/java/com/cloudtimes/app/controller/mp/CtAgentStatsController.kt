package com.cloudtimes.app.controller.mp

import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.stats.domain.CtStatsMonthlySales
import com.cloudtimes.stats.service.ICtAgentStatsService
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * 代理数据统计Controller
 *
 * @author 沈兵
 * @date 2023-02-09
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/stats")
@Tag(name = "代理-数据统计")
class CtAgentStatsController : BaseController() {
    @Autowired
    private lateinit var ctStatsMonthlySalesService: ICtAgentStatsService

    /**
     * 查询门店月销售统计列表
     */
    @GetMapping("/monthly_sales")
    @Operation(summary = "查询门店月销售统计列表")
    fun monthlySales(@Valid ctStatsMonthlySales: CtStatsMonthlySales): TableDataInfo {
        startPage()
        val list = ctStatsMonthlySalesService.selectCtStatsMonthlySalesList(ctStatsMonthlySales)
        return getDataTable(list)
    }

    @GetMapping("/commission_and_dividend")
    @Operation(summary = "查询代理佣金和分润统计")
    fun commissionAndDividend(): AjaxResult {
        // :TODO: 获取登陆用户ID
        val userId = "e4011707-a691-11ed-8957-0242ac110003"
        val stats = ctStatsMonthlySalesService.selectCtStatsCommisionAndDividend(userId)

        return AjaxResult.success(stats);
    }
}
