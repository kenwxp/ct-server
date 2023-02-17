package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivityRel

/**
 * 代理活动关系Service接口
 *
 * @author tank
 * @date 2023-02-17
 */
interface ICtAgentActivityRelService {
    /**
     * 查询代理活动关系
     *
     * @param activityId 代理活动关系主键
     * @return 代理活动关系
     */
    fun selectCtAgentActivityRelByActivityId(activityId: String): CtAgentActivityRel

    /**
     * 查询代理活动关系列表
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 代理活动关系集合
     */
    fun selectCtAgentActivityRelList(ctAgentActivityRel: CtAgentActivityRel): List<CtAgentActivityRel>

    /**
     * 新增代理活动关系
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 结果
     */
    fun insertCtAgentActivityRel(ctAgentActivityRel: CtAgentActivityRel): Int

    /**
     * 修改代理活动关系
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 结果
     */
    fun updateCtAgentActivityRel(ctAgentActivityRel: CtAgentActivityRel): Int

    /**
     * 批量删除代理活动关系
     *
     * @param activityIds 需要删除的代理活动关系主键集合
     * @return 结果
     */
    fun deleteCtAgentActivityRelByActivityIds(activityIds: Array<String>): Int

    /**
     * 删除代理活动关系信息
     *
     * @param activityId 代理活动关系主键
     * @return 结果
     */
    fun deleteCtAgentActivityRelByActivityId(activityId: String): Int
}
