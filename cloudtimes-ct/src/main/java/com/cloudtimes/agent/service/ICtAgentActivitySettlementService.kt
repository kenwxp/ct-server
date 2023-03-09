package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivitySettlement
import com.cloudtimes.agent.dto.request.ActivityRuleRequest
import com.cloudtimes.agent.dto.response.CtAgentActivitySettlementDto

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

    fun selectCtAgentActivitySettlementListPlus(ctAgentActivitySettlement: CtAgentActivitySettlement): List<CtAgentActivitySettlementDto>

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
}
