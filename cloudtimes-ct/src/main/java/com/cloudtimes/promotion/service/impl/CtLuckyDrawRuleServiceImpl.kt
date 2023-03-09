package com.cloudtimes.promotion.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.mapper.CtLuckyDrawRuleMapper
import com.cloudtimes.promotion.mapper.provider.CtLuckyDrawRuleProvider
import com.cloudtimes.promotion.service.ICtLuckyDrawRuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 幸运大抽奖规则Service业务层处理
 *
 * @author tank
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtLuckyDrawRuleServiceImpl : ICtLuckyDrawRuleService {
    @Autowired
    private lateinit var ruleMapper: CtLuckyDrawRuleMapper

    override fun selectCtLuckyDrawRuleListByActivityId(activityId: String): List<CtLuckyDrawRule> {
        return ruleMapper.selectMany(
            CtLuckyDrawRuleProvider.findRulesByActivityIdStmt(activityId)
        )
    }

    /**
     * 查询幸运大抽奖规则
     *
     * @param id 幸运大抽奖规则主键
     * @return 幸运大抽奖规则
     */
    override fun selectCtLuckyDrawRuleById(id: String): CtLuckyDrawRule? {
        return ruleMapper.selectCtLuckyDrawRuleById(id)
    }

    /**
     * 查询幸运大抽奖规则列表
     *
     * @param ctLuckyDrawRule 幸运大抽奖规则
     * @return 幸运大抽奖规则
     */
    override fun selectCtLuckyDrawRuleList(ctLuckyDrawRule: CtLuckyDrawRule): List<CtLuckyDrawRule> {
        return ruleMapper.selectCtLuckyDrawRuleList(ctLuckyDrawRule)
    }

    /**
     * 新增幸运大抽奖规则
     *
     * @param ctLuckyDrawRule 幸运大抽奖规则
     * @return 结果
     */
    override fun insertCtLuckyDrawRule(ctLuckyDrawRule: CtLuckyDrawRule): Int {
        ctLuckyDrawRule.createTime = DateUtils.getNowDate()
        return ruleMapper.insertCtLuckyDrawRule(ctLuckyDrawRule)
    }

    /**
     * 修改幸运大抽奖规则
     *
     * @param ctLuckyDrawRule 幸运大抽奖规则
     * @return 结果
     */
    override fun updateCtLuckyDrawRule(ctLuckyDrawRule: CtLuckyDrawRule): Int {
        ctLuckyDrawRule.updateTime = DateUtils.getNowDate()
        return ruleMapper.updateCtLuckyDrawRule(ctLuckyDrawRule)
    }

    /**
     * 批量删除幸运大抽奖规则
     *
     * @param ids 需要删除的幸运大抽奖规则主键
     * @return 结果
     */
    override fun deleteCtLuckyDrawRuleByIds(ids: Array<String>): Int {
        return ruleMapper.deleteCtLuckyDrawRuleByIds(ids)
    }

    /**
     * 删除幸运大抽奖规则信息
     *
     * @param id 幸运大抽奖规则主键
     * @return 结果
     */
    override fun deleteCtLuckyDrawRuleById(id: String): Int {
        return ruleMapper.deleteCtLuckyDrawRuleById(id)
    }
}
