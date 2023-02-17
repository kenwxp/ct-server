package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentCommission
import com.cloudtimes.account.dto.request.UpdateSubUserCommissionRequest

/**
 * 代理销售佣金设置Service接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
interface ICtAgentCommissionService {
    fun selectCtAgentCommissionByUserId(userId: String): CtAgentCommission?

    fun updateSubUserCommission(request: UpdateSubUserCommissionRequest): Int

    /**
     * 查询代理销售佣金设置
     *
     * @param id 代理销售佣金设置主键
     * @return 代理销售佣金设置
     */
    fun selectCtAgentCommissionById(id: String): CtAgentCommission?

    /**
     * 查询代理销售佣金设置列表
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 代理销售佣金设置集合
     */
    fun selectCtAgentCommissionList(ctAgentCommission: CtAgentCommission): List<CtAgentCommission>

    /**
     * 新增代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    fun insertCtAgentCommission(ctAgentCommission: CtAgentCommission): Int

    /**
     * 修改代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    fun updateCtAgentCommission(ctAgentCommission: CtAgentCommission): Int

    /**
     * 批量删除代理销售佣金设置
     *
     * @param ids 需要删除的代理销售佣金设置主键集合
     * @return 结果
     */
    fun deleteCtAgentCommissionByIds(ids: Array<String>): Int

    /**
     * 删除代理销售佣金设置信息
     *
     * @param id 代理销售佣金设置主键
     * @return 结果
     */
    fun deleteCtAgentCommissionById(id: String): Int
}
