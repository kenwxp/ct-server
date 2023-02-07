package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentActivityReward;

/**
 * 代理活动奖励维护Service接口
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
public interface ICtAgentActivityRewardService 
{
    /**
     * 查询代理活动奖励维护
     * 
     * @param id 代理活动奖励维护主键
     * @return 代理活动奖励维护
     */
    public CtAgentActivityReward selectCtAgentActivityRewardById(String id);

    /**
     * 查询代理活动奖励维护列表
     * 
     * @param ctAgentActivityReward 代理活动奖励维护
     * @return 代理活动奖励维护集合
     */
    public List<CtAgentActivityReward> selectCtAgentActivityRewardList(CtAgentActivityReward ctAgentActivityReward);

    /**
     * 新增代理活动奖励维护
     * 
     * @param ctAgentActivityReward 代理活动奖励维护
     * @return 结果
     */
    public int insertCtAgentActivityReward(CtAgentActivityReward ctAgentActivityReward);

    /**
     * 修改代理活动奖励维护
     * 
     * @param ctAgentActivityReward 代理活动奖励维护
     * @return 结果
     */
    public int updateCtAgentActivityReward(CtAgentActivityReward ctAgentActivityReward);

    /**
     * 批量删除代理活动奖励维护
     * 
     * @param ids 需要删除的代理活动奖励维护主键集合
     * @return 结果
     */
    public int deleteCtAgentActivityRewardByIds(String[] ids);

    /**
     * 删除代理活动奖励维护信息
     * 
     * @param id 代理活动奖励维护主键
     * @return 结果
     */
    public int deleteCtAgentActivityRewardById(String id);
}
