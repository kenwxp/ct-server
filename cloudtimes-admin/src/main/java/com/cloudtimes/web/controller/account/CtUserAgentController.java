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
import com.cloudtimes.account.domain.CtUserAgent;
import com.cloudtimes.account.service.ICtUserAgentService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 代理Controller
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/agent/agent")
public class CtUserAgentController extends BaseController
{
    @Autowired
    private ICtUserAgentService ctUserAgentService;

    /**
     * 查询代理列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtUserAgent ctUserAgent)
    {
        startPage();
        List<CtUserAgent> list = ctUserAgentService.selectCtUserAgentList(ctUserAgent);
        return getDataTable(list);
    }

    /**
     * 导出代理列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:export')")
    @Log(title = "代理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtUserAgent ctUserAgent)
    {
        List<CtUserAgent> list = ctUserAgentService.selectCtUserAgentList(ctUserAgent);
        ExcelUtil<CtUserAgent> util = new ExcelUtil<CtUserAgent>(CtUserAgent.class);
        util.exportExcel(response, list, "代理数据");
    }

    /**
     * 获取代理详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return AjaxResult.success(ctUserAgentService.selectCtUserAgentByUserId(userId));
    }

    /**
     * 新增代理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:add')")
    @Log(title = "代理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtUserAgent ctUserAgent)
    {
        return toAjax(ctUserAgentService.insertCtUserAgent(ctUserAgent));
    }

    /**
     * 修改代理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:edit')")
    @Log(title = "代理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtUserAgent ctUserAgent)
    {
        return toAjax(ctUserAgentService.updateCtUserAgent(ctUserAgent));
    }

    /**
     * 删除代理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:remove')")
    @Log(title = "代理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        return toAjax(ctUserAgentService.deleteCtUserAgentByUserIds(userIds));
    }
}
