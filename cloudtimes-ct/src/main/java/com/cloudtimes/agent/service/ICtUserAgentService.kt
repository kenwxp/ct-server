package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.dto.request.AgentStoreRequest
import com.cloudtimes.account.dto.request.TransferCashRequest
import com.cloudtimes.account.dto.request.WithdrawCashRequest
import com.cloudtimes.agent.dto.response.AgentShopStats
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.account.dto.response.TeamMember

/**
 * 代理Service接口
 *
 * @author 沈兵
 * @date 2023-02-07
 */
interface ICtUserAgentService {
    fun selectTeamMember(userId: String): TeamMember?

    fun selectTeamMembers(userId: String): List<TeamMember>

    /**
     * 查询代理
     *
     * @param userId 代理主键
     * @return 代理
     */
    fun selectCtUserAgentByUserId(userId: String): CtUserAgent?

    /**
     * 查询代理列表
     *
     * @param ctUserAgent 代理
     * @return 代理集合
     */
    fun selectCtUserAgentList(ctUserAgent: CtUserAgent): List<CtUserAgent>


    /** 查询代理门店列表 */
    fun selectCtAgentShopList(agentStoreRequest: AgentStoreRequest): List<StoreAndCommission>

    /** 查询代理门店上线统计 */
    fun selectCtAgentShopStats(userId: String): List<AgentShopStats>

    /**
     * 新增代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun insertCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 修改代理
     *
     * @param ctUserAgent 代理
     * @return 结果
     */
    fun updateCtUserAgent(ctUserAgent: CtUserAgent): Int

    /**
     * 批量删除代理
     *
     * @param userIds 需要删除的代理主键集合
     * @return 结果
     */
    fun deleteCtUserAgentByUserIds(userIds: Array<String>): Int

    /**
     * 删除代理信息
     *
     * @param userId 代理主键
     * @return 结果
     */
    fun deleteCtUserAgentByUserId(userId: String): Int

    /** 提现 */
    fun withdrawCash(withdrawCashRequest: WithdrawCashRequest)

    /** 转账 */
    fun transferCash(transferCashRequest: TransferCashRequest)
}
