package com.cloudtimes.hardwaredevice.mapper;

import java.util.List;
import com.cloudtimes.hardwaredevice.domain.CtSuperviseLogs;

/**
 * 申请值守日志Mapper接口
 * 
 * @author tank
 * @date 2023-01-17
 */
public interface CtSuperviseLogsMapper 
{
    /**
     * 查询申请值守日志
     * 
     * @param id 申请值守日志主键
     * @return 申请值守日志
     */
    public CtSuperviseLogs selectCtSuperviseLogsById(Long id);

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
     * 删除申请值守日志
     * 
     * @param id 申请值守日志主键
     * @return 结果
     */
    public int deleteCtSuperviseLogsById(Long id);

    /**
     * 批量删除申请值守日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtSuperviseLogsByIds(Long[] ids);
}
