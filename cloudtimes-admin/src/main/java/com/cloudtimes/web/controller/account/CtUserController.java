package com.cloudtimes.web.controller.account;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author 沈兵
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/account/ctuser")
public class CtUserController extends BaseController
{
    @Autowired
    private ICtUserService ctUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('account:ctuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtUser ctUser)
    {
        startPage();
        List<CtUser> list = ctUserService.selectCtUserList(ctUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('account:ctuser:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtUser ctUser)
    {
        List<CtUser> list = ctUserService.selectCtUserList(ctUser);
        ExcelUtil<CtUser> util = new ExcelUtil<CtUser>(CtUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:ctuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctUserService.selectCtUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('account:ctuser:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtUser ctUser)
    {
        return toAjax(ctUserService.insertCtUser(ctUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('account:ctuser:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtUser ctUser)
    {
        return toAjax(ctUserService.updateCtUser(ctUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('account:ctuser:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctUserService.deleteCtUserByIds(ids));
    }
}
