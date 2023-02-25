package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentDividendSettlement
import com.cloudtimes.agent.dto.request.AgentDividendRequest
import com.cloudtimes.agent.dto.request.StoreDividendRequest
import com.cloudtimes.agent.mapper.CtAgentDividendSettlementMapper
import com.cloudtimes.agent.mapper.provider.CtAgentDividendSettlementProvider
import com.cloudtimes.agent.service.ICtAgentDividendSettlementService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 分润结算审核Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentDividendSettlementServiceImpl : ICtAgentDividendSettlementService {
    @Autowired
    private lateinit var dividendSettlementMapper: CtAgentDividendSettlementMapper

    /** 查询店铺分润列表 */
    override fun selectStoreDividendList(request: StoreDividendRequest): List<CtAgentDividendSettlement> {
        return dividendSettlementMapper.selectMany(
            CtAgentDividendSettlementProvider.selectStoreDividendStmt(request)
        )
    }


    /** 代理确认分润 */
    override fun agentApproveDividend(request: AgentDividendRequest): Int {
        return dividendSettlementMapper.update(
            CtAgentDividendSettlementProvider.agentApproveDividendStmt(request)
        )
    }


    /** 代理分润详情 */
    override fun agentDividendDetail(request: AgentDividendRequest): CtAgentDividendSettlement? {
        return dividendSettlementMapper.selectOne(
            CtAgentDividendSettlementProvider.selectAgentDividendDetailStmt(request)
        )
    }

    /**
     * 查询分润结算审核
     *
     * @param id 分润结算审核主键
     * @return 分润结算审核
     */
    override fun selectCtAgentDividendSettlementById(id: String): CtAgentDividendSettlement? {
        return dividendSettlementMapper.selectCtAgentDividendSettlementById(id)
    }

    /**
     * 查询分润结算审核列表
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 分润结算审核
     */
    override fun selectCtAgentDividendSettlementList(ctAgentDividendSettlement: CtAgentDividendSettlement): List<CtAgentDividendSettlement> {
        return dividendSettlementMapper.selectCtAgentDividendSettlementList(ctAgentDividendSettlement)
    }

    /**
     * 新增分润结算审核
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    override fun insertCtAgentDividendSettlement(ctAgentDividendSettlement: CtAgentDividendSettlement): Int {
        ctAgentDividendSettlement.createTime = DateUtils.getNowDate()
        return dividendSettlementMapper.insertCtAgentDividendSettlement(ctAgentDividendSettlement)
    }

    /**
     * 修改分润结算审核
     *
     * @param ctAgentDividendSettlement 分润结算审核
     * @return 结果
     */
    override fun updateCtAgentDividendSettlement(ctAgentDividendSettlement: CtAgentDividendSettlement): Int {
        ctAgentDividendSettlement.updateTime = DateUtils.getNowDate()
        return dividendSettlementMapper.updateCtAgentDividendSettlement(ctAgentDividendSettlement)
    }
}