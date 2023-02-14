package com.cloudtimes.supervise.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.supervise.domain.CtEvents
import com.cloudtimes.supervise.mapper.CtEventsMapper
import com.cloudtimes.supervise.service.ICtEventsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 事件Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@DataSource(DataSourceType.CT)
@Service
class CtEventsServiceImpl : ICtEventsService {
    @Autowired
    private lateinit var ctEventsMapper: CtEventsMapper

    /**
     * 查询事件
     *
     * @param id 事件主键
     * @return 事件
     */
    override fun selectCtEventsById(id: String): CtEvents? {
        return ctEventsMapper.selectCtEventsById(id)
    }

    /**
     * 查询事件列表
     *
     * @param ctEvents 事件
     * @return 事件
     */
    override fun selectCtEventsList(ctEvents: CtEvents): List<CtEvents> {
        return ctEventsMapper.selectCtEventsList(ctEvents)
    }

    /**
     * 新增事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    override fun insertCtEvents(ctEvents: CtEvents): Int {
        ctEvents.createTime = DateUtils.getNowDate()
        return ctEventsMapper.insertCtEvents(ctEvents)
    }

    /**
     * 修改事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    override fun updateCtEvents(ctEvents: CtEvents): Int {
        ctEvents.updateTime = DateUtils.getNowDate()
        return ctEventsMapper.updateCtEvents(ctEvents)
    }

    /**
     * 批量删除事件
     *
     * @param ids 需要删除的事件主键
     * @return 结果
     */
    override fun deleteCtEventsByIds(ids: Array<String>): Int {
        return ctEventsMapper.deleteCtEventsByIds(ids)
    }

    /**
     * 删除事件信息
     *
     * @param id 事件主键
     * @return 结果
     */
    override fun deleteCtEventsById(id: String): Int {
        return ctEventsMapper.deleteCtEventsById(id)
    }
}