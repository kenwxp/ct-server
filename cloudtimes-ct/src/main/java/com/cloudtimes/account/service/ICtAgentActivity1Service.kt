package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtAgentActivity1

/**
 * 代理活动1Service接口
 *
 * @author 沈兵
 * @date 2023-02-13
 */
interface ICtAgentActivity1Service {
    /**
     * 查询代理活动1
     *
     * @param id 代理活动1主键
     * @return 代理活动1
     */
    fun selectCtAgentActivity1ById(id: String): CtAgentActivity1?

    /**
     * 查询代理活动1列表
     *
     * @param ctAgentActivity1 代理活动1
     * @return 代理活动1集合
     */
    fun selectCtAgentActivity1List(ctAgentActivity1: CtAgentActivity1): List<CtAgentActivity1>

    /**
     * 新增代理活动1
     *
     * @param ctAgentActivity1 代理活动1
     * @return 结果
     */
    fun insertCtAgentActivity1(ctAgentActivity1: CtAgentActivity1): Int

    /**
     * 修改代理活动1
     *
     * @param ctAgentActivity1 代理活动1
     * @return 结果
     */
    fun updateCtAgentActivity1(ctAgentActivity1: CtAgentActivity1): Int

    /**
     * 批量删除代理活动1
     *
     * @param ids 需要删除的代理活动1主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity1ByIds(ids: Array<String>): Int

    /**
     * 删除代理活动1信息
     *
     * @param id 代理活动1主键
     * @return 结果
     */
    fun deleteCtAgentActivity1ById(id: String): Int
}