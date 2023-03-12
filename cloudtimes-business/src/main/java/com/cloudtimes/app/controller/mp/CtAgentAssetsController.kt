package com.cloudtimes.app.controller.mp

import com.cloudtimes.account.domain.CtWithdrawalBook
import com.cloudtimes.account.dto.request.*
import com.cloudtimes.account.dto.response.QueryAssetsBookResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.account.dto.response.TransferRecord
import com.cloudtimes.account.service.ICtTransferBookService
import com.cloudtimes.account.service.ICtUserAssetsBookService
import com.cloudtimes.agent.service.ICtUserAgentService
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.account.service.ICtWithdrawalBookService
import com.cloudtimes.agent.dto.response.AgentAssets
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.app.controller.system.SmsController
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.exception.ServiceException
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


/** 泛型具体化 */
class AgentAssetsResponse() : RestResult<AgentAssets>()
class TransferRecordPage() : RestPageResult<TransferRecord>()
class WithdrawalRecordPage() : RestPageResult<CtWithdrawalBook>()
class AssetsBookPage() : RestPageResult<QueryAssetsBookResponse>()

/**
 * 代理Controller
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/assets")
@Tag(name = "代理-资产")
class CtAgentAssetsController : BaseController() {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var agentService: ICtUserAgentService

    @Autowired
    private lateinit var userService: ICtUserService

    @Autowired
    private lateinit var smsController: SmsController

    @Autowired
    private lateinit var transferBookService: ICtTransferBookService

    @Autowired
    private lateinit var withdrawalBookService: ICtWithdrawalBookService

    @Autowired
    private lateinit var assetsBookService: ICtUserAssetsBookService

    /** 获取代理资产详细信息 */
    @PostMapping()
    @Operation(summary = "获取代理资产详细信息")
    fun listAssetsByUserId(@Valid @RequestBody request: QueryByUserIdRequest): AgentAssetsResponse {
        val assets = agentService.selectAgentAssets(request.userId)
        return if (assets == null) {
            // AgentAssetsResponse().apply{ notFound("没有获取到代理资产详细信息") }
            AgentAssetsResponse()
        } else {
            AgentAssetsResponse().apply {
                data = assets
            }
        }
    }

    @Log(title = "代理提现", businessType = BusinessType.UPDATE)
    @PostMapping("/withdrawal")
    @Operation(summary = "代理提现")
    fun withdrawCash(@Valid @RequestBody request: WithdrawCashRequest): AjaxResult {
        return AjaxResult.success(agentService.withdrawCash(request))
    }

    @Log(title = "代理转账", businessType = BusinessType.UPDATE)
    @PostMapping("/transfer")
    @Operation(summary = "代理转账")
    fun transferCash(@Valid @RequestBody request: TransferCashRequest): AjaxResult {
        // Step 1. 校验手机验证码
        val payer = userService.selectCtUserById(request.payerUserId!!) ?:
            throw ServiceException("没有查询到用户信息")
        smsController.validateSMS(payer.mobile, request.verifyCode, request.verifyUUID)

        // Step 2. 业务处理
        return AjaxResult.success(agentService.transferCash(request))
    }

    @Log(title = "代理转账记录", businessType = BusinessType.UPDATE)
    @PostMapping("/list_transfer_records")
    @Operation(summary = "查询代理转账记录")
    fun listTransferRecords(@Valid @RequestBody request: QueryByUserIdWithPageRequest): TransferRecordPage {
        startPage(request.pageNum, request.pageSize)

        val transferRecords = transferBookService.selectTransferRecords(request.userId!!)

        return TransferRecordPage().apply {
            total = getDataTable(transferRecords).total
            data = transferRecords
        }
    }

    @Log(title = "查询代理账单", businessType = BusinessType.OTHER)
    @PostMapping("/list_assets_book")
    @Operation(summary = "查询代理账单")
    fun listAssetsBook(@Valid @RequestBody request: QueryAssetsBookRequest): AssetsBookPage {
        startPage(request.pageNum, request.pageSize)

        logger.info("request: $request")

        val records = assetsBookService.selectAgentAssetsBookList(request)

        return AssetsBookPage().apply {
            total = getDataTable(records).total
            data = records
        }
    }

    @Log(title = "代理提现记录", businessType = BusinessType.UPDATE)
    @PostMapping("/list_withdrawal_records")
    @Operation(summary = "查询代理提现记录")
    fun listWithdrawalRecords(@Valid @RequestBody request: QueryAgentWithdrawalRequest): WithdrawalRecordPage {
        startPage(request.pageNum, request.pageSize)

        val records = withdrawalBookService.selectAgentWithdrawalList(request)

        return WithdrawalRecordPage().apply {
            total = getDataTable(records).total
            data = records
        }
    }
}
