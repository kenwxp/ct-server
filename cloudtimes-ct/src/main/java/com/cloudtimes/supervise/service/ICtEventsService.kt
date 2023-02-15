package com.cloudtimes.supervise.service

import com.cloudtimes.supervise.domain.CtEvents

/**
 * 事件Service接口
 *
 * @author 沈兵
 * @date 2023-02-14
 */
interface ICtEventsService {
    fun selectByReceiver(receiver: String): List<CtEvents>

    fun selectSummaryByReceiver(receiver: String): List<CtEvents>

    fun selectByReceiverAndMsgType(receiver: String, msgType: String): List<CtEvents>

    /**
     * 查询事件
     *
     * @param id 事件主键
     * @return 事件
     */
    fun selectCtEventsById(id: String): CtEvents?

    /**
     * 查询事件列表
     *
     * @param ctEvents 事件
     * @return 事件集合
     */
    fun selectCtEventsList(ctEvents: CtEvents): List<CtEvents>

    /**
     * 新增事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    fun insertCtEvents(ctEvents: CtEvents): Int

    /**
     * 修改事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    fun updateCtEvents(ctEvents: CtEvents): Int

    /**
     * 批量删除事件
     *
     * @param ids 需要删除的事件主键集合
     * @return 结果
     */
    fun deleteCtEventsByIds(ids: Array<String>): Int

    /**
     * 删除事件信息
     *
     * @param id 事件主键
     * @return 结果
     */
    fun deleteCtEventsById(id: String): Int
}