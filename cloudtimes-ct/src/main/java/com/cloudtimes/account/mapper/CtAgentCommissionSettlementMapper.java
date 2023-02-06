package com.cloudtimes.account.mapper;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentCommissionSettlement;

/**
 * 销售佣金结算Mapper接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface CtAgentCommissionSettlementMapper 
{
    /**
     * 查询销售佣金结算
     * 
     * @param id 销售佣金结算主键
     * @return 销售佣金结算
     */
    public CtAgentCommissionSettlement selectCtAgentCommissionSettlementById(String id);

    /**
     * 查询销售佣金结算列表
     * 
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 销售佣金结算集合
     */
    public List<CtAgentCommissionSettlement> selectCtAgentCommissionSettlementList(CtAgentCommissionSettlement ctAgentCommissionSettlement);

    /**
     * 新增销售佣金结算
     * 
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    public int insertCtAgentCommissionSettlement(CtAgentCommissionSettlement ctAgentCommissionSettlement);

    /**
     * 修改销售佣金结算
     * 
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    public int updateCtAgentCommissionSettlement(CtAgentCommissionSettlement ctAgentCommissionSettlement);

    /**
     * 删除销售佣金结算
     * 
     * @param id 销售佣金结算主键
     * @return 结果
     */
    public int deleteCtAgentCommissionSettlementById(String id);

    /**
     * 批量删除销售佣金结算
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtAgentCommissionSettlementByIds(String[] ids);
}
