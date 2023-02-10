package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtAgentCommission
import com.cloudtimes.account.mapper.CtAgentCommissionMapper
import com.cloudtimes.account.service.ICtAgentCommissionService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
    private lateinit var ctAgentCommissionMapper: CtAgentCommissionMapper

    /**
     * 查询代理销售佣金设置
     *
     * @param id 代理销售佣金设置主键
     * @return 代理销售佣金设置
     */
    override fun selectCtAgentCommissionById(id: String): CtAgentCommission? {
        return ctAgentCommissionMapper.selectCtAgentCommissionById(id)
    }

    /**
     * 查询代理销售佣金设置列表
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 代理销售佣金设置
     */
    override fun selectCtAgentCommissionList(ctAgentCommission: CtAgentCommission): List<CtAgentCommission> {
        return ctAgentCommissionMapper.selectCtAgentCommissionList(ctAgentCommission)
    }

    /**
     * 新增代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    override fun insertCtAgentCommission(ctAgentCommission: CtAgentCommission): Int {
        ctAgentCommission.createTime = DateUtils.getNowDate()
        return ctAgentCommissionMapper.insertCtAgentCommission(ctAgentCommission)
    }

    /**
     * 修改代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    override fun updateCtAgentCommission(ctAgentCommission: CtAgentCommission): Int {
        ctAgentCommission.updateTime = DateUtils.getNowDate()
        return ctAgentCommissionMapper.updateCtAgentCommission(ctAgentCommission)
    }

    /**
     * 批量删除代理销售佣金设置
     *
     * @param ids 需要删除的代理销售佣金设置主键
     * @return 结果
     */
    override fun deleteCtAgentCommissionByIds(ids: Array<String>): Int {
        return ctAgentCommissionMapper.deleteCtAgentCommissionByIds(ids)
    }

    /**
     * 删除代理销售佣金设置信息
     *
     * @param id 代理销售佣金设置主键
     * @return 结果
     */
    override fun deleteCtAgentCommissionById(id: String): Int {
        return ctAgentCommissionMapper.deleteCtAgentCommissionById(id)
    }
}