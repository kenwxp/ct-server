package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtAgentCommissionSettlementMapper;
import com.cloudtimes.account.domain.CtAgentCommissionSettlement;
import com.cloudtimes.account.service.ICtAgentCommissionSettlementService;

/**
 * 销售佣金结算Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtAgentCommissionSettlementServiceImpl implements ICtAgentCommissionSettlementService 
{
    @Autowired
    private CtAgentCommissionSettlementMapper ctAgentCommissionSettlementMapper;

    /**
     * 查询销售佣金结算
     * 
     * @param id 销售佣金结算主键
     * @return 销售佣金结算
     */
    @Override
    public CtAgentCommissionSettlement selectCtAgentCommissionSettlementById(String id)
    {
        return ctAgentCommissionSettlementMapper.selectCtAgentCommissionSettlementById(id);
    }

    /**
     * 查询销售佣金结算列表
     * 
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 销售佣金结算
     */
    @Override
    public List<CtAgentCommissionSettlement> selectCtAgentCommissionSettlementList(CtAgentCommissionSettlement ctAgentCommissionSettlement)
    {
        return ctAgentCommissionSettlementMapper.selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement);
    }

    /**
     * 新增销售佣金结算
     * 
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    @Override
    public int insertCtAgentCommissionSettlement(CtAgentCommissionSettlement ctAgentCommissionSettlement)
    {
        ctAgentCommissionSettlement.setCreateTime(DateUtils.getNowDate());
        return ctAgentCommissionSettlementMapper.insertCtAgentCommissionSettlement(ctAgentCommissionSettlement);
    }

    /**
     * 修改销售佣金结算
     * 
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    @Override
    public int updateCtAgentCommissionSettlement(CtAgentCommissionSettlement ctAgentCommissionSettlement)
    {
        ctAgentCommissionSettlement.setUpdateTime(DateUtils.getNowDate());
        return ctAgentCommissionSettlementMapper.updateCtAgentCommissionSettlement(ctAgentCommissionSettlement);
    }

    /**
     * 批量删除销售佣金结算
     * 
     * @param ids 需要删除的销售佣金结算主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentCommissionSettlementByIds(String[] ids)
    {
        return ctAgentCommissionSettlementMapper.deleteCtAgentCommissionSettlementByIds(ids);
    }

    /**
     * 删除销售佣金结算信息
     * 
     * @param id 销售佣金结算主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentCommissionSettlementById(String id)
    {
        return ctAgentCommissionSettlementMapper.deleteCtAgentCommissionSettlementById(id);
    }
}
