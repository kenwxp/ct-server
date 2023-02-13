package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtAgentActivitySettlement
import com.cloudtimes.account.mapper.CtAgentActivitySettlementMapper
import com.cloudtimes.account.service.ICtAgentActivitySettlementService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动结算Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-13
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivitySettlementServiceImpl : ICtAgentActivitySettlementService {
    @Autowired
    private lateinit var ctAgentActivitySettlementMapper: CtAgentActivitySettlementMapper

    /**
     * 查询代理活动结算
     *
     * @param id 代理活动结算主键
     * @return 代理活动结算
     */
    override fun selectCtAgentActivitySettlementById(id: String): CtAgentActivitySettlement? {
        return ctAgentActivitySettlementMapper.selectCtAgentActivitySettlementById(id)
    }

    /**
     * 查询代理活动结算列表
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 代理活动结算
     */
    override fun selectCtAgentActivitySettlementList(ctAgentActivitySettlement: CtAgentActivitySettlement): List<CtAgentActivitySettlement> {
        return ctAgentActivitySettlementMapper.selectCtAgentActivitySettlementList(ctAgentActivitySettlement)
    }

    /**
     * 新增代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    override fun insertCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int {
        ctAgentActivitySettlement.createTime = DateUtils.getNowDate()
        return ctAgentActivitySettlementMapper.insertCtAgentActivitySettlement(ctAgentActivitySettlement)
    }

    /**
     * 修改代理活动结算
     *
     * @param ctAgentActivitySettlement 代理活动结算
     * @return 结果
     */
    override fun updateCtAgentActivitySettlement(ctAgentActivitySettlement: CtAgentActivitySettlement): Int {
        ctAgentActivitySettlement.updateTime = DateUtils.getNowDate()
        return ctAgentActivitySettlementMapper.updateCtAgentActivitySettlement(ctAgentActivitySettlement)
    }
}