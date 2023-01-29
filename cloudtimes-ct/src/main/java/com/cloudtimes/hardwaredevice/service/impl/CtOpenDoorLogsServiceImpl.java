package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.service.ICtOpenDoorLogsService;

/**
 * 开门日志Service业务层处理
 * 
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtOpenDoorLogsServiceImpl implements ICtOpenDoorLogsService 
{
    @Autowired
    private CtOpenDoorLogsMapper ctOpenDoorLogsMapper;

    /**
     * 查询开门日志
     * 
     * @param id 开门日志主键
     * @return 开门日志
     */
    @Override
    public CtOpenDoorLogs selectCtOpenDoorLogsById(Long id)
    {
        return ctOpenDoorLogsMapper.selectCtOpenDoorLogsById(id);
    }

    /**
     * 查询开门日志列表
     * 
     * @param ctOpenDoorLogs 开门日志
     * @return 开门日志
     */
    @Override
    public List<CtOpenDoorLogs> selectCtOpenDoorLogsList(CtOpenDoorLogs ctOpenDoorLogs)
    {
        return ctOpenDoorLogsMapper.selectCtOpenDoorLogsList(ctOpenDoorLogs);
    }

    /**
     * 新增开门日志
     * 
     * @param ctOpenDoorLogs 开门日志
     * @return 结果
     */
    @Override
    public int insertCtOpenDoorLogs(CtOpenDoorLogs ctOpenDoorLogs)
    {
        ctOpenDoorLogs.setCreateTime(DateUtils.getNowDate());
        return ctOpenDoorLogsMapper.insertCtOpenDoorLogs(ctOpenDoorLogs);
    }

    /**
     * 修改开门日志
     * 
     * @param ctOpenDoorLogs 开门日志
     * @return 结果
     */
    @Override
    public int updateCtOpenDoorLogs(CtOpenDoorLogs ctOpenDoorLogs)
    {
        ctOpenDoorLogs.setUpdateTime(DateUtils.getNowDate());
        return ctOpenDoorLogsMapper.updateCtOpenDoorLogs(ctOpenDoorLogs);
    }

    /**
     * 批量删除开门日志
     * 
     * @param ids 需要删除的开门日志主键
     * @return 结果
     */
    @Override
    public int deleteCtOpenDoorLogsByIds(Long[] ids)
    {
        return ctOpenDoorLogsMapper.deleteCtOpenDoorLogsByIds(ids);
    }

    /**
     * 删除开门日志信息
     * 
     * @param id 开门日志主键
     * @return 结果
     */
    @Override
    public int deleteCtOpenDoorLogsById(Long id)
    {
        return ctOpenDoorLogsMapper.deleteCtOpenDoorLogsById(id);
    }
}
