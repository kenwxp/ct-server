package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtAgentActivityRewardMapper;
import com.cloudtimes.account.domain.CtAgentActivityReward;
import com.cloudtimes.account.service.ICtAgentActivityRewardService;

/**
 * 代理活动奖励维护Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
public class CtAgentActivityRewardServiceImpl implements ICtAgentActivityRewardService 
{
    @Autowired
    private CtAgentActivityRewardMapper ctAgentActivityRewardMapper;

    /**
     * 查询代理活动奖励维护
     * 
     * @param id 代理活动奖励维护主键
     * @return 代理活动奖励维护
     */
    @Override
    public CtAgentActivityReward selectCtAgentActivityRewardById(String id)
    {
        return ctAgentActivityRewardMapper.selectCtAgentActivityRewardById(id);
    }

    /**
     * 查询代理活动奖励维护列表
     * 
     * @param ctAgentActivityReward 代理活动奖励维护
     * @return 代理活动奖励维护
     */
    @Override
    public List<CtAgentActivityReward> selectCtAgentActivityRewardList(CtAgentActivityReward ctAgentActivityReward)
    {
        return ctAgentActivityRewardMapper.selectCtAgentActivityRewardList(ctAgentActivityReward);
    }

    /**
     * 新增代理活动奖励维护
     * 
     * @param ctAgentActivityReward 代理活动奖励维护
     * @return 结果
     */
    @Override
    public int insertCtAgentActivityReward(CtAgentActivityReward ctAgentActivityReward)
    {
        ctAgentActivityReward.setCreateTime(DateUtils.getNowDate());
        return ctAgentActivityRewardMapper.insertCtAgentActivityReward(ctAgentActivityReward);
    }

    /**
     * 修改代理活动奖励维护
     * 
     * @param ctAgentActivityReward 代理活动奖励维护
     * @return 结果
     */
    @Override
    public int updateCtAgentActivityReward(CtAgentActivityReward ctAgentActivityReward)
    {
        ctAgentActivityReward.setUpdateTime(DateUtils.getNowDate());
        return ctAgentActivityRewardMapper.updateCtAgentActivityReward(ctAgentActivityReward);
    }

    /**
     * 批量删除代理活动奖励维护
     * 
     * @param ids 需要删除的代理活动奖励维护主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentActivityRewardByIds(String[] ids)
    {
        return ctAgentActivityRewardMapper.deleteCtAgentActivityRewardByIds(ids);
    }

    /**
     * 删除代理活动奖励维护信息
     * 
     * @param id 代理活动奖励维护主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentActivityRewardById(String id)
    {
        return ctAgentActivityRewardMapper.deleteCtAgentActivityRewardById(id);
    }
}
