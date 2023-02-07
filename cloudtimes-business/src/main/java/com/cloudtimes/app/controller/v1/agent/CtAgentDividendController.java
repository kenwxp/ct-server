package com.cloudtimes.app.controller.v1.agent;

import com.cloudtimes.account.domain.CtAgentDividend;
import com.cloudtimes.account.service.ICtAgentDividendService;
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
 * 分润配置Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/v1/agent/agent_dividend")
@Api(tags = "代理分润")
public class CtAgentDividendController extends BaseController
{
    @Autowired
    private ICtAgentDividendService ctAgentDividendService;

    /**
     * 查询分润配置列表
     */
    @GetMapping("/list")
    @ApiOperation("查询分润配置列表")
    public TableDataInfo list(CtAgentDividend ctAgentDividend)
    {
        startPage();
        List<CtAgentDividend> list = ctAgentDividendService.selectCtAgentDividendList(ctAgentDividend);
        return getDataTable(list);
    }

    /**
     * 获取分润配置详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取分润配置详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentDividendService.selectCtAgentDividendById(id));
    }

    /**
     * 新增分润配置
     */
    @Log(title = "分润配置", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增分润配置")
    public AjaxResult add(@RequestBody CtAgentDividend ctAgentDividend)
    {
        return toAjax(ctAgentDividendService.insertCtAgentDividend(ctAgentDividend));
    }

    /**
     * 修改分润配置
     */
    @Log(title = "分润配置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改分润配置")
    public AjaxResult edit(@RequestBody CtAgentDividend ctAgentDividend)
    {
        return toAjax(ctAgentDividendService.updateCtAgentDividend(ctAgentDividend));
    }
}
