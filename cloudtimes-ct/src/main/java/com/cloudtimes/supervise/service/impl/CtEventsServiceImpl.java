package com.cloudtimes.supervise.service.impl;

import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.supervise.domain.CtEvents;
import com.cloudtimes.supervise.mapper.CtEventsMapper;
import com.cloudtimes.supervise.service.ICtEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 事件Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
public class CtEventsServiceImpl implements ICtEventsService {
    @Autowired
    private CtEventsMapper ctEventsMapper;

    /**
     * 查询事件
     *
     * @param id 事件主键
     * @return 事件
     */
    @Override
    public CtEvents selectCtEventsById(String id) {
        return ctEventsMapper.selectCtEventsById(id);
    }

    /**
     * 查询事件列表
     *
     * @param ctEvents 事件
     * @return 事件
     */
    @Override
    public List<CtEvents> selectCtEventsList(CtEvents ctEvents) {
        return ctEventsMapper.selectCtEventsList(ctEvents);
    }

    /**
     * 新增事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    @Override
    public int insertCtEvents(CtEvents ctEvents) {
        ctEvents.setCreateTime(DateUtils.getNowDate());
        return ctEventsMapper.insertCtEvents(ctEvents);
    }

    /**
     * 修改事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    @Override
    public int updateCtEvents(CtEvents ctEvents) {
        ctEvents.setUpdateTime(DateUtils.getNowDate());
        return ctEventsMapper.updateCtEvents(ctEvents);
    }

    /**
     * 批量删除事件
     *
     * @param ids 需要删除的事件主键
     * @return 结果
     */
    @Override
    public int deleteCtEventsByIds(String[] ids) {
        return ctEventsMapper.deleteCtEventsByIds(ids);
    }

    /**
     * 删除事件信息
     *
     * @param id 事件主键
     * @return 结果
     */
    @Override
    public int deleteCtEventsById(String id) {
        return ctEventsMapper.deleteCtEventsById(id);
    }
}
