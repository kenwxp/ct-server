package com.cloudtimes.app.controller.agent;

import com.cloudtimes.account.domain.CtAgentActivity;
import com.cloudtimes.account.service.ICtAgentActivityService;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 代理活动Controller
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
@RestController
@RequestMapping("/v1/agent/agent_activity")
@Api(tags = "代理活动")
public class CtAgentActivityController extends BaseController
{
    @Autowired
    private ICtAgentActivityService ctAgentActivityService;

    /**
     * 查询代理活动列表
     */
    @GetMapping("/list")
    @ApiOperation("查询代理活动列表")
    public TableDataInfo list(CtAgentActivity ctAgentActivity)
    {
        startPage();
        List<CtAgentActivity> list = ctAgentActivityService.selectCtAgentActivityList(ctAgentActivity);
        return getDataTable(list);
    }

    /**
     * 获取代理活动详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取代理活动详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentActivityService.selectCtAgentActivityById(id));
    }

    /**
     * 新增代理活动
     */
    @Log(title = "代理活动", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增代理活动")
    public AjaxResult add(@RequestBody CtAgentActivity ctAgentActivity)
    {
        var user = getLoginUser();
        ctAgentActivity.setOperator(user.getUsername());
        return toAjax(ctAgentActivityService.insertCtAgentActivity(ctAgentActivity));
    }

    /**
     * 修改代理活动
     */
    @Log(title = "代理活动", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改代理活动")
    public AjaxResult edit(@RequestBody CtAgentActivity ctAgentActivity)
    {
        var user = getLoginUser();
        ctAgentActivity.setOperator(user.getUsername());
        return toAjax(ctAgentActivityService.updateCtAgentActivity(ctAgentActivity));
    }
}
