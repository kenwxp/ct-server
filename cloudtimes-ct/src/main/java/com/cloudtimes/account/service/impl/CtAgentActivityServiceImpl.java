package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtAgentActivityMapper;
import com.cloudtimes.account.domain.CtAgentActivity;
import com.cloudtimes.account.service.ICtAgentActivityService;

/**
 * 代理活动Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
public class CtAgentActivityServiceImpl implements ICtAgentActivityService 
{
    @Autowired
    private CtAgentActivityMapper ctAgentActivityMapper;

    /**
     * 查询代理活动
     * 
     * @param id 代理活动主键
     * @return 代理活动
     */
    @Override
    public CtAgentActivity selectCtAgentActivityById(String id)
    {
        return ctAgentActivityMapper.selectCtAgentActivityById(id);
    }

    /**
     * 查询代理活动列表
     * 
     * @param ctAgentActivity 代理活动
     * @return 代理活动
     */
    @Override
    public List<CtAgentActivity> selectCtAgentActivityList(CtAgentActivity ctAgentActivity)
    {
        return ctAgentActivityMapper.selectCtAgentActivityList(ctAgentActivity);
    }

    /**
     * 新增代理活动
     * 
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    @Override
    public int insertCtAgentActivity(CtAgentActivity ctAgentActivity)
    {
        ctAgentActivity.setCreateTime(DateUtils.getNowDate());
        return ctAgentActivityMapper.insertCtAgentActivity(ctAgentActivity);
    }

    /**
     * 修改代理活动
     * 
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    @Override
    public int updateCtAgentActivity(CtAgentActivity ctAgentActivity)
    {
        ctAgentActivity.setUpdateTime(DateUtils.getNowDate());
        return ctAgentActivityMapper.updateCtAgentActivity(ctAgentActivity);
    }

    /**
     * 批量删除代理活动
     * 
     * @param ids 需要删除的代理活动主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentActivityByIds(String[] ids)
    {
        return ctAgentActivityMapper.deleteCtAgentActivityByIds(ids);
    }

    /**
     * 删除代理活动信息
     * 
     * @param id 代理活动主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentActivityById(String id)
    {
        return ctAgentActivityMapper.deleteCtAgentActivityById(id);
    }
}
