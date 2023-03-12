package com.cloudtimes.app.controller.mp

import com.cloudtimes.account.domain.CtUserBankCard
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.service.ICtUserBankCardService
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.enums.UserType
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
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
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/bank_card")
@Tag(name = "代理-银行卡")
class CtUserBankCardController : BaseController() {
    @Autowired
    private lateinit var ctUserBankCardService: ICtUserBankCardService

    /**
     * 查询用户银行卡列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询用户银行卡列表")
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
    @Operation(summary = "获取用户银行卡详细信息")
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
    @Operation(summary = "新增用户银行卡")
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
    @Operation(summary = "修改用户银行卡")
    fun edit(@Valid @RequestBody ctUserBankCard: CtUserBankCard): AjaxResult {
        ctUserBankCard.userType = UserType.Agent.code
        return toAjax(ctUserBankCardService.updateCtUserBankCard(ctUserBankCard))
    }
}
