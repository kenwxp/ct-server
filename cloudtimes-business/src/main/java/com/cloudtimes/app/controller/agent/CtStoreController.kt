package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.agent.dto.request.AgentStoreListRequest
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.agent.dto.request.AgentStoreDetailRequest
import com.cloudtimes.agent.dto.response.AgentStoreOnlineStats
import com.cloudtimes.agent.dto.response.AgentStoreProfitStats
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.app.controller.system.SmsController
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.hardwaredevice.dto.request.RegisterStoreRequest
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByMonth
import com.cloudtimes.hardwaredevice.service.ICtStoreService
import com.cloudtimes.supervise.domain.CtOrder
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
class MonthlyOrderResponse() : RestPageResult<CtOrder>()
class StoreProfitResponse() : RestResult<AgentStoreProfitStats>()

/**
 * 用户Controller
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/agent/store")
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
     * 查询代理门店没有订单
     */
    @ApiOperation("查询代理门店每月订单")
    @PostMapping("/monthly_order")
    fun monthlyOrder(@Valid @RequestBody request: QueryOrdersByMonth): MonthlyOrderResponse {
        startPage(request.pageNum, request.pageSize)

        val list = ctOrderService.selectMonthlyOrders(request)
        val page = getDataTable(list)

        return MonthlyOrderResponse().apply {
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
    fun profitStats(@Valid @RequestBody request: QueryByUserIdRequest): StoreProfitResponse {
        // :TODO:
        return StoreProfitResponse().apply {
            data = AgentStoreProfitStats()
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
