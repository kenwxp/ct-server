package com.cloudtimes.app.controller.v1.agent

import com.cloudtimes.account.domain.CtUserBankCard
import com.cloudtimes.account.service.ICtUserBankCardService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 用户银行卡Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/v1/agent/bank_card")
@Api("用户银行卡")
class CtUserBankCardController : BaseController() {
    @Autowired
    private lateinit var ctUserBankCardService: ICtUserBankCardService

    /**
     * 查询用户银行卡列表
     */
    @GetMapping("/list")
    @ApiOperation("查询用户银行卡列表")
    fun list(ctUserBankCard: CtUserBankCard?): TableDataInfo {
        startPage()
        val list: List<CtUserBankCard?> = ctUserBankCardService.selectCtUserBankCardList(
            ctUserBankCard!!
        )
        return getDataTable(list)
    }

    /**
     * 获取用户银行卡详细信息
     */
    @GetMapping(value = ["/{id}"])
    @ApiOperation("获取用户银行卡详细信息")
    fun getInfo(@PathVariable("id") id: String): AjaxResult {
        val info = ctUserBankCardService.selectCtUserBankCardById(id)
        return if (info == null) {
            AjaxResult.error(HttpStatus.NOT_FOUND.value(), "未找到银行卡")
        } else {
            AjaxResult.success(info)
        }
    }

    /**
     * 获取用户银行卡详细信息
     */
    @GetMapping(value = ["/by_user_id/{userId}"])
    @ApiOperation("获取用户银行卡详细信息")
    fun getInfoByUserId(@PathVariable("userId") userId: String): AjaxResult {
        val info = ctUserBankCardService.selectCtUserBankCardByUserId(userId)
        return if (info == null) {
            AjaxResult.error(HttpStatus.NOT_FOUND.value(), "Not Found")
            AjaxResult.error(HttpStatus.NOT_FOUND.value(), "未找到银行卡")
        } else {
            AjaxResult.success(info)
        }
    }

    /**
     * 新增用户银行卡
     */
    @Log(title = "用户银行卡", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增用户银行卡")
    fun add(@RequestBody ctUserBankCard: CtUserBankCard): AjaxResult {
        return toAjax(ctUserBankCardService.insertCtUserBankCard(ctUserBankCard))
    }

    /**
     * 修改用户银行卡
     */
    @Log(title = "用户银行卡", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改用户银行卡")
    fun edit(@RequestBody ctUserBankCard: CtUserBankCard?): AjaxResult {
        return toAjax(ctUserBankCardService.updateCtUserBankCard(ctUserBankCard!!))
    }
}