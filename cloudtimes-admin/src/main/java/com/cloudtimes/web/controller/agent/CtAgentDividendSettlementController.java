package com.cloudtimes.web.controller.agent;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.agent.dto.response.CtAgentDividendSettlementDto;
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
import com.cloudtimes.agent.domain.CtAgentDividendSettlement;
import com.cloudtimes.agent.service.ICtAgentDividendSettlementService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 分润结算审核Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/dividend_settlement")
@Tag(name = "分润结算审核")
public class CtAgentDividendSettlementController extends BaseController {
    @Autowired
    private ICtAgentDividendSettlementService ctAgentDividendSettlementService;

    @Operation(summary = "查询分润结算审核列表")
    @PreAuthorize("@ss.hasPermi('account:dividend_settlement:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentDividendSettlement ctAgentDividendSettlement) {
        startPage();
        List<CtAgentDividendSettlementDto> list = ctAgentDividendSettlementService.selectCtAgentDividendSettlementListPlus(ctAgentDividendSettlement);
        return getDataTable(list);
    }

    @Operation(summary = "导出分润结算审核列表")
    @PreAuthorize("@ss.hasPermi('account:dividend_settlement:export')")
    @Log(title = "分润结算审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentDividendSettlement ctAgentDividendSettlement) {
        List<CtAgentDividendSettlement> list = ctAgentDividendSettlementService.selectCtAgentDividendSettlementList(ctAgentDividendSettlement);
        ExcelUtil<CtAgentDividendSettlement> util = new ExcelUtil<CtAgentDividendSettlement>(CtAgentDividendSettlement.class);
        util.exportExcel(response, list, "分润结算审核数据");
    }

    @Operation(summary = "获取分润结算审核详细信息")
    //  @PreAuthorize("@ss.hasPermi('account:dividend_settlement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctAgentDividendSettlementService.selectCtAgentDividendSettlementById(id));
    }

    @Operation(summary = "新增分润结算审核")
    @PreAuthorize("@ss.hasPermi('account:dividend_settlement:add')")
    @Log(title = "分润结算审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentDividendSettlement ctAgentDividendSettlement) {
        return toAjax(ctAgentDividendSettlementService.insertCtAgentDividendSettlement(ctAgentDividendSettlement));
    }

    @Operation(summary = "修改分润结算审核")
    //  @PreAuthorize("@ss.hasPermi('account:dividend_settlement:edit')")
    @Log(title = "分润结算审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentDividendSettlement ctAgentDividendSettlement) {
        return toAjax(ctAgentDividendSettlementService.updateCtAgentDividendSettlement(ctAgentDividendSettlement));
    }
}
