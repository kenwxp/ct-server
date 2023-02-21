package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivity
import com.cloudtimes.agent.dto.request.ActivityListRequest
import com.cloudtimes.agent.dto.request.ActivityStoreRequest
import com.cloudtimes.agent.dto.response.AgentStoreDetail

/**
 * 代理活动Service接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
interface ICtAgentActivityService {
    fun selectAgentActivity(request: ActivityListRequest): List<CtAgentActivity>
    fun selectActivityById(id: String): CtAgentActivity?

    fun selectActivityStores(request: ActivityStoreRequest): List<AgentStoreDetail>

    /**
     * 查询代理活动列表
     *
     * @param ctAgentActivity 代理活动
     * @return 代理活动集合
     */
    fun selectCtAgentActivityList(ctAgentActivity: CtAgentActivity): List<CtAgentActivity>

    /**
     * 新增代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    fun insertCtAgentActivity(ctAgentActivity: CtAgentActivity): Int

    /**
     * 修改代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    fun updateCtAgentActivity(ctAgentActivity: CtAgentActivity): Int

    /**
     * 批量删除代理活动
     *
     * @param ids 需要删除的代理活动主键集合
     * @return 结果
     */
    fun deleteCtAgentActivityByIds(ids: Array<String>): Int

    /**
     * 删除代理活动信息
     *
     * @param id 代理活动主键
     * @return 结果
     */
    fun deleteCtAgentActivityById(id: String): Int
}
