package com.cloudtimes.device.mapper;

import java.util.List;
import com.cloudtimes.device.domain.CtDevice;

/**
 * 电子设备管理Mapper接口
 * 
 * @author tank
 * @date 2023-01-12
 */
public interface CtDeviceMapper 
{
    /**
     * 查询电子设备管理
     * 
     * @param id 电子设备管理主键
     * @return 电子设备管理
     */
    public CtDevice selectCtDeviceById(Long id);

    /**
     * 查询电子设备管理列表
     * 
     * @param ctDevice 电子设备管理
     * @return 电子设备管理集合
     */
    public List<CtDevice> selectCtDeviceList(CtDevice ctDevice);

    /**
     * 新增电子设备管理
     * 
     * @param ctDevice 电子设备管理
     * @return 结果
     */
    public int insertCtDevice(CtDevice ctDevice);

    /**
     * 修改电子设备管理
     * 
     * @param ctDevice 电子设备管理
     * @return 结果
     */
    public int updateCtDevice(CtDevice ctDevice);

    /**
     * 删除电子设备管理
     * 
     * @param id 电子设备管理主键
     * @return 结果
     */
    public int deleteCtDeviceById(Long id);

    /**
     * 批量删除电子设备管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtDeviceByIds(Long[] ids);
}
