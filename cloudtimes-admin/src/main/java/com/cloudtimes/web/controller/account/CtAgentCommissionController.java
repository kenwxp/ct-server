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
import com.cloudtimes.account.domain.CtAgentCommission;
import com.cloudtimes.account.service.ICtAgentCommissionService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 代理销售佣金设置Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/agent_commission")
public class CtAgentCommissionController extends BaseController
{
    @Autowired
    private ICtAgentCommissionService ctAgentCommissionService;

    /**
     * 查询代理销售佣金设置列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_commission:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentCommission ctAgentCommission)
    {
        startPage();
        List<CtAgentCommission> list = ctAgentCommissionService.selectCtAgentCommissionList(ctAgentCommission);
        return getDataTable(list);
    }

    /**
     * 导出代理销售佣金设置列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_commission:export')")
    @Log(title = "代理销售佣金设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentCommission ctAgentCommission)
    {
        List<CtAgentCommission> list = ctAgentCommissionService.selectCtAgentCommissionList(ctAgentCommission);
        ExcelUtil<CtAgentCommission> util = new ExcelUtil<CtAgentCommission>(CtAgentCommission.class);
        util.exportExcel(response, list, "代理销售佣金设置数据");
    }

    /**
     * 获取代理销售佣金设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:agent_commission:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentCommissionService.selectCtAgentCommissionById(id));
    }

    /**
     * 新增代理销售佣金设置
     */
    @PreAuthorize("@ss.hasPermi('account:agent_commission:add')")
    @Log(title = "代理销售佣金设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentCommission ctAgentCommission)
    {
        var user = getLoginUser();
        ctAgentCommission.operator = user.getUsername();
        return toAjax(ctAgentCommissionService.insertCtAgentCommission(ctAgentCommission));
    }

    /**
     * 修改代理销售佣金设置
     */
    @PreAuthorize("@ss.hasPermi('account:agent_commission:edit')")
    @Log(title = "代理销售佣金设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentCommission ctAgentCommission)
    {
        var user = getLoginUser();
        ctAgentCommission.operator = user.getUsername();
        return toAjax(ctAgentCommissionService.updateCtAgentCommission(ctAgentCommission));
    }

    /**
     * 删除代理销售佣金设置
     */
    @PreAuthorize("@ss.hasPermi('account:agent_commission:remove')")
    @Log(title = "代理销售佣金设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctAgentCommissionService.deleteCtAgentCommissionByIds(ids));
    }
}
