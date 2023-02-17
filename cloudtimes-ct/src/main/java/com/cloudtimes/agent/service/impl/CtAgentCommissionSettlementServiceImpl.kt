package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentCommissionSettlement
import com.cloudtimes.agent.mapper.CtAgentCommissionSettlementMapper
import com.cloudtimes.agent.service.ICtAgentCommissionSettlementService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 销售佣金结算Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentCommissionSettlementServiceImpl : ICtAgentCommissionSettlementService {
    @Autowired
    private lateinit var ctAgentCommissionSettlementMapper: CtAgentCommissionSettlementMapper

    /**
     * 查询销售佣金结算
     *
     * @param id 销售佣金结算主键
     * @return 销售佣金结算
     */
    override fun selectCtAgentCommissionSettlementById(id: String): CtAgentCommissionSettlement? {
        return ctAgentCommissionSettlementMapper.selectCtAgentCommissionSettlementById(id)
    }

    /**
     * 查询销售佣金结算列表
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 销售佣金结算
     */
    override fun selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement: CtAgentCommissionSettlement): List<CtAgentCommissionSettlement> {
        return ctAgentCommissionSettlementMapper.selectCtAgentCommissionSettlementList(ctAgentCommissionSettlement)
    }

    /**
     * 新增销售佣金结算
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    override fun insertCtAgentCommissionSettlement(ctAgentCommissionSettlement: CtAgentCommissionSettlement): Int {
        ctAgentCommissionSettlement.createTime = DateUtils.getNowDate()
        return ctAgentCommissionSettlementMapper.insertCtAgentCommissionSettlement(ctAgentCommissionSettlement)
    }

    /**
     * 修改销售佣金结算
     *
     * @param ctAgentCommissionSettlement 销售佣金结算
     * @return 结果
     */
    override fun updateCtAgentCommissionSettlement(ctAgentCommissionSettlement: CtAgentCommissionSettlement): Int {
        ctAgentCommissionSettlement.updateTime = DateUtils.getNowDate()
        return ctAgentCommissionSettlementMapper.updateCtAgentCommissionSettlement(ctAgentCommissionSettlement)
    }

    /**
     * 代理佣金确认
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    override fun agentConfirmCtAgentCommissionSettlementById(id: String): Int {
        return ctAgentCommissionSettlementMapper.agentConfirmCtAgentCommissionSettlementById(id)
    }

    /**
     * 平台佣金确认
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    override fun platformConfirmCtAgentCommissionSettlementById(id: String): Int {
        return ctAgentCommissionSettlementMapper.platformConfirmCtAgentCommissionSettlementById(id)
    }

    /**
     * 批量删除销售佣金结算
     *
     * @param ids 需要删除的销售佣金结算主键
     * @return 结果
     */
    override fun deleteCtAgentCommissionSettlementByIds(ids: Array<String>): Int {
        return ctAgentCommissionSettlementMapper.deleteCtAgentCommissionSettlementByIds(ids)
    }

    /**
     * 删除销售佣金结算信息
     *
     * @param id 销售佣金结算主键
     * @return 结果
     */
    override fun deleteCtAgentCommissionSettlementById(id: String): Int {
        return ctAgentCommissionSettlementMapper.deleteCtAgentCommissionSettlementById(id)
    }
}
