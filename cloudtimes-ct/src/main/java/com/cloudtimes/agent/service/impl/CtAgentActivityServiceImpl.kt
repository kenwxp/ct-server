package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.*
import com.cloudtimes.agent.dto.request.ActivityListRequest
import com.cloudtimes.agent.dto.request.ActivityStoreRequest
import com.cloudtimes.agent.dto.response.AgentStoreDetail
import com.cloudtimes.agent.mapper.*
import com.cloudtimes.agent.mapper.provider.CtAgentActivity1RuleProvider
import com.cloudtimes.agent.mapper.provider.CtAgentActivityProvider
import com.cloudtimes.agent.service.*
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.ActivityType
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivityServiceImpl : ICtAgentActivityService {
    @Autowired
    private lateinit var activityMapper: CtAgentActivityMapper

    @Autowired
    private lateinit var rule1Mapper: CtAgentActivity1RuleMapper

    override fun selectAgentActivity(request: ActivityListRequest): List<CtAgentActivity> {
        return activityMapper.selectMany(
            CtAgentActivityProvider.selectAgentActivityListStmt(request)
        )
    }

    override fun selectActivityById(id: String): CtAgentActivity? {
        return activityMapper.selectOne(
            CtAgentActivityProvider.selectByIdStmt(id)
        )
    }

    /** 查询满足活动规则的店铺 */
    override fun selectActivityStores(request: ActivityStoreRequest): List<AgentStoreDetail> {
        val stores = activityMapper.selectActivityStores(
            CtAgentActivityProvider.selectActivityStores(request)
        )

        if ( stores.isEmpty() ) {
            return emptyList()
        }

        if (request.activityType == ActivityType.Activity1.code) {
            val rule1 = rule1Mapper.selectOne(CtAgentActivity1RuleProvider.selectById(request.activityRuleId!!))!!

            stores.forEach {
                it.activityType = request.activityType
                it.activityRuleId = request.activityRuleId
                it.fulfilDate = it.storeOnlineDate?.plusMonths(rule1.timeSpan ?: 0)
            }
        } else {
            stores.forEach {
                it.activityType = request.activityType
                it.activityRuleId = request.activityRuleId
                it.fulfilDate = it.storeOnlineDate
            }
        }

        return stores
    }

    /**
     * 查询代理活动列表
     *
     * @param ctAgentActivity 代理活动
     * @return 代理活动
     */
    override fun selectCtAgentActivityList(ctAgentActivity: CtAgentActivity): List<CtAgentActivity> {
        return activityMapper.selectCtAgentActivityList(ctAgentActivity)
    }

    /**
     * 新增代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    override fun insertCtAgentActivity(ctAgentActivity: CtAgentActivity): Int {
        ctAgentActivity.createTime = DateUtils.getNowDate()
        return activityMapper.insertCtAgentActivity(ctAgentActivity)
    }

    /**
     * 修改代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    override fun updateCtAgentActivity(ctAgentActivity: CtAgentActivity): Int {
        ctAgentActivity.updateTime = DateUtils.getNowDate()
        return activityMapper.updateCtAgentActivity(ctAgentActivity)
    }

    /**
     * 批量删除代理活动
     *
     * @param ids 需要删除的代理活动主键
     * @return 结果
     */
    override fun deleteCtAgentActivityByIds(ids: Array<String>): Int {
        return activityMapper.deleteCtAgentActivityByIds(ids)
    }

    /**
     * 删除代理活动信息
     *
     * @param id 代理活动主键
     * @return 结果
     */
    override fun deleteCtAgentActivityById(id: String): Int {
        return activityMapper.deleteCtAgentActivityById(id)
    }
}
