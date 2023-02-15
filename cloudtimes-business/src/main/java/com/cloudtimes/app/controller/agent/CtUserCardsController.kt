package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtUserCards
import com.cloudtimes.account.service.ICtUserCardsService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 卡劵维护Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/user_cards")
@Api(tags = ["代理-卡劵维护"])
class CtUserCardsController : BaseController() {
    @Autowired
    private val ctUserCardsService: ICtUserCardsService? = null

    /**
     * 查询卡劵维护列表
     */
    @ApiOperation("查询卡劵列表")
    @GetMapping("/list")
    fun list(ctUserCards: CtUserCards?): TableDataInfo {
        startPage()
        val list = ctUserCardsService!!.selectCtUserCardsList(ctUserCards)
        return getDataTable(list)
    }

    /**
     * 获取卡劵维护详细信息
     */
    @GetMapping(value = ["/{id}"])
    @ApiOperation("获取卡劵维护详细信息")
    fun getInfo(@PathVariable("id") id: String?): AjaxResult {
        return AjaxResult.success(ctUserCardsService!!.selectCtUserCardsById(id))
    }

    /**
     * 新增卡劵维护
     */
    @Log(title = "卡劵维护", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增卡劵")
    fun add(@RequestBody ctUserCards: CtUserCards?): AjaxResult {
        return toAjax(ctUserCardsService!!.insertCtUserCards(ctUserCards))
    }

    /**
     * 修改卡劵维护
     */
    @Log(title = "卡劵维护", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改卡劵")
    fun edit(@RequestBody ctUserCards: CtUserCards?): AjaxResult {
        return toAjax(ctUserCardsService!!.updateCtUserCards(ctUserCards))
    }
}