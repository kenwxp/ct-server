package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtAgentActivity2
import com.cloudtimes.account.mapper.CtAgentActivity2Mapper
import com.cloudtimes.account.service.ICtAgentActivity2Service
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 代理活动2Service业务层处理
 *
 * @author tank
 * @date 2023-02-13
 */
@DataSource(DataSourceType.CT)
@Service
class CtAgentActivity2ServiceImpl : ICtAgentActivity2Service {
    @Autowired
    private lateinit var ctAgentActivity2Mapper: CtAgentActivity2Mapper

    /**
     * 查询代理活动2
     *
     * @param id 代理活动2主键
     * @return 代理活动2
     */
    override fun selectCtAgentActivity2ById(id: String): CtAgentActivity2? {
        return ctAgentActivity2Mapper.selectCtAgentActivity2ById(id)
    }

    /**
     * 查询代理活动2列表
     *
     * @param ctAgentActivity2 代理活动2
     * @return 代理活动2
     */
    override fun selectCtAgentActivity2List(ctAgentActivity2: CtAgentActivity2): List<CtAgentActivity2> {
        return ctAgentActivity2Mapper.selectCtAgentActivity2List(ctAgentActivity2)
    }

    /**
     * 新增代理活动2
     *
     * @param ctAgentActivity2 代理活动2
     * @return 结果
     */
    override fun insertCtAgentActivity2(ctAgentActivity2: CtAgentActivity2): Int {
        ctAgentActivity2!!.createTime = DateUtils.getNowDate()
        return ctAgentActivity2Mapper.insertCtAgentActivity2(ctAgentActivity2)
    }

    /**
     * 修改代理活动2
     *
     * @param ctAgentActivity2 代理活动2
     * @return 结果
     */
    override fun updateCtAgentActivity2(ctAgentActivity2: CtAgentActivity2): Int {
        ctAgentActivity2!!.updateTime = DateUtils.getNowDate()
        return ctAgentActivity2Mapper.updateCtAgentActivity2(ctAgentActivity2)
    }

    /**
     * 批量删除代理活动2
     *
     * @param ids 需要删除的代理活动2主键
     * @return 结果
     */
    override fun deleteCtAgentActivity2ByIds(ids: Array<String>): Int {
        return ctAgentActivity2Mapper.deleteCtAgentActivity2ByIds(ids)
    }

    /**
     * 删除代理活动2信息
     *
     * @param id 代理活动2主键
     * @return 结果
     */
    override fun deleteCtAgentActivity2ById(id: String): Int {
        return ctAgentActivity2Mapper.deleteCtAgentActivity2ById(id)
    }
}