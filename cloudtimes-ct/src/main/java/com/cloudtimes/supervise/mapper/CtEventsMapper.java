package com.cloudtimes.supervise.mapper;

import com.cloudtimes.supervise.domain.CtEvents;

import java.util.List;

/**
 * 事件Mapper接口
 *
 * @author wangxp
 * @date 2023-02-07
 */
public interface CtEventsMapper {
    /**
     * 查询事件
     *
     * @param id 事件主键
     * @return 事件
     */
    public CtEvents selectCtEventsById(String id);

    /**
     * 查询事件列表
     *
     * @param ctEvents 事件
     * @return 事件集合
     */
    public List<CtEvents> selectCtEventsList(CtEvents ctEvents);

    /**
     * 新增事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    public int insertCtEvents(CtEvents ctEvents);

    /**
     * 修改事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    public int updateCtEvents(CtEvents ctEvents);

    /**
     * 删除事件
     *
     * @param id 事件主键
     * @return 结果
     */
    public int deleteCtEventsById(String id);

    /**
     * 批量删除事件
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtEventsByIds(String[] ids);
}
