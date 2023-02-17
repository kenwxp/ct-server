package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentCommissionSettlement

/**
 * 销售佣金结算Service接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface ICtAgentCommissionSettlementService {
    /**
     * 查询销售佣金结算
     *
     * @param id 销售佣金结算主键
     * @return 销售佣金结算
     */
    fun selectCtAgentCommissionSettlementById(id: String): CtAgentCommissionSettlement?

    /**
     * 查询销售佣金结算列表
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 销售佣金结算集合
     */
    fun selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement: CtAgentCommissionSettlement): List<CtAgentCommissionSettlement>

    /**
     * 新增销售佣金结算
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    fun insertCtAgentCommissionSettlement(ctAgentCommissionSettlement: CtAgentCommissionSettlement): Int

    /**
     * 修改销售佣金结算
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    fun updateCtAgentCommissionSettlement(ctAgentCommissionSettlement: CtAgentCommissionSettlement): Int

    /**
     * 代理佣金确认
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    fun agentConfirmCtAgentCommissionSettlementById(id: String): Int

    /**
     * 平台佣金确认
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    fun platformConfirmCtAgentCommissionSettlementById(id: String): Int

    /**
     * 批量删除销售佣金结算
     *
     * @param ids 需要删除的销售佣金结算主键集合
     * @return 结果
     */
    fun deleteCtAgentCommissionSettlementByIds(ids: Array<String>): Int

    /**
     * 删除销售佣金结算信息
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    fun deleteCtAgentCommissionSettlementById(id: String): Int
}
