package com.cloudtimes.agent.service

import com.cloudtimes.agent.domain.CtAgentActivityRuleStoreRel

/**
 * 代理活动规则与店铺关系Service接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
interface ICtAgentActivityRuleStoreRelService {
    /**
     * 查询代理活动规则与店铺关系
     *
     * @param id 代理活动规则与店铺关系主键
     * @return 代理活动规则与店铺关系
     */
    fun selectCtAgentActivityRuleStoreRelById(id: String): CtAgentActivityRuleStoreRel?

    /**
     * 查询代理活动规则与店铺关系列表
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 代理活动规则与店铺关系集合
     */
    fun selectCtAgentActivityRuleStoreRelList(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): List<CtAgentActivityRuleStoreRel>

    /**
     * 新增代理活动规则与店铺关系
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 结果
     */
    fun insertCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): Int

    /**
     * 修改代理活动规则与店铺关系
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 结果
     */
    fun updateCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): Int

    /**
     * 批量删除代理活动规则与店铺关系
     *
     * @param ids 需要删除的代理活动规则与店铺关系主键集合
     * @return 结果
     */
    fun deleteCtAgentActivityRuleStoreRelByIds(ids: Array<String>): Int

    /**
     * 删除代理活动规则与店铺关系信息
     *
     * @param id 代理活动规则与店铺关系主键
     * @return 结果
     */
    fun deleteCtAgentActivityRuleStoreRelById(id: String): Int
}
