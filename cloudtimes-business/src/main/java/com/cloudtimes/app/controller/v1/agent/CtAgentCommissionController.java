package com.cloudtimes.app.controller.v1.agent;

import com.cloudtimes.account.domain.CtAgentCommission;
import com.cloudtimes.account.service.ICtAgentCommissionService;
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
 * 代理销售佣金设置Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/v1/agent/agent_commission")
@Api(tags = "代理佣金")
public class CtAgentCommissionController extends BaseController
{
    @Autowired
    private ICtAgentCommissionService ctAgentCommissionService;

    /**
     * 查询代理销售佣金设置列表
     */
    @GetMapping("/list")
    @ApiOperation("查询代理销售佣金设置列表")
    public TableDataInfo list(CtAgentCommission ctAgentCommission)
    {
        startPage();
        List<CtAgentCommission> list = ctAgentCommissionService.selectCtAgentCommissionList(ctAgentCommission);
        return getDataTable(list);
    }

    /**
     * 获取代理销售佣金设置详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取代理销售佣金设置")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentCommissionService.selectCtAgentCommissionById(id));
    }

    /**
     * 新增代理销售佣金设置
     */
    @Log(title = "代理销售佣金设置", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增代理销售佣金")
    public AjaxResult add(@RequestBody CtAgentCommission ctAgentCommission)
    {
        var user = getLoginUser();
        ctAgentCommission.setOperator(user.getUsername());
        return toAjax(ctAgentCommissionService.insertCtAgentCommission(ctAgentCommission));
    }

    /**
     * 修改代理销售佣金设置
     */
    @Log(title = "代理销售佣金设置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改代理销售佣金")
    public AjaxResult edit(@RequestBody CtAgentCommission ctAgentCommission)
    {
        var user = getLoginUser();
        ctAgentCommission.setOperator(user.getUsername());
        return toAjax(ctAgentCommissionService.updateCtAgentCommission(ctAgentCommission));
    }
}
