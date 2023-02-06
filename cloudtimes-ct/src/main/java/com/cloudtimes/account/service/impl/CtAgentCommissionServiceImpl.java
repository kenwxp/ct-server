package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtAgentCommissionMapper;
import com.cloudtimes.account.domain.CtAgentCommission;
import com.cloudtimes.account.service.ICtAgentCommissionService;

/**
 * 代理销售佣金设置Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtAgentCommissionServiceImpl implements ICtAgentCommissionService 
{
    @Autowired
    private CtAgentCommissionMapper ctAgentCommissionMapper;

    /**
     * 查询代理销售佣金设置
     * 
     * @param id 代理销售佣金设置主键
     * @return 代理销售佣金设置
     */
    @Override
    public CtAgentCommission selectCtAgentCommissionById(String id)
    {
        return ctAgentCommissionMapper.selectCtAgentCommissionById(id);
    }

    /**
     * 查询代理销售佣金设置列表
     * 
     * @param ctAgentCommission 代理销售佣金设置
     * @return 代理销售佣金设置
     */
    @Override
    public List<CtAgentCommission> selectCtAgentCommissionList(CtAgentCommission ctAgentCommission)
    {
        return ctAgentCommissionMapper.selectCtAgentCommissionList(ctAgentCommission);
    }

    /**
     * 新增代理销售佣金设置
     * 
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    @Override
    public int insertCtAgentCommission(CtAgentCommission ctAgentCommission)
    {
        ctAgentCommission.setCreateTime(DateUtils.getNowDate());
        return ctAgentCommissionMapper.insertCtAgentCommission(ctAgentCommission);
    }

    /**
     * 修改代理销售佣金设置
     * 
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    @Override
    public int updateCtAgentCommission(CtAgentCommission ctAgentCommission)
    {
        ctAgentCommission.setUpdateTime(DateUtils.getNowDate());
        return ctAgentCommissionMapper.updateCtAgentCommission(ctAgentCommission);
    }

    /**
     * 批量删除代理销售佣金设置
     * 
     * @param ids 需要删除的代理销售佣金设置主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentCommissionByIds(String[] ids)
    {
        return ctAgentCommissionMapper.deleteCtAgentCommissionByIds(ids);
    }

    /**
     * 删除代理销售佣金设置信息
     * 
     * @param id 代理销售佣金设置主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentCommissionById(String id)
    {
        return ctAgentCommissionMapper.deleteCtAgentCommissionById(id);
    }
}
