package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtStoreDiscount
import com.cloudtimes.account.service.ICtStoreDiscountService
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.utils.poi.ExcelUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * 店铺会员折扣Controller
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/account/store_discount")
class CtStoreDiscountController : BaseController() {
    @Autowired
    private lateinit var ctStoreDiscountService: ICtStoreDiscountService

    /**
     * 查询店铺会员折扣列表
     */
    @PreAuthorize("@ss.hasPermi('account:store_discount:list')")
    @GetMapping("/list")
    fun list(ctStoreDiscount: CtStoreDiscount): TableDataInfo {
        startPage()
        val list: List<CtStoreDiscount> = ctStoreDiscountService.selectCtStoreDiscountList(
            ctStoreDiscount
        )
        return getDataTable(list)
    }

    /**
     * 导出店铺会员折扣列表
     */
    @PreAuthorize("@ss.hasPermi('account:store_discount:export')")
    @Log(title = "店铺会员折扣", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctStoreDiscount: CtStoreDiscount) {
        val list = ctStoreDiscountService.selectCtStoreDiscountList(ctStoreDiscount)
        val util = ExcelUtil(
            CtStoreDiscount::class.java
        )
        util.exportExcel(response, list, "店铺会员折扣数据")
    }

    /**
     * 获取店铺会员折扣详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:store_discount:query')")
    @GetMapping(value = ["/{storeId}"])
    fun getInfo(@PathVariable("storeId") storeId: String): AjaxResult {
        return AjaxResult.success(ctStoreDiscountService.selectCtStoreDiscountByStoreId(storeId))
    }

    /**
     * 新增店铺会员折扣
     */
    @PreAuthorize("@ss.hasPermi('account:store_discount:add')")
    @Log(title = "店铺会员折扣", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctStoreDiscount: CtStoreDiscount): AjaxResult {
        return toAjax(ctStoreDiscountService.insertCtStoreDiscount(ctStoreDiscount))
    }

    /**
     * 修改店铺会员折扣
     */
    @PreAuthorize("@ss.hasPermi('account:store_discount:edit')")
    @Log(title = "店铺会员折扣", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctStoreDiscount: CtStoreDiscount): AjaxResult {
        return toAjax(ctStoreDiscountService.updateCtStoreDiscount(ctStoreDiscount))
    }

    /**
     * 删除店铺会员折扣
     */
    @PreAuthorize("@ss.hasPermi('account:store_discount:remove')")
    @Log(title = "店铺会员折扣", businessType = BusinessType.DELETE)
    @DeleteMapping("/{storeIds}")
    fun remove(@PathVariable storeIds: Array<String>): AjaxResult {
        return toAjax(ctStoreDiscountService.deleteCtStoreDiscountByStoreIds(storeIds))
    }
}