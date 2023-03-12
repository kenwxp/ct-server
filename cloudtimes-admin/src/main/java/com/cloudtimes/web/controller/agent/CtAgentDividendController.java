package com.cloudtimes.web.controller.agent;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.agent.dto.response.CtAgentDividendDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import com.cloudtimes.agent.domain.CtAgentDividend;
import com.cloudtimes.agent.service.ICtAgentDividendService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 分润配置Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/agent/agent_dividend")
@Tag(name = "分润配置")
public class CtAgentDividendController extends BaseController {
    @Autowired
    private ICtAgentDividendService ctAgentDividendService;

    @Operation(summary = "查询分润配置列表")
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentDividendDto ctAgentDividend) {
        startPage();
        List<CtAgentDividendDto> list = ctAgentDividendService.selectCtAgentDividendListPlus(ctAgentDividend);
        return getDataTable(list);
    }

    @Operation(summary = "导出分润配置列表")
    @PreAuthorize("@ss.hasPermi('account:agent_dividend:export')")
    @Log(title = "分润配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentDividend ctAgentDividend) {
        List<CtAgentDividend> list = ctAgentDividendService.selectCtAgentDividendList(ctAgentDividend);
        ExcelUtil<CtAgentDividend> util = new ExcelUtil<CtAgentDividend>(CtAgentDividend.class);
        util.exportExcel(response, list, "分润配置数据");
    }

    @Operation(summary = "获取分润配置详细信息")
    //   @PreAuthorize("@ss.hasPermi('account:agent_dividend:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctAgentDividendService.selectCtAgentDividendById(id));
    }

    @Operation(summary = "新增分润配置")
    //  @PreAuthorize("@ss.hasPermi('account:agent_dividend:add')")
    @Log(title = "分润配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentDividend ctAgentDividend) {
        return toAjax(ctAgentDividendService.insertCtAgentDividend(ctAgentDividend));
    }

    @Operation(summary = "修改分润配置")
    // @PreAuthorize("@ss.hasPermi('account:agent_dividend:edit')")
    @Log(title = "分润配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentDividend ctAgentDividend) {
        return toAjax(ctAgentDividendService.updateCtAgentDividend(ctAgentDividend));
    }

    @Operation(summary = "删除分润配置")
    //  @PreAuthorize("@ss.hasPermi('account:agent_dividend:remove')")
    @Log(title = "分润配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctAgentDividendService.deleteCtAgentDividendByIds(ids));
    }
}
