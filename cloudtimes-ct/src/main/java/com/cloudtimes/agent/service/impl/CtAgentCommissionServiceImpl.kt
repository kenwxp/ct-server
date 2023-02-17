package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentCommission
import com.cloudtimes.account.dto.request.UpdateSubUserCommissionRequest
import com.cloudtimes.agent.mapper.CtAgentCommissionMapper
import com.cloudtimes.agent.mapper.CtUserAgentMapper
import com.cloudtimes.agent.mapper.provider.CtAgentCommissionProvider
import com.cloudtimes.agent.mapper.provider.CtUserAgentProvider
import com.cloudtimes.agent.service.ICtAgentCommissionService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.AgentType
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * 代理销售佣金设置Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentCommissionServiceImpl : ICtAgentCommissionService {
    @Autowired
    private lateinit var commissionMapper: CtAgentCommissionMapper

    @Autowired
    private lateinit var agentMapper: CtUserAgentMapper

    override fun selectCtAgentCommissionByUserId(userId: String): CtAgentCommission? {
        var commission = commissionMapper.selectOne(
            CtAgentCommissionProvider.selectByUserId(userId)
        )

        //  查询到记录直接返回
        if (commission != null) return commission

        // 获取代理信息
        val agent = agentMapper.selectOne(CtUserAgentProvider.selectById(userId)) ?:
            throw ServiceException("数据库异常，查询代理信息失败")

        if (agent.agentType != AgentType.GeneralAgent.code) {
            throw ServiceException("数据库异常，代理佣金参数未配置")
        }

        if (agent.parentUserId == null) {
            throw ServiceException("数据库异常，代理没有绑定上级代理")
        }

        // 获取上级代理佣金信息
        val parentCommission = commissionMapper.selectOne(
            CtAgentCommissionProvider.selectByUserId(agent.parentUserId!!)
        ) ?: throw ServiceException("数据库异常，代理佣金参数未配置")

        //  插入下级代理佣金默认值
        commissionMapper.generalInsert(
            CtAgentCommissionProvider.insertOneWithParentConfig(parentCommission, userId)
        )

        // 再次查询
        commission = commissionMapper.selectOne(
            CtAgentCommissionProvider.selectByUserId(userId)
        )

        return commission
    }

    override fun updateSubUserCommission(request: UpdateSubUserCommissionRequest): Int {
        // Step 1. 合法性检查
        val parentCommission = commissionMapper.selectCtAgentCommissionById(
            request.detail!!.parentUserId ?: ""
        ) ?: throw ServiceException("数据库异常，未能查询到上级佣金配置信息")

        val costPrice = request.detail!!.costPrice ?: BigDecimal.ZERO
        if (costPrice <= parentCommission.costPrice!! || costPrice > BigDecimal("1000000.00")) {
            throw ServiceException("参数错误，代理价格不合理")
        }

        return commissionMapper.update(
            CtAgentCommissionProvider.updateSubUserCommission(request)
        )
    }

    /**
     * 查询代理销售佣金设置
     *
     * @param id 代理销售佣金设置主键
     * @return 代理销售佣金设置
     */
    override fun selectCtAgentCommissionById(id: String): CtAgentCommission? {
        return commissionMapper.selectCtAgentCommissionById(id)
    }

    /**
     * 查询代理销售佣金设置列表
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 代理销售佣金设置
     */
    override fun selectCtAgentCommissionList(ctAgentCommission: CtAgentCommission): List<CtAgentCommission> {
        return commissionMapper.selectCtAgentCommissionList(ctAgentCommission)
    }

    /**
     * 新增代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    override fun insertCtAgentCommission(ctAgentCommission: CtAgentCommission): Int {
        ctAgentCommission.createTime = DateUtils.getNowDate()
        return commissionMapper.insertCtAgentCommission(ctAgentCommission)
    }

    /**
     * 修改代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    override fun updateCtAgentCommission(ctAgentCommission: CtAgentCommission): Int {
        ctAgentCommission.updateTime = DateUtils.getNowDate()
        return commissionMapper.updateCtAgentCommission(ctAgentCommission)
    }

    /**
     * 批量删除代理销售佣金设置
     *
     * @param ids 需要删除的代理销售佣金设置主键
     * @return 结果
     */
    override fun deleteCtAgentCommissionByIds(ids: Array<String>): Int {
        return commissionMapper.deleteCtAgentCommissionByIds(ids)
    }

    /**
     * 删除代理销售佣金设置信息
     *
     * @param id 代理销售佣金设置主键
     * @return 结果
     */
    override fun deleteCtAgentCommissionById(id: String): Int {
        return commissionMapper.deleteCtAgentCommissionById(id)
    }
}
