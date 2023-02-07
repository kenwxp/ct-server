package com.cloudtimes.app.controller.v1.agent;

import com.cloudtimes.account.domain.CtAgentActivityReward;
import com.cloudtimes.account.service.ICtAgentActivityRewardService;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 代理活动奖励维护Controller
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
@RestController
@RequestMapping("/v1/agent/activity_reward")
@Api(tags = "代理活动奖励")
public class CtAgentActivityRewardController extends BaseController
{
    @Autowired
    private ICtAgentActivityRewardService ctAgentActivityRewardService;

    /**
     * 查询代理活动奖励维护列表
     */
    @GetMapping("/list")
    @ApiOperation("查询代理活动奖励列表")
    public TableDataInfo list(CtAgentActivityReward ctAgentActivityReward)
    {
        startPage();
        List<CtAgentActivityReward> list = ctAgentActivityRewardService.selectCtAgentActivityRewardList(ctAgentActivityReward);
        return getDataTable(list);
    }

    /**
     * 获取代理活动奖励维护详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取代理活动奖详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentActivityRewardService.selectCtAgentActivityRewardById(id));
    }

    /**
     * 新增代理活动奖励维护
     */
    @Log(title = "代理活动奖励维护", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增代理活动奖励")
    public AjaxResult add(@RequestBody CtAgentActivityReward ctAgentActivityReward)
    {
        var user = getLoginUser();
        ctAgentActivityReward.setOperator(user.getUsername());
        return toAjax(ctAgentActivityRewardService.insertCtAgentActivityReward(ctAgentActivityReward));
    }

    /**
     * 修改代理活动奖励维护
     */
    @Log(title = "代理活动奖励维护", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改代理活动奖励")
    public AjaxResult edit(@RequestBody CtAgentActivityReward ctAgentActivityReward)
    {
        var user = getLoginUser();
        ctAgentActivityReward.setOperator(user.getUsername());
        return toAjax(ctAgentActivityRewardService.updateCtAgentActivityReward(ctAgentActivityReward));
    }
}
