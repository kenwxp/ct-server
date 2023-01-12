package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.LimitType;
import com.cloudtimes.ybf.domain.YbfMemberAgentReward;
import com.cloudtimes.ybf.service.IYbfMemberAgentRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 会员代理区域奖励Controller
 *
 * @author polo
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/ybf/areareward")
public class AppMemberAgentRewardController extends BaseController {
    @Autowired
    private IYbfMemberAgentRewardService ybfMemberAgentRewardService;

    /**
     * 查询会员代理区域奖励列表
     */
    @GetMapping("/list")
    public AjaxResult list(YbfMemberAgentReward ybfMemberAgentReward) {
        ybfMemberAgentReward.setUsername(getAppUsername());
        List<YbfMemberAgentReward> list = ybfMemberAgentRewardService.selectYbfMemberAgentRewards(ybfMemberAgentReward);
        return AjaxResult.success(list);
    }


}
