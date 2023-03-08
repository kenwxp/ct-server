package com.cloudtimes.promotion.mapper

import com.cloudtimes.promotion.domain.CtLuckyDrawRule

/**
 * 幸运大抽奖规则Mapper接口
 *
 * @author tank
 * @date 2023-03-08
 */
interface CtLuckyDrawRuleMapper {
    /**
     * 查询幸运大抽奖规则
     *
     * @param id 幸运大抽奖规则主键
     * @return 幸运大抽奖规则
     */
    fun selectCtLuckyDrawRuleById(id: String): CtLuckyDrawRule?

    /**
     * 查询幸运大抽奖规则列表
     *
     * @param ctLuckyDrawRule 幸运大抽奖规则
     * @return 幸运大抽奖规则集合
     */
    fun selectCtLuckyDrawRuleList(ctLuckyDrawRule: CtLuckyDrawRule): List<CtLuckyDrawRule>

    /**
     * 新增幸运大抽奖规则
     *
     * @param ctLuckyDrawRule 幸运大抽奖规则
     * @return 结果
     */
    fun insertCtLuckyDrawRule(ctLuckyDrawRule: CtLuckyDrawRule): Int

    /**
     * 修改幸运大抽奖规则
     *
     * @param ctLuckyDrawRule 幸运大抽奖规则
     * @return 结果
     */
    fun updateCtLuckyDrawRule(ctLuckyDrawRule: CtLuckyDrawRule): Int

    /**
     * 删除幸运大抽奖规则
     *
     * @param id 幸运大抽奖规则主键
     * @return 结果
     */
    fun deleteCtLuckyDrawRuleById(id: String): Int

    /**
     * 批量删除幸运大抽奖规则
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtLuckyDrawRuleByIds(ids: Array<String>): Int
}
