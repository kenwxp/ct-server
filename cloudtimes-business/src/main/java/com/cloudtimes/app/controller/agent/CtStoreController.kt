package com.cloudtimes.app.controller.agent

import com.cloudtimes.agent.dto.request.AgentStoreRequest
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.app.controller.system.SmsController
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.hardwaredevice.dto.request.RegisterStoreRequest
import com.cloudtimes.hardwaredevice.dto.request.ShopOrderByMonthRequest
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
    @ApiOperation("查询代理门店")
    @PostMapping("/list")
    fun list(@Valid @RequestBody request: AgentStoreRequest): StoreAndCommissionPage {
        startPage(request.pageNum, request.pageSize)
        val storeAndCommissionList = ctUserAgentService.selectCtAgentShopList(request)
        val pageData = getDataTable(storeAndCommissionList)
        return StoreAndCommissionPage().apply {
            total = pageData.total
            data = storeAndCommissionList
        }
    }

    /**
     * 查询代理门店没有订单
     */
    @ApiOperation("查询代理门店没有订单")
    @GetMapping("/monthly_order")
    fun monthlyOrder(@Valid monthlyOrderRequest: ShopOrderByMonthRequest): TableDataInfo {
        startPage()
        val order = CtOrder()
        order.yearMonth = monthlyOrderRequest.yearMonth
        order.storeId = monthlyOrderRequest.storeId
        val list = ctOrderService.selectCtOrderList(order)
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

    @ApiOperation("门店注册")
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterStoreRequest): AjaxResult {
        // Step 1. 校验手机验证码
        // :TODO: 放开手机校验
        // smsController.validateSMS(request.mobile, request.verifyCode, request.verifyUUID)

        // Step 2. 业务处理
        ctStoreService.registerStore(request)
        return AjaxResult.success()
    }

}
