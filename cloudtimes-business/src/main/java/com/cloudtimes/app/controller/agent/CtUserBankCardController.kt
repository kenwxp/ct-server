package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtUserBankCard
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.service.ICtUserBankCardService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.enums.UserType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


class AgentBackCardResponse(override var data: CtUserBankCard? = null) : RestResult<CtUserBankCard>(data)

/**
 * 用户银行卡Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/agent/bank_card")
@Api(tags = ["代理-银行卡"])
class CtUserBankCardController : BaseController() {
    @Autowired
    private lateinit var ctUserBankCardService: ICtUserBankCardService

    /**
     * 查询用户银行卡列表
     */
    @GetMapping("/list")
    @ApiOperation("查询用户银行卡列表")
    fun list(ctUserBankCard: CtUserBankCard): TableDataInfo {
        startPage()
        val list: List<CtUserBankCard> = ctUserBankCardService.selectCtUserBankCardList(
            ctUserBankCard
        )
        return getDataTable(list)
    }

    /**
     * 获取用户银行卡详细信息
     */
    @PostMapping(value = ["/by_user_id"])
    @ApiOperation("获取用户银行卡详细信息")
    fun getInfoByUserId(@Valid @RequestBody request: QueryByUserIdRequest): AgentBackCardResponse {
        val info = ctUserBankCardService.selectCtUserBankCardByUserId(request.userId!!)
        val response = AgentBackCardResponse(info)
        if (info == null) {
            response.msg = "未找到银行卡"
        }
        return response
    }

    /**
     * 新增用户银行卡
     */
    @Log(title = "用户银行卡", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增用户银行卡")
    fun add(@Valid @RequestBody ctUserBankCard: CtUserBankCard): AjaxResult {
        ctUserBankCard.userType = UserType.Agent.code
        val info = ctUserBankCardService.insertCtUserBankCard(ctUserBankCard)
        return AjaxResult.success(info)
    }

    /**
     * 修改用户银行卡
     */
    @Log(title = "用户银行卡", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改用户银行卡")
    fun edit(@Valid @RequestBody ctUserBankCard: CtUserBankCard): AjaxResult {
        ctUserBankCard.userType = UserType.Agent.code
        return toAjax(ctUserBankCardService.updateCtUserBankCard(ctUserBankCard))
    }
}
