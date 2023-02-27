package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentDividendSettlement
import com.cloudtimes.agent.dto.request.AgentDividendRequest
import com.cloudtimes.agent.dto.request.StoreDividendRequest

/**
 * 分润结算审核Service接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface ICtAgentDividendSettlementService {
    /** 查询店铺分润列表 */
    fun selectStoreDividendList(request: StoreDividendRequest): List<CtAgentDividendSettlement>

    /** 代理确认分润 */
    fun agentApproveDividend(request: AgentDividendRequest): Int

    /** 代理分润详情 */
    fun agentDividendDetail(request: AgentDividendRequest): CtAgentDividendSettlement?

    /**
     * 查询分润结算审核
     *
     * @param id 分润结算审核主键
     * @return 分润结算审核
     */
    fun selectCtAgentDividendSettlementById(id: String): CtAgentDividendSettlement?

    /**
     * 查询分润结算审核列表
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 分润结算审核集合
     */
    fun selectCtAgentDividendSettlementList(ctAgentDividendSettlement: CtAgentDividendSettlement): List<CtAgentDividendSettlement>

    /**
     * 新增分润结算审核
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    fun insertCtAgentDividendSettlement(ctAgentDividendSettlement: CtAgentDividendSettlement): Int

    /**
     * 修改分润结算审核
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    fun updateCtAgentDividendSettlement(ctAgentDividendSettlement: CtAgentDividendSettlement): Int
}