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
import com.cloudtimes.account.domain.CtAgentActivityReward;
import com.cloudtimes.account.service.ICtAgentActivityRewardService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 代理活动奖励维护Controller
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
@RestController
@RequestMapping("/account/activity_reward")
public class CtAgentActivityRewardController extends BaseController
{
    @Autowired
    private ICtAgentActivityRewardService ctAgentActivityRewardService;

    /**
     * 查询代理活动奖励维护列表
     */
    @PreAuthorize("@ss.hasPermi('account:activity_reward:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtAgentActivityReward ctAgentActivityReward)
    {
        startPage();
        List<CtAgentActivityReward> list = ctAgentActivityRewardService.selectCtAgentActivityRewardList(ctAgentActivityReward);
        return getDataTable(list);
    }

    /**
     * 导出代理活动奖励维护列表
     */
    @PreAuthorize("@ss.hasPermi('account:activity_reward:export')")
    @Log(title = "代理活动奖励维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtAgentActivityReward ctAgentActivityReward)
    {
        List<CtAgentActivityReward> list = ctAgentActivityRewardService.selectCtAgentActivityRewardList(ctAgentActivityReward);
        ExcelUtil<CtAgentActivityReward> util = new ExcelUtil<CtAgentActivityReward>(CtAgentActivityReward.class);
        util.exportExcel(response, list, "代理活动奖励维护数据");
    }

    /**
     * 获取代理活动奖励维护详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:activity_reward:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctAgentActivityRewardService.selectCtAgentActivityRewardById(id));
    }

    /**
     * 新增代理活动奖励维护
     */
    @PreAuthorize("@ss.hasPermi('account:activity_reward:add')")
    @Log(title = "代理活动奖励维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtAgentActivityReward ctAgentActivityReward)
    {
        var user = getLoginUser();
        ctAgentActivityReward.setOperator(user.getUsername());
        return toAjax(ctAgentActivityRewardService.insertCtAgentActivityReward(ctAgentActivityReward));
    }

    /**
     * 修改代理活动奖励维护
     */
    @PreAuthorize("@ss.hasPermi('account:activity_reward:edit')")
    @Log(title = "代理活动奖励维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtAgentActivityReward ctAgentActivityReward)
    {
        var user = getLoginUser();
        ctAgentActivityReward.setOperator(user.getUsername());
        return toAjax(ctAgentActivityRewardService.updateCtAgentActivityReward(ctAgentActivityReward));
    }

    /**
     * 删除代理活动奖励维护
     */
    @PreAuthorize("@ss.hasPermi('account:activity_reward:remove')")
    @Log(title = "代理活动奖励维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctAgentActivityRewardService.deleteCtAgentActivityRewardByIds(ids));
    }
}
