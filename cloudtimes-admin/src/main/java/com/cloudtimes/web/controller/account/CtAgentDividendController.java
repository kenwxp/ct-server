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
import com.cloudtimes.account.domain.CtAgentDividend;
import com.cloudtimes.account.service.ICtAgentDividendService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 分润配置Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/agent_dividend")
public class CtAgentDividendController extends BaseController
{
    @Autowired
    private ICtAgentDividendService ctAgentDividendService;

    /**
     * 查询分润配置列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentDividend ctAgentDividend)
    {
        startPage();
        List<CtAgentDividend> list = ctAgentDividendService.selectCtAgentDividendList(ctAgentDividend);
        return getDataTable(list);
    }

    /**
     * 导出分润配置列表
     */
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:export')")
    @Log(title = "分润配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentDividend ctAgentDividend)
    {
        List<CtAgentDividend> list = ctAgentDividendService.selectCtAgentDividendList(ctAgentDividend);
        ExcelUtil<CtAgentDividend> util = new ExcelUtil<CtAgentDividend>(CtAgentDividend.class);
        util.exportExcel(response, list, "分润配置数据");
    }

    /**
     * 获取分润配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentDividendService.selectCtAgentDividendById(id));
    }

    /**
     * 新增分润配置
     */
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:add')")
    @Log(title = "分润配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentDividend ctAgentDividend)
    {
        return toAjax(ctAgentDividendService.insertCtAgentDividend(ctAgentDividend));
    }

    /**
     * 修改分润配置
     */
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:edit')")
    @Log(title = "分润配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentDividend ctAgentDividend)
    {
        return toAjax(ctAgentDividendService.updateCtAgentDividend(ctAgentDividend));
    }

    /**
     * 删除分润配置
     */
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:remove')")
    @Log(title = "分润配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctAgentDividendService.deleteCtAgentDividendByIds(ids));
    }
}
