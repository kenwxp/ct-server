package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.*
import com.cloudtimes.agent.dto.request.ActivityRuleRequest
import com.cloudtimes.agent.mapper.*
import com.cloudtimes.agent.mapper.provider.CtAgentActivitySettlementProvider
import com.cloudtimes.agent.service.*
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.enums.YesNoState
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动结算Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivitySettlementServiceImpl : ICtAgentActivitySettlementService {
    @Autowired
    private lateinit var settlementMapper: CtAgentActivitySettlementMapper


    /** 代理确认活动完成情况 */
    override fun agentConfirm(request: ActivityRuleRequest): Int {

        val settlement = settlementMapper.selectOne(
            CtAgentActivitySettlementProvider.selectByKey(request.activityRuleId!!, request.userId!!)
        ) ?: throw ServiceException("活动未完成，不能确定")

        if ( settlement.isFulfilled === null || settlement.isFulfilled != YesNoState.Yes.code) {
            throw ServiceException("活动未完成，不能确定")
        }

        return settlementMapper.update(
            CtAgentActivitySettlementProvider.updateFulfilledByKey(request.activityRuleId!!, request.userId!!)
        )
    }


    /**
     * 查询代理活动结算
     *
     * @param id 代理活动结算主键
     * @return 代理活动结算
     */
    override fun selectCtAgentActivitySettlementById(id: String): CtAgentActivitySettlement? {
        return settlementMapper.selectCtAgentActivitySettlementById(id)
    }

    /**
     * 查询代理活动结算列表
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 代理活动结算
     */
    override fun selectCtAgentActivitySettlementList(ctAgentActivitySettlement: CtAgentActivitySettlement): List<CtAgentActivitySettlement> {
        return settlementMapper.selectCtAgentActivitySettlementList(ctAgentActivitySettlement)
    }

    /**
     * 新增代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    override fun insertCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int {
        ctAgentActivitySettlement.createTime = DateUtils.getNowDate()
        return settlementMapper.insertCtAgentActivitySettlement(ctAgentActivitySettlement)
    }

    /**
     * 修改代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    override fun updateCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int {
        ctAgentActivitySettlement.updateTime = DateUtils.getNowDate()
        return settlementMapper.updateCtAgentActivitySettlement(ctAgentActivitySettlement)
    }

    /**
     * 批量删除代理活动结算
     *
     * @param ids 需要删除的代理活动结算主键
     * @return 结果
     */
    override fun deleteCtAgentActivitySettlementByIds(ids: Array<String>): Int {
        return settlementMapper.deleteCtAgentActivitySettlementByIds(ids)
    }

    /**
     * 删除代理活动结算信息
     *
     * @param id 代理活动结算主键
     * @return 结果
     */
    override fun deleteCtAgentActivitySettlementById(id: String): Int {
        return settlementMapper.deleteCtAgentActivitySettlementById(id)
    }
}
