package com.cloudtimes.app.controller.agent;

import com.cloudtimes.account.domain.CtAgentDividendSettlement;
import com.cloudtimes.account.service.ICtAgentDividendSettlementService;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分润结算审核Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/agent/dividend_settlement")
@Api(tags = "代理-分润结算")
public class CtAgentDividendSettlementController extends BaseController
{
    @Autowired
    private ICtAgentDividendSettlementService ctAgentDividendSettlementService;

    /**
     * 查询分润结算审核列表
     */
    @GetMapping("/list")
    @ApiOperation("查询分润结算审核列表")
    public TableDataInfo list(CtAgentDividendSettlement ctAgentDividendSettlement)
    {
        startPage();
        List<CtAgentDividendSettlement> list = ctAgentDividendSettlementService.selectCtAgentDividendSettlementList(ctAgentDividendSettlement);
        return getDataTable(list);
    }

    /**
     * 获取分润结算审核详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取分润结算审核详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentDividendSettlementService.selectCtAgentDividendSettlementById(id));
    }

    /**
     * 新增分润结算审核
     */
    @Log(title = "分润结算审核", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增分润结算")
    public AjaxResult add(@RequestBody CtAgentDividendSettlement ctAgentDividendSettlement)
    {
        return toAjax(ctAgentDividendSettlementService.insertCtAgentDividendSettlement(ctAgentDividendSettlement));
    }

    /**
     * 修改分润结算审核
     */
    @Log(title = "分润结算审核", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改分润结算")
    public AjaxResult edit(@RequestBody CtAgentDividendSettlement ctAgentDividendSettlement)
    {
        return toAjax(ctAgentDividendSettlementService.updateCtAgentDividendSettlement(ctAgentDividendSettlement));
    }
}
