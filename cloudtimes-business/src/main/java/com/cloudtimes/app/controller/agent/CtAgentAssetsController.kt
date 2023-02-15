package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtUserAgent
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.dto.request.TransferCashRequest
import com.cloudtimes.account.dto.request.WithdrawCashRequest
import com.cloudtimes.account.service.ICtUserAgentService
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.enums.BusinessType
import com.github.xiaoymin.knife4j.annotations.DynamicParameters
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


class AgentAssetsResponse(override var data: CtUserAgent? = null) : RestResult<CtUserAgent>(data)

/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/agent/assets")
@Api(tags = ["代理-资产"])
class CtAgentAssetsController : BaseController() {
    @Autowired
    private lateinit var ctUserAgentService: ICtUserAgentService

    /** 获取代理资产详细信息 */
    @PostMapping()
    @ApiOperation(value = "获取代理资产详细信息", response = CtUserAgent::class)
    fun listAssetsByUserId(@Valid @RequestBody request: QueryByUserIdRequest): AgentAssetsResponse {
        val assets = ctUserAgentService.selectCtUserAgentByUserId(request.userId!!)
        return if (assets == null) {
            AgentAssetsResponse().apply{
                notFound("没有获取到代理资产详细信息")
            }
        } else {
            AgentAssetsResponse(assets)
        }
    }

    @Log(title = "代理提现", businessType = BusinessType.UPDATE)
    @PostMapping("/withdrawal")
    @ApiOperation("代理提现")
    fun withdrawCash(@Valid @RequestBody request: WithdrawCashRequest): AjaxResult {
        return AjaxResult.success(ctUserAgentService.withdrawCash(request))
    }

    @Log(title = "代理转账", businessType = BusinessType.UPDATE)
    @PostMapping("/transfer")
    @ApiOperation("代理转账")
    fun transferCash(@Valid @RequestBody request: TransferCashRequest): AjaxResult {
        return AjaxResult.success(ctUserAgentService.transferCash(request))
    }
}
