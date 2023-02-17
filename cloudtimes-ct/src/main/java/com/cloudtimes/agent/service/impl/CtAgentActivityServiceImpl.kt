package com.cloudtimes.agent.service.impl

import com.cloudtimes.agent.domain.*
import com.cloudtimes.agent.dto.request.QueryActivityRequest
import com.cloudtimes.agent.mapper.*
import com.cloudtimes.agent.service.*
import com.cloudtimes.common.annotation.DataSource
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
    private lateinit var ctAgentActivityMapper: CtAgentActivityMapper

    override fun selectAgentActivity(request: QueryActivityRequest): List<CtAgentActivity> {
        TODO("Not yet implemented")
    }

    /**
     * 查询代理活动
     *
     * @param id 代理活动主键
     * @return 代理活动
     */
    override fun selectCtAgentActivityById(id: String): CtAgentActivity? {
        return ctAgentActivityMapper.selectCtAgentActivityById(id)
    }

    /**
     * 查询代理活动列表
     *
     * @param ctAgentActivity 代理活动
     * @return 代理活动
     */
    override fun selectCtAgentActivityList(ctAgentActivity: CtAgentActivity): List<CtAgentActivity> {
        return ctAgentActivityMapper.selectCtAgentActivityList(ctAgentActivity)
    }

    /**
     * 新增代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    override fun insertCtAgentActivity(ctAgentActivity: CtAgentActivity): Int {
        ctAgentActivity.createTime = DateUtils.getNowDate()
        return ctAgentActivityMapper.insertCtAgentActivity(ctAgentActivity)
    }

    /**
     * 修改代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    override fun updateCtAgentActivity(ctAgentActivity: CtAgentActivity): Int {
        ctAgentActivity.updateTime = DateUtils.getNowDate()
        return ctAgentActivityMapper.updateCtAgentActivity(ctAgentActivity)
    }

    /**
     * 批量删除代理活动
     *
     * @param ids 需要删除的代理活动主键
     * @return 结果
     */
    override fun deleteCtAgentActivityByIds(ids: Array<String>): Int {
        return ctAgentActivityMapper.deleteCtAgentActivityByIds(ids)
    }

    /**
     * 删除代理活动信息
     *
     * @param id 代理活动主键
     * @return 结果
     */
    override fun deleteCtAgentActivityById(id: String): Int {
        return ctAgentActivityMapper.deleteCtAgentActivityById(id)
    }
}
