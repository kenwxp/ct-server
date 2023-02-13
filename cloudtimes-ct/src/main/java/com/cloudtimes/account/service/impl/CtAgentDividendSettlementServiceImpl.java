package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtAgentDividendSettlementMapper;
import com.cloudtimes.account.domain.CtAgentDividendSettlement;
import com.cloudtimes.account.service.ICtAgentDividendSettlementService;

/**
 * 分润结算审核Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtAgentDividendSettlementServiceImpl implements ICtAgentDividendSettlementService 
{
    @Autowired
    private CtAgentDividendSettlementMapper ctAgentDividendSettlementMapper;

    /**
     * 查询分润结算审核
     * 
     * @param id 分润结算审核主键
     * @return 分润结算审核
     */
    @Override
    public CtAgentDividendSettlement selectCtAgentDividendSettlementById(String id)
    {
        return ctAgentDividendSettlementMapper.selectCtAgentDividendSettlementById(id);
    }

    /**
     * 查询分润结算审核列表
     * 
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 分润结算审核
     */
    @Override
    public List<CtAgentDividendSettlement> selectCtAgentDividendSettlementList(CtAgentDividendSettlement ctAgentDividendSettlement)
    {
        return ctAgentDividendSettlementMapper.selectCtAgentDividendSettlementList(ctAgentDividendSettlement);
    }

    /**
     * 新增分润结算审核
     * 
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    @Override
    public int insertCtAgentDividendSettlement(CtAgentDividendSettlement ctAgentDividendSettlement)
    {
        ctAgentDividendSettlement.setCreateTime(DateUtils.getNowDate());
        return ctAgentDividendSettlementMapper.insertCtAgentDividendSettlement(ctAgentDividendSettlement);
    }

    /**
     * 修改分润结算审核
     * 
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    @Override
    public int updateCtAgentDividendSettlement(CtAgentDividendSettlement ctAgentDividendSettlement)
    {
        ctAgentDividendSettlement.setUpdateTime(DateUtils.getNowDate());
        return ctAgentDividendSettlementMapper.updateCtAgentDividendSettlement(ctAgentDividendSettlement);
    }
}
