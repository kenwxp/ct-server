package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtStoreUser
import com.cloudtimes.account.service.ICtStoreUserService
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
 * 门店会员Controller
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/account/store_user")
class CtStoreUserController : BaseController() {
    @Autowired
    private lateinit var ctStoreUserService: ICtStoreUserService

    /**
     * 查询门店会员列表
     */
    @PreAuthorize("@ss.hasPermi('account:store_user:list')")
    @GetMapping("/list")
    fun list(ctStoreUser: CtStoreUser): TableDataInfo {
        startPage()
        val list: List<CtStoreUser> = ctStoreUserService.selectCtStoreUserList(ctStoreUser)
        return getDataTable(list)
    }

    /**
     * 导出门店会员列表
     */
    @PreAuthorize("@ss.hasPermi('account:store_user:export')")
    @Log(title = "门店会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctStoreUser: CtStoreUser) {
        val list = ctStoreUserService.selectCtStoreUserList(ctStoreUser)
        val util = ExcelUtil(
            CtStoreUser::class.java
        )
        util.exportExcel(response, list, "门店会员数据")
    }

    /**
     * 获取门店会员详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:store_user:query')")
    @GetMapping(value = ["/{storeId}"])
    fun getInfo(@PathVariable("storeId") storeId: String): AjaxResult {
        return AjaxResult.success(ctStoreUserService.selectCtStoreUserByStoreId(storeId))
    }

    /**
     * 新增门店会员
     */
    @PreAuthorize("@ss.hasPermi('account:store_user:add')")
    @Log(title = "门店会员", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctStoreUser: CtStoreUser): AjaxResult {
        return toAjax(ctStoreUserService.insertCtStoreUser(ctStoreUser))
    }

    /**
     * 修改门店会员
     */
    @PreAuthorize("@ss.hasPermi('account:store_user:edit')")
    @Log(title = "门店会员", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctStoreUser: CtStoreUser): AjaxResult {
        return toAjax(ctStoreUserService.updateCtStoreUser(ctStoreUser))
    }

    /**
     * 删除门店会员
     */
    @PreAuthorize("@ss.hasPermi('account:store_user:remove')")
    @Log(title = "门店会员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{storeIds}")
    fun remove(@PathVariable storeIds: Array<String>): AjaxResult {
        return toAjax(ctStoreUserService.deleteCtStoreUserByStoreIds(storeIds))
    }
}