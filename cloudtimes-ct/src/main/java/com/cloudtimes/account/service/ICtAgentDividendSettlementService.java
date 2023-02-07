package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentDividendSettlement;

/**
 * 分润结算审核Service接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface ICtAgentDividendSettlementService 
{
    /**
     * 查询分润结算审核
     * 
     * @param id 分润结算审核主键
     * @return 分润结算审核
     */
    public CtAgentDividendSettlement selectCtAgentDividendSettlementById(String id);

    /**
     * 查询分润结算审核列表
     * 
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 分润结算审核集合
     */
    public List<CtAgentDividendSettlement> selectCtAgentDividendSettlementList(CtAgentDividendSettlement ctAgentDividendSettlement);

    /**
     * 新增分润结算审核
     * 
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    public int insertCtAgentDividendSettlement(CtAgentDividendSettlement ctAgentDividendSettlement);

    /**
     * 修改分润结算审核
     * 
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    public int updateCtAgentDividendSettlement(CtAgentDividendSettlement ctAgentDividendSettlement);

    /**
     * 批量删除分润结算审核
     * 
     * @param ids 需要删除的分润结算审核主键集合
     * @return 结果
     */
    public int deleteCtAgentDividendSettlementByIds(String[] ids);

    /**
     * 删除分润结算审核信息
     * 
     * @param id 分润结算审核主键
     * @return 结果
     */
    public int deleteCtAgentDividendSettlementById(String id);
}