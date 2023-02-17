package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentActivityRuleStoreRel
import com.cloudtimes.agent.mapper.CtAgentActivityRuleStoreRelMapper
import com.cloudtimes.agent.service.ICtAgentActivityRuleStoreRelService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动规则与店铺关系Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivityRuleStoreRelServiceImpl : ICtAgentActivityRuleStoreRelService {
    @Autowired
    private lateinit var ctAgentActivityRuleStoreRelMapper: CtAgentActivityRuleStoreRelMapper

    /**
     * 查询代理活动规则与店铺关系
     *
     * @param id 代理活动规则与店铺关系主键
     * @return 代理活动规则与店铺关系
     */
    override fun selectCtAgentActivityRuleStoreRelById(id: String): CtAgentActivityRuleStoreRel? {
        return ctAgentActivityRuleStoreRelMapper.selectCtAgentActivityRuleStoreRelById(id)
    }

    /**
     * 查询代理活动规则与店铺关系列表
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 代理活动规则与店铺关系
     */
    override fun selectCtAgentActivityRuleStoreRelList(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): List<CtAgentActivityRuleStoreRel> {
        return ctAgentActivityRuleStoreRelMapper.selectCtAgentActivityRuleStoreRelList(ctAgentActivityRuleStoreRel)
    }

    /**
     * 新增代理活动规则与店铺关系
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 结果
     */
    override fun insertCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): Int {
        ctAgentActivityRuleStoreRel.createTime = DateUtils.getNowDate()
        return ctAgentActivityRuleStoreRelMapper.insertCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel)
    }

    /**
     * 修改代理活动规则与店铺关系
     *
     * @param ctAgentActivityRuleStoreRel 代理活动规则与店铺关系
     * @return 结果
     */
    override fun updateCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel: CtAgentActivityRuleStoreRel): Int {
        ctAgentActivityRuleStoreRel.updateTime = DateUtils.getNowDate()
        return ctAgentActivityRuleStoreRelMapper.updateCtAgentActivityRuleStoreRel(ctAgentActivityRuleStoreRel)
    }

    /**
     * 批量删除代理活动规则与店铺关系
     *
     * @param ids 需要删除的代理活动规则与店铺关系主键
     * @return 结果
     */
    override fun deleteCtAgentActivityRuleStoreRelByIds(ids: Array<String>): Int {
        return ctAgentActivityRuleStoreRelMapper.deleteCtAgentActivityRuleStoreRelByIds(ids)
    }

    /**
     * 删除代理活动规则与店铺关系信息
     *
     * @param id 代理活动规则与店铺关系主键
     * @return 结果
     */
    override fun deleteCtAgentActivityRuleStoreRelById(id: String): Int {
        return ctAgentActivityRuleStoreRelMapper.deleteCtAgentActivityRuleStoreRelById(id)
    }
}
