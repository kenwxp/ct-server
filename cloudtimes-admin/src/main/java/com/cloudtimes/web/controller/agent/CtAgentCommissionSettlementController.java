package com.cloudtimes.web.controller.agent;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.agent.dto.response.CtAgentCommissionSettlementDto;
import com.cloudtimes.common.utils.DateUtils;
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
import com.cloudtimes.agent.domain.CtAgentCommissionSettlement;
import com.cloudtimes.agent.service.ICtAgentCommissionSettlementService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 销售佣金结算Controller
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/commission_settlement")
public class CtAgentCommissionSettlementController extends BaseController {
    @Autowired
    private ICtAgentCommissionSettlementService ctAgentCommissionSettlementService;

    /**
     * 查询销售佣金结算列表
     */
    @PreAuthorize("@ss.hasPermi('account:commission_settlement:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentCommissionSettlement ctAgentCommissionSettlement) {
        startPage();
        List<CtAgentCommissionSettlement> list = ctAgentCommissionSettlementService.selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement);
        return getDataTable(list);
    }

    /**
     * 查询销售佣金结算列表
     */
    @GetMapping("/commissionSettlementList")
    public TableDataInfo commissionSettlementList(CtAgentCommissionSettlement ctAgentCommissionSettlement) {
        startPage();
        List<CtAgentCommissionSettlementDto> list = ctAgentCommissionSettlementService.selectCtAgentCommissionSettlementListPlus(ctAgentCommissionSettlement);
        return getDataTable(list);
    }

    /**
     * 导出销售佣金结算列表
     */
    @PreAuthorize("@ss.hasPermi('account:commission_settlement:export')")
    @Log(title = "销售佣金结算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentCommissionSettlement ctAgentCommissionSettlement) {
        List<CtAgentCommissionSettlement> list = ctAgentCommissionSettlementService.selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement);
        ExcelUtil<CtAgentCommissionSettlement> util = new ExcelUtil<CtAgentCommissionSettlement>(CtAgentCommissionSettlement.class);
        util.exportExcel(response, list, "销售佣金结算数据");
    }

    /**
     * 获取销售佣金结算详细信息
     */
    //@PreAuthorize("@ss.hasPermi('account:commission_settlement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctAgentCommissionSettlementService.selectCtAgentCommissionSettlementById(id));
    }

    /**
     * 新增销售佣金结算
     */
    @PreAuthorize("@ss.hasPermi('account:commission_settlement:add')")
    @Log(title = "销售佣金结算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentCommissionSettlement ctAgentCommissionSettlement) {
        return toAjax(ctAgentCommissionSettlementService.insertCtAgentCommissionSettlement(ctAgentCommissionSettlement));
    }

    /**
     * 修改销售佣金结算
     */
//    @PreAuthorize("@ss.hasPermi('account:commission_settlement:edit')")
    @Log(title = "销售佣金结算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentCommissionSettlement ctAgentCommissionSettlement) {
        ctAgentCommissionSettlement.setPlatformApprovedTime(DateUtils.getNowDate());
        return toAjax(ctAgentCommissionSettlementService.updateCtAgentCommissionSettlement(ctAgentCommissionSettlement));
    }

    /**
     * 删除销售佣金结算
     */
    @PreAuthorize("@ss.hasPermi('account:commission_settlement:remove')")
    @Log(title = "销售佣金结算", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctAgentCommissionSettlementService.deleteCtAgentCommissionSettlementByIds(ids));
    }
}
