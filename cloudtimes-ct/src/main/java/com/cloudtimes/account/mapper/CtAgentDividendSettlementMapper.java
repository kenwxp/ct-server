package com.cloudtimes.account.mapper;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentDividendSettlement;

/**
 * 分润结算审核Mapper接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface CtAgentDividendSettlementMapper 
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
     * 删除分润结算审核
     * 
     * @param id 分润结算审核主键
     * @return 结果
     */
    public int deleteCtAgentDividendSettlementById(String id);

    /**
     * 批量删除分润结算审核
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtAgentDividendSettlementByIds(String[] ids);
}
