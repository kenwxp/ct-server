package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.agent.dto.request.AgentStoreListRequest
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.agent.dto.request.AgentStoreDetailRequest
import com.cloudtimes.agent.dto.request.StoreProfitRequest
import com.cloudtimes.agent.dto.response.AgentStoreOnlineStats
import com.cloudtimes.agent.dto.response.AgentStoreProfitStats
import com.cloudtimes.agent.dto.response.StoreOrderDetail
import com.cloudtimes.agent.dto.response.OrderMonthlyStats
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.app.controller.system.SmsController
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByDate
import com.cloudtimes.hardwaredevice.dto.request.RegisterStoreRequest
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByMonth
import com.cloudtimes.hardwaredevice.service.ICtStoreService
import com.cloudtimes.supervise.service.ICtOrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


// 泛型具体化
class StoreAndCommissionPage() : RestPageResult<StoreAndCommission>()
class StoreAndCommissionDetail() : RestResult<StoreAndCommission>()
class AgentShopStatsPage() : RestPageResult<AgentStoreOnlineStats>()
class MonthlyOrderResponse() : RestPageResult<OrderMonthlyStats>()
class DailyOrderResponse() : RestPageResult<StoreOrderDetail>()
class StoreProfitResponse() : RestResult<AgentStoreProfitStats>()

/**
 * 用户Controller
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/store")
@Api(tags = ["代理-门店"])
class CtStoreController : BaseController() {
    @Autowired
    private lateinit var ctUserAgentService: ICtUserAgentService

    @Autowired
    private lateinit var ctOrderService: ICtOrderService

    @Autowired
    private lateinit var ctStoreService: ICtStoreService

    @Autowired
    private lateinit var smsController: SmsController

    /**
     * 查询代理门店
     */
    @ApiOperation("查询代理门店列表")
    @PostMapping("/list")
    fun list(@Valid @RequestBody request: AgentStoreListRequest): StoreAndCommissionPage {
        startPage(request.pageNum, request.pageSize)
        val storeAndCommissionList = ctUserAgentService.selectCtAgentShopList(request)
        val pageData = getDataTable(storeAndCommissionList)
        return StoreAndCommissionPage().apply {
            total = pageData.total
            data = storeAndCommissionList
        }
    }

    /**
     * 查询代理门店详情
     */
    @ApiOperation("查询代理门店详情")
    @PostMapping("/detail")
    fun detail(@Valid @RequestBody request: AgentStoreDetailRequest): StoreAndCommissionDetail {
        val detail = ctUserAgentService.selectCtAgentShopDetail(request)
        return StoreAndCommissionDetail().apply {
            data = detail
        }
    }

    /**
     * 查询代理门店没有订单统计
     */
    @ApiOperation("查询代理门店每月订单统计")
    @PostMapping("/monthly_order_stats")
    fun monthlyOrderStats(@Valid @RequestBody request: QueryOrdersByMonth): MonthlyOrderResponse {
        val list = ctOrderService.selectMonthlyOrderStats(request)

        return MonthlyOrderResponse().apply {
            data = list
            total = list.size.toLong()
        }
    }


    /**
     * 查询代理门店没有订单
     */
    @ApiOperation("查询代理门店每日订单")
    @PostMapping("/daily_orders")
    fun dailyOrder(@Valid @RequestBody request: QueryOrdersByDate): DailyOrderResponse {
        startPage(request.pageNum, request.pageSize)

        val list = ctOrderService.selectDailyOrders(request)
        val page = getDataTable(list)

        return DailyOrderResponse().apply {
            data = list
            total = page.total
        }
    }

    /**
     * 查询代理门店上线统计
     */
    @ApiOperation("查询代理门店上线统计")
    @PostMapping("/online_stats")
    fun onlineStats(@Valid @RequestBody request: QueryByUserIdRequest): AgentShopStatsPage {
        val list = ctUserAgentService.selectCtAgentShopOnlineStats(request.userId)
        return AgentShopStatsPage().apply {
            data = list
            total = list.size.toLong()
        }
    }

    /**
     * 查询代理门店收益统计
     */
    @ApiOperation("查询代理门店收益统计")
    @PostMapping("/profit_stats")
    fun profitStats(@Valid @RequestBody request: StoreProfitRequest): StoreProfitResponse {
        val profits = ctUserAgentService.selectCtAgentShopProfitStats(request)
        return StoreProfitResponse().apply {
            data = profits
        }
    }

    @ApiOperation("门店注册")
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterStoreRequest): AjaxResult {
        // Step 1. 校验手机验证码
        smsController.validateSMS(request.mobile, request.verifyCode, request.verifyUUID)

        // Step 2. 业务处理
        ctStoreService.registerStore(request)
        return AjaxResult.success()
    }

}
