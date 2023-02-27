package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentActivity1Rule
import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.dto.response.AgentActivity1Detail
import com.cloudtimes.agent.mapper.CtAgentActivity1RuleMapper
import com.cloudtimes.agent.mapper.CtAgentActivitySettlementMapper
import com.cloudtimes.agent.mapper.provider.CtAgentActivity1RuleProvider
import com.cloudtimes.agent.service.ICtAgentActivity1RuleService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.enums.VerifyState
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动1规则Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivity1RuleServiceImpl : ICtAgentActivity1RuleService {
    @Autowired
    private lateinit var activity1RuleMapper: CtAgentActivity1RuleMapper

    @Autowired
    private lateinit var settlementMapper: CtAgentActivitySettlementMapper

    override fun listAgentActivityDetail(request: ActivityDetailRequest): List<AgentActivity1Detail> {
        val detailList = activity1RuleMapper.selectAgentActivityDetail(
            CtAgentActivity1RuleProvider.selectAgentActivityDetailStmt(request)
        )

        detailList.forEach {
            it.isFulfilled = it.isFulfilled ?: "0"
            it.verifyState = it.verifyState ?: VerifyState.None.code
            it.settlementState = it.settlementState ?: "0"
        }

        return detailList
    }

    /**
     * 查询代理活动1规则
     *
     * @param id 代理活动1规则主键
     * @return 代理活动1规则
     */
    override fun selectCtAgentActivity1RuleById(id: String): CtAgentActivity1Rule? {
        return activity1RuleMapper.selectCtAgentActivity1RuleById(id)
    }

    /**
     * 查询代理活动1规则列表
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 代理活动1规则
     */
    override fun selectCtAgentActivity1RuleList(ctAgentActivity1Rule: CtAgentActivity1Rule): List<CtAgentActivity1Rule> {
        return activity1RuleMapper.selectCtAgentActivity1RuleList(ctAgentActivity1Rule)
    }

    /**
     * 新增代理活动1规则
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 结果
     */
    override fun insertCtAgentActivity1Rule(ctAgentActivity1Rule: CtAgentActivity1Rule): Int {
        ctAgentActivity1Rule.createTime = DateUtils.getNowDate()
        return activity1RuleMapper.insertCtAgentActivity1Rule(ctAgentActivity1Rule)
    }

    /**
     * 修改代理活动1规则
     *
     * @param ctAgentActivity1Rule 代理活动1规则
     * @return 结果
     */
    override fun updateCtAgentActivity1Rule(ctAgentActivity1Rule: CtAgentActivity1Rule): Int {
        ctAgentActivity1Rule.updateTime = DateUtils.getNowDate()
        return activity1RuleMapper.updateCtAgentActivity1Rule(ctAgentActivity1Rule)
    }

    /**
     * 批量删除代理活动1规则
     *
     * @param ids 需要删除的代理活动1规则主键
     * @return 结果
     */
    override fun deleteCtAgentActivity1RuleByIds(ids: Array<String>): Int {
        return activity1RuleMapper.deleteCtAgentActivity1RuleByIds(ids)
    }

    /**
     * 删除代理活动1规则信息
     *
     * @param id 代理活动1规则主键
     * @return 结果
     */
    override fun deleteCtAgentActivity1RuleById(id: String): Int {
        return activity1RuleMapper.deleteCtAgentActivity1RuleById(id)
    }
}
