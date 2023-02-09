package com.cloudtimes.hardwaredevice.service;

import java.util.List;

import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;

/**
 * 开门日志Service接口
 *
 * @author tank
 * @date 2023-02-08
 */
public interface ICtOpenDoorLogsService {
    /**
     * 查询开门日志
     *
     * @param id 开门日志主键
     * @return 开门日志
     */
    public CtOpenDoorLogs selectCtOpenDoorLogsById(String id);

    /**
     * 查询开门日志列表
     *
     * @param ctOpenDoorLogs 开门日志
     * @return 开门日志集合
     */
    public List<CtOpenDoorLogs> selectCtOpenDoorLogsList(CtOpenDoorLogs ctOpenDoorLogs);

    /**
     * 新增开门日志
     *
     * @param ctOpenDoorLogs 开门日志
     * @return 结果
     */
    public int insertCtOpenDoorLogs(CtOpenDoorLogs ctOpenDoorLogs);

    /**
     * 修改开门日志
     *
     * @param ctOpenDoorLogs 开门日志
     * @return 结果
     */
    public int updateCtOpenDoorLogs(CtOpenDoorLogs ctOpenDoorLogs);

    /**
     * 批量删除开门日志
     *
     * @param ids 需要删除的开门日志主键集合
     * @return 结果
     */
    public int deleteCtOpenDoorLogsByIds(String[] ids);

    /**
     * 删除开门日志信息
     *
     * @param id 开门日志主键
     * @return 结果
     */
    public int deleteCtOpenDoorLogsById(String id);
}
