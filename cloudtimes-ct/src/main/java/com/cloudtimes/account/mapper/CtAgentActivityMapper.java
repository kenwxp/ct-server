package com.cloudtimes.account.mapper;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentActivity;

/**
 * 代理活动Mapper接口
 * 
 * @author 沈兵
 * @date 2023-02-02
 */
public interface CtAgentActivityMapper 
{
    /**
     * 查询代理活动
     * 
     * @param id 代理活动主键
     * @return 代理活动
     */
    public CtAgentActivity selectCtAgentActivityById(String id);

    /**
     * 查询代理活动列表
     * 
     * @param ctAgentActivity 代理活动
     * @return 代理活动集合
     */
    public List<CtAgentActivity> selectCtAgentActivityList(CtAgentActivity ctAgentActivity);

    /**
     * 新增代理活动
     * 
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    public int insertCtAgentActivity(CtAgentActivity ctAgentActivity);

    /**
     * 修改代理活动
     * 
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    public int updateCtAgentActivity(CtAgentActivity ctAgentActivity);

    /**
     * 删除代理活动
     * 
     * @param id 代理活动主键
     * @return 结果
     */
    public int deleteCtAgentActivityById(String id);

    /**
     * 批量删除代理活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtAgentActivityByIds(String[] ids);
}
