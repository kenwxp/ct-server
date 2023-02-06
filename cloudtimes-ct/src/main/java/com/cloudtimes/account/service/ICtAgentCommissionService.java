package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentCommission;

/**
 * 代理销售佣金设置Service接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface ICtAgentCommissionService 
{
    /**
     * 查询代理销售佣金设置
     * 
     * @param id 代理销售佣金设置主键
     * @return 代理销售佣金设置
     */
    public CtAgentCommission selectCtAgentCommissionById(String id);

    /**
     * 查询代理销售佣金设置列表
     * 
     * @param ctAgentCommission 代理销售佣金设置
     * @return 代理销售佣金设置集合
     */
    public List<CtAgentCommission> selectCtAgentCommissionList(CtAgentCommission ctAgentCommission);

    /**
     * 新增代理销售佣金设置
     * 
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    public int insertCtAgentCommission(CtAgentCommission ctAgentCommission);

    /**
     * 修改代理销售佣金设置
     * 
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    public int updateCtAgentCommission(CtAgentCommission ctAgentCommission);

    /**
     * 批量删除代理销售佣金设置
     * 
     * @param ids 需要删除的代理销售佣金设置主键集合
     * @return 结果
     */
    public int deleteCtAgentCommissionByIds(String[] ids);

    /**
     * 删除代理销售佣金设置信息
     * 
     * @param id 代理销售佣金设置主键
     * @return 结果
     */
    public int deleteCtAgentCommissionById(String id);
}
