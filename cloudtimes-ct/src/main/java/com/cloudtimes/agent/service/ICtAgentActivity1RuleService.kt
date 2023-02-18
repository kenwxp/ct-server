package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivity1Rule
import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.dto.request.ActivityRuleRequest
import com.cloudtimes.agent.dto.response.AgentActivity1Detail

/**
 * 代理活动1规则Service接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
interface ICtAgentActivity1RuleService {
    fun listAgentActivityDetail(request: ActivityDetailRequest): List<AgentActivity1Detail>

    /**
     * 查询代理活动1规则
     *
     * @param id 代理活动1规则主键
     * @return 代理活动1规则
     */
    fun selectCtAgentActivity1RuleById(id: String): CtAgentActivity1Rule?

    /**
     * 查询代理活动1规则列表
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 代理活动1规则集合
     */
    fun selectCtAgentActivity1RuleList(ctAgentActivity1Rule: CtAgentActivity1Rule): List<CtAgentActivity1Rule>

    /**
     * 新增代理活动1规则
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 结果
     */
    fun insertCtAgentActivity1Rule(ctAgentActivity1Rule: CtAgentActivity1Rule): Int

    /**
     * 修改代理活动1规则
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 结果
     */
    fun updateCtAgentActivity1Rule(ctAgentActivity1Rule: CtAgentActivity1Rule): Int

    /**
     * 批量删除代理活动1规则
     *
     * @param ids 需要删除的代理活动1规则主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity1RuleByIds(ids: Array<String>): Int

    /**
     * 删除代理活动1规则信息
     *
     * @param id 代理活动1规则主键
     * @return 结果
     */
    fun deleteCtAgentActivity1RuleById(id: String): Int
}
