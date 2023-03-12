package com.cloudtimes.hardwaredevice.mapper;

import java.util.List;

import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.domain.dto.CtOpenDoorLogsDto;
import org.apache.ibatis.annotations.Param;

/**
 * 开门日志Mapper接口
 *
 * @author tank
 * @date 2023-02-08
 */
public interface CtOpenDoorLogsMapper {
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

    public List<CtOpenDoorLogsDto> selectCtOpenDoorLogsListPlus(CtOpenDoorLogs ctOpenDoorLogs);

    /**
     * 查询最近的开门日志
     *
     * @param storeId
     * @param optType
     * @return
     */
    public CtOpenDoorLogs selectLatestOpenDoorLogByStore(@Param("storeId") String storeId, @Param("optType") String optType);

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
     * 删除开门日志
     *
     * @param id 开门日志主键
     * @return 结果
     */
    public int deleteCtOpenDoorLogsById(String id);

    /**
     * 批量删除开门日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtOpenDoorLogsByIds(String[] ids);
}
