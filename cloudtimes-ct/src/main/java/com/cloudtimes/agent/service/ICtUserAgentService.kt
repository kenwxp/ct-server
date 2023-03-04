package com.cloudtimes.agent.service

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.dto.request.AgentStoreListRequest
import com.cloudtimes.account.dto.request.TransferCashRequest
import com.cloudtimes.account.dto.request.WithdrawCashRequest
import com.cloudtimes.agent.dto.response.AgentStoreOnlineStats
import com.cloudtimes.account.dto.response.StoreAndCommission
import com.cloudtimes.account.dto.response.TeamMember
import com.cloudtimes.agent.dto.request.AgentStoreDetailRequest
import com.cloudtimes.agent.dto.request.StoreProfitRequest
import com.cloudtimes.agent.dto.response.AgentAssets
import com.cloudtimes.agent.dto.response.AgentStoreProfitStats

/**
 * 代理Service接口
 *
 * @author 沈兵
 * @date 2023-02-07
 */
interface ICtUserAgentService {
    /** 修改代理类型/状态 */
    fun updateAgentStatus(user: CtUser): Int

    fun selectTeamMember(userId: String): TeamMember?

    fun selectTeamMembers(userId: String): List<TeamMember>

    fun selectAgentAssets(userId: String): AgentAssets?


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
    fun selectCtAgentShopList(request: AgentStoreListRequest): List<StoreAndCommission>

    /** 查询代理门店详情 */
    fun selectCtAgentShopDetail(request: AgentStoreDetailRequest): StoreAndCommission?

    /** 查询代理门店上线统计 */
    fun selectCtAgentShopOnlineStats(userId: String): List<AgentStoreOnlineStats>

    /** 查询代理门店收益统计 */
    fun selectCtAgentShopProfitStats(request: StoreProfitRequest): AgentStoreProfitStats

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
     * 删除代理信息
     *
     * @param userId 代理主键
     * @return 结果
     */
    fun deleteCtUserAgentByUserId(userId: String): Int

    /** 提现 */
    fun withdrawCash(request: WithdrawCashRequest)

    /** 转账 */
    fun transferCash(transferCashRequest: TransferCashRequest)
}
