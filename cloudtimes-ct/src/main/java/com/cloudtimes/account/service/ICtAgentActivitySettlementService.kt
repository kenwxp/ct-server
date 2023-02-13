package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtAgentActivitySettlement

/**
 * 代理活动结算Service接口
 *
 * @author 沈兵
 * @date 2023-02-13
 */
interface ICtAgentActivitySettlementService {
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
}