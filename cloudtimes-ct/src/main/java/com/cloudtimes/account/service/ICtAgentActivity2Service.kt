package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtAgentActivity2

/**
 * 代理活动2Service接口
 *
 * @author tank
 * @date 2023-02-13
 */
interface ICtAgentActivity2Service {
    /**
     * 查询代理活动2
     *
     * @param id 代理活动2主键
     * @return 代理活动2
     */
    fun selectCtAgentActivity2ById(id: String): CtAgentActivity2?

    /**
     * 查询代理活动2列表
     *
     * @param ctAgentActivity2 代理活动2
     * @return 代理活动2集合
     */
    fun selectCtAgentActivity2List(ctAgentActivity2: CtAgentActivity2): List<CtAgentActivity2>

    /**
     * 新增代理活动2
     *
     * @param ctAgentActivity2 代理活动2
     * @return 结果
     */
    fun insertCtAgentActivity2(ctAgentActivity2: CtAgentActivity2): Int

    /**
     * 修改代理活动2
     *
     * @param ctAgentActivity2 代理活动2
     * @return 结果
     */
    fun updateCtAgentActivity2(ctAgentActivity2: CtAgentActivity2): Int

    /**
     * 批量删除代理活动2
     *
     * @param ids 需要删除的代理活动2主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity2ByIds(ids: Array<String>): Int

    /**
     * 删除代理活动2信息
     *
     * @param id 代理活动2主键
     * @return 结果
     */
    fun deleteCtAgentActivity2ById(id: String): Int
}