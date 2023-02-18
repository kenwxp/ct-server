package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivitySettlement
import com.cloudtimes.agent.dto.request.ActivityRuleRequest

/**
 * 代理活动结算Service接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
interface ICtAgentActivitySettlementService {
    fun agentConfirm(request: ActivityRuleRequest): Int

    /**
     * 查询代理活动结算
     *
     * @param id 代理活动结算主键
     * @return 代理活动结算
     */
    fun selectCtAgentActivitySettlementById(id: String): CtAgentActivitySettlement?

    /**
     * 查询代理活动结算列表
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 代理活动结算集合
     */
    fun selectCtAgentActivitySettlementList(ctAgentActivitySettlement: CtAgentActivitySettlement): List<CtAgentActivitySettlement>

    /**
     * 新增代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    fun insertCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int

    /**
     * 修改代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    fun updateCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int

    /**
     * 批量删除代理活动结算
     *
     * @param ids 需要删除的代理活动结算主键集合
     * @return 结果
     */
    fun deleteCtAgentActivitySettlementByIds(ids: Array<String>): Int

    /**
     * 删除代理活动结算信息
     *
     * @param id 代理活动结算主键
     * @return 结果
     */
    fun deleteCtAgentActivitySettlementById(id: String): Int
}
