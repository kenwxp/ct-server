package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.CtAgentActivityRel
import com.cloudtimes.agent.mapper.CtAgentActivityRelMapper
import com.cloudtimes.agent.service.ICtAgentActivityRelService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动关系Service业务层处理
 *
 * @author tank
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivityRelServiceImpl : ICtAgentActivityRelService {
    @Autowired
    private lateinit var ctAgentActivityRelMapper: CtAgentActivityRelMapper

    /**
     * 查询代理活动关系
     *
     * @param activityId 代理活动关系主键
     * @return 代理活动关系
     */
    override fun selectCtAgentActivityRelByActivityId(activityId: String): CtAgentActivityRel {
        return ctAgentActivityRelMapper.selectCtAgentActivityRelByActivityId(activityId)
    }

    /**
     * 查询代理活动关系列表
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 代理活动关系
     */
    override fun selectCtAgentActivityRelList(ctAgentActivityRel: CtAgentActivityRel): List<CtAgentActivityRel> {
        return ctAgentActivityRelMapper.selectCtAgentActivityRelList(ctAgentActivityRel)
    }

    /**
     * 新增代理活动关系
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 结果
     */
    override fun insertCtAgentActivityRel(ctAgentActivityRel: CtAgentActivityRel): Int {
        return ctAgentActivityRelMapper.insertCtAgentActivityRel(ctAgentActivityRel)
    }

    /**
     * 修改代理活动关系
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 结果
     */
    override fun updateCtAgentActivityRel(ctAgentActivityRel: CtAgentActivityRel): Int {
        return ctAgentActivityRelMapper.updateCtAgentActivityRel(ctAgentActivityRel)
    }

    /**
     * 批量删除代理活动关系
     *
     * @param activityIds 需要删除的代理活动关系主键
     * @return 结果
     */
    override fun deleteCtAgentActivityRelByActivityIds(activityIds: Array<String>): Int {
        return ctAgentActivityRelMapper.deleteCtAgentActivityRelByActivityIds(activityIds)
    }

    /**
     * 删除代理活动关系信息
     *
     * @param activityId 代理活动关系主键
     * @return 结果
     */
    override fun deleteCtAgentActivityRelByActivityId(activityId: String): Int {
        return ctAgentActivityRelMapper.deleteCtAgentActivityRelByActivityId(activityId)
    }
}
