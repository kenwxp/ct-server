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
import com.cloudtimes.account.domain.CtAgentActivity;
import com.cloudtimes.account.service.ICtAgentActivityService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 代理活动Controller
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
@RestController
@RequestMapping("/account/agent_activity")
public class CtAgentActivityController extends BaseController
{
    @Autowired
    private ICtAgentActivityService ctAgentActivityService;

    /**
     * 查询代理活动列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentActivity ctAgentActivity)
    {
        startPage();
        List<CtAgentActivity> list = ctAgentActivityService.selectCtAgentActivityList(ctAgentActivity);
        return getDataTable(list);
    }

    /**
     * 导出代理活动列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity:export')")
    @Log(title = "代理活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentActivity ctAgentActivity)
    {
        List<CtAgentActivity> list = ctAgentActivityService.selectCtAgentActivityList(ctAgentActivity);
        ExcelUtil<CtAgentActivity> util = new ExcelUtil<CtAgentActivity>(CtAgentActivity.class);
        util.exportExcel(response, list, "代理活动数据");
    }

    /**
     * 获取代理活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentActivityService.selectCtAgentActivityById(id));
    }

    /**
     * 新增代理活动
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity:add')")
    @Log(title = "代理活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentActivity ctAgentActivity)
    {
        var user = getLoginUser();
        ctAgentActivity.setOperator(user.getUsername());
        return toAjax(ctAgentActivityService.insertCtAgentActivity(ctAgentActivity));
    }

    /**
     * 修改代理活动
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity:edit')")
    @Log(title = "代理活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentActivity ctAgentActivity)
    {
        var user = getLoginUser();
        ctAgentActivity.setOperator(user.getUsername());
        return toAjax(ctAgentActivityService.updateCtAgentActivity(ctAgentActivity));
    }

    /**
     * 删除代理活动
     */
    @PreAuthorize("@ss.hasPermi('account:agent_activity:remove')")
    @Log(title = "代理活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctAgentActivityService.deleteCtAgentActivityByIds(ids));
    }
}
