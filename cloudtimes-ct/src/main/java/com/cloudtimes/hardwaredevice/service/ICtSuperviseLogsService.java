package com.cloudtimes.hardwaredevice.service;

import java.util.List;

import com.cloudtimes.hardwaredevice.domain.CtSuperviseLogs;

/**
 * 申请值守日志Service接口
 *
 * @author tank
 * @date 2023-02-08
 */
public interface ICtSuperviseLogsService {
    /**
     * 查询申请值守日志
     *
     * @param id 申请值守日志主键
     * @return 申请值守日志
     */
    public CtSuperviseLogs selectCtSuperviseLogsById(String id);

    /**
     * 查询申请值守日志列表
     *
     * @param ctSuperviseLogs 申请值守日志
     * @return 申请值守日志集合
     */
    public List<CtSuperviseLogs> selectCtSuperviseLogsList(CtSuperviseLogs ctSuperviseLogs);

    /**
     * 新增申请值守日志
     *
     * @param ctSuperviseLogs 申请值守日志
     * @return 结果
     */
    public int insertCtSuperviseLogs(CtSuperviseLogs ctSuperviseLogs);

    /**
     * 修改申请值守日志
     *
     * @param ctSuperviseLogs 申请值守日志
     * @return 结果
     */
    public int updateCtSuperviseLogs(CtSuperviseLogs ctSuperviseLogs);

    /**
     * 批量删除申请值守日志
     *
     * @param ids 需要删除的申请值守日志主键集合
     * @return 结果
     */
    public int deleteCtSuperviseLogsByIds(String[] ids);

    /**
     * 删除申请值守日志信息
     *
     * @param id 申请值守日志主键
     * @return 结果
     */
    public int deleteCtSuperviseLogsById(String id);
}
