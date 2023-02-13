package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtAgentActivity1
import com.cloudtimes.account.mapper.CtAgentActivity1Mapper
import com.cloudtimes.account.service.ICtAgentActivity1Service
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动1Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-13
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivity1ServiceImpl : ICtAgentActivity1Service {
    @Autowired
    private lateinit var ctAgentActivity1Mapper: CtAgentActivity1Mapper

    /**
     * 查询代理活动1
     *
     * @param id 代理活动1主键
     * @return 代理活动1
     */
    override fun selectCtAgentActivity1ById(id: String): CtAgentActivity1? {
        return ctAgentActivity1Mapper.selectCtAgentActivity1ById(id)
    }

    /**
     * 查询代理活动1列表
     *
     * @param ctAgentActivity1 代理活动1
     * @return 代理活动1
     */
    override fun selectCtAgentActivity1List(ctAgentActivity1: CtAgentActivity1): List<CtAgentActivity1> {
        return ctAgentActivity1Mapper.selectCtAgentActivity1List(ctAgentActivity1)
    }

    /**
     * 新增代理活动1
     *
     * @param ctAgentActivity1 代理活动1
     * @return 结果
     */
    override fun insertCtAgentActivity1(ctAgentActivity1: CtAgentActivity1): Int {
        ctAgentActivity1!!.createTime = DateUtils.getNowDate()
        return ctAgentActivity1Mapper.insertCtAgentActivity1(ctAgentActivity1)
    }

    /**
     * 修改代理活动1
     *
     * @param ctAgentActivity1 代理活动1
     * @return 结果
     */
    override fun updateCtAgentActivity1(ctAgentActivity1: CtAgentActivity1): Int {
        ctAgentActivity1!!.updateTime = DateUtils.getNowDate()
        return ctAgentActivity1Mapper.updateCtAgentActivity1(ctAgentActivity1)
    }

    /**
     * 批量删除代理活动1
     *
     * @param ids 需要删除的代理活动1主键
     * @return 结果
     */
    override fun deleteCtAgentActivity1ByIds(ids: Array<String>): Int {
        return ctAgentActivity1Mapper.deleteCtAgentActivity1ByIds(ids)
    }

    /**
     * 删除代理活动1信息
     *
     * @param id 代理活动1主键
     * @return 结果
     */
    override fun deleteCtAgentActivity1ById(id: String): Int {
        return ctAgentActivity1Mapper.deleteCtAgentActivity1ById(id)
    }
}