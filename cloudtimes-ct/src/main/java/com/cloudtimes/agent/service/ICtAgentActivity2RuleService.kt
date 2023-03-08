package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivity2Rule
import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.dto.response.AgentActivity2Detail
import com.cloudtimes.agent.dto.response.CtAgentActivity2RuleDto

/**
 * 代理活动2规则Service接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
interface ICtAgentActivity2RuleService {
    fun listAgentActivityDetail(request: ActivityDetailRequest): List<AgentActivity2Detail>

    /**
     * 查询代理活动2规则
     *
     * @param id 代理活动2规则主键
     * @return 代理活动2规则
     */
    fun selectCtAgentActivity2RuleById(id: String): CtAgentActivity2Rule?

    /**
     * 查询代理活动2规则列表
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 代理活动2规则集合
     */
    fun selectCtAgentActivity2RuleList(ctAgentActivity2Rule: CtAgentActivity2Rule): List<CtAgentActivity2Rule>

    fun selectCtAgentActivity2RuleListPlus(ctAgentActivity2Rule: CtAgentActivity2Rule): List<CtAgentActivity2RuleDto>

    /**
     * 新增代理活动2规则
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 结果
     */
    fun insertCtAgentActivity2Rule(ctAgentActivity2Rule: CtAgentActivity2Rule): Int

    /**
     * 修改代理活动2规则
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 结果
     */
    fun updateCtAgentActivity2Rule(ctAgentActivity2Rule: CtAgentActivity2Rule): Int

    /**
     * 批量删除代理活动2规则
     *
     * @param ids 需要删除的代理活动2规则主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity2RuleByIds(ids: Array<String>): Int

    /**
     * 删除代理活动2规则信息
     *
     * @param id 代理活动2规则主键
     * @return 结果
     */
    fun deleteCtAgentActivity2RuleById(id: String): Int
}
