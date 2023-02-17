package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentActivity2Rule
import com.cloudtimes.agent.mapper.CtAgentActivity2RuleMapper
import com.cloudtimes.agent.service.ICtAgentActivity2RuleService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动2规则Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivity2RuleServiceImpl : ICtAgentActivity2RuleService {
    @Autowired
    private lateinit var ctAgentActivity2RuleMapper: CtAgentActivity2RuleMapper

    /**
     * 查询代理活动2规则
     *
     * @param id 代理活动2规则主键
     * @return 代理活动2规则
     */
    override fun selectCtAgentActivity2RuleById(id: String): CtAgentActivity2Rule? {
        return ctAgentActivity2RuleMapper.selectCtAgentActivity2RuleById(id)
    }

    /**
     * 查询代理活动2规则列表
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 代理活动2规则
     */
    override fun selectCtAgentActivity2RuleList(ctAgentActivity2Rule: CtAgentActivity2Rule): List<CtAgentActivity2Rule> {
        return ctAgentActivity2RuleMapper.selectCtAgentActivity2RuleList(ctAgentActivity2Rule)
    }

    /**
     * 新增代理活动2规则
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 结果
     */
    override fun insertCtAgentActivity2Rule(ctAgentActivity2Rule: CtAgentActivity2Rule): Int {
        ctAgentActivity2Rule.createTime = DateUtils.getNowDate()
        return ctAgentActivity2RuleMapper.insertCtAgentActivity2Rule(ctAgentActivity2Rule)
    }

    /**
     * 修改代理活动2规则
     *
     * @param ctAgentActivity2Rule 代理活动2规则
     * @return 结果
     */
    override fun updateCtAgentActivity2Rule(ctAgentActivity2Rule: CtAgentActivity2Rule): Int {
        ctAgentActivity2Rule.updateTime = DateUtils.getNowDate()
        return ctAgentActivity2RuleMapper.updateCtAgentActivity2Rule(ctAgentActivity2Rule)
    }

    /**
     * 批量删除代理活动2规则
     *
     * @param ids 需要删除的代理活动2规则主键
     * @return 结果
     */
    override fun deleteCtAgentActivity2RuleByIds(ids: Array<String>): Int {
        return ctAgentActivity2RuleMapper.deleteCtAgentActivity2RuleByIds(ids)
    }

    /**
     * 删除代理活动2规则信息
     *
     * @param id 代理活动2规则主键
     * @return 结果
     */
    override fun deleteCtAgentActivity2RuleById(id: String): Int {
        return ctAgentActivity2RuleMapper.deleteCtAgentActivity2RuleById(id)
    }
}
