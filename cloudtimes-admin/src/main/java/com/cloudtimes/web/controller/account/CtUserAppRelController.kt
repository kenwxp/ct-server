package com.cloudtimes.web.controller.account

import com.cloudtimes.account.domain.CtUserAppRel
import com.cloudtimes.account.service.ICtUserAppRelService
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
 * 用户与应用关系Controller
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/account/user_app")
class CtUserAppRelController : BaseController() {
    @Autowired
    private lateinit var ctUserAppRelService: ICtUserAppRelService

    /**
     * 查询用户与应用关系列表
     */
    @PreAuthorize("@ss.hasPermi('account:user_app:list')")
    @GetMapping("/list")
    fun list(ctUserAppRel: CtUserAppRel): TableDataInfo {
        startPage()
        val list: List<CtUserAppRel> = ctUserAppRelService.selectCtUserAppRelList(ctUserAppRel)
        return getDataTable(list)
    }

    /**
     * 导出用户与应用关系列表
     */
    @PreAuthorize("@ss.hasPermi('account:user_app:export')")
    @Log(title = "用户与应用关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    fun export(response: HttpServletResponse, ctUserAppRel: CtUserAppRel) {
        val list = ctUserAppRelService.selectCtUserAppRelList(ctUserAppRel)
        val util = ExcelUtil(
            CtUserAppRel::class.java
        )
        util.exportExcel(response, list, "用户与应用关系数据")
    }

    /**
     * 获取用户与应用关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:user_app:query')")
    @GetMapping(value = ["/{userId}"])
    fun getInfo(@PathVariable("userId") userId: String): AjaxResult {
        return AjaxResult.success(ctUserAppRelService.selectCtUserAppRelByUserId(userId))
    }

    /**
     * 新增用户与应用关系
     */
    @PreAuthorize("@ss.hasPermi('account:user_app:add')")
    @Log(title = "用户与应用关系", businessType = BusinessType.INSERT)
    @PostMapping
    fun add(@RequestBody ctUserAppRel: CtUserAppRel): AjaxResult {
        return toAjax(ctUserAppRelService.insertCtUserAppRel(ctUserAppRel))
    }

    /**
     * 修改用户与应用关系
     */
    @PreAuthorize("@ss.hasPermi('account:user_app:edit')")
    @Log(title = "用户与应用关系", businessType = BusinessType.UPDATE)
    @PutMapping
    fun edit(@RequestBody ctUserAppRel: CtUserAppRel): AjaxResult {
        return toAjax(ctUserAppRelService.updateCtUserAppRel(ctUserAppRel))
    }

    /**
     * 删除用户与应用关系
     */
    @PreAuthorize("@ss.hasPermi('account:user_app:remove')")
    @Log(title = "用户与应用关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    fun remove(@PathVariable userIds: Array<String>): AjaxResult {
        return toAjax(ctUserAppRelService.deleteCtUserAppRelByUserIds(userIds))
    }
}