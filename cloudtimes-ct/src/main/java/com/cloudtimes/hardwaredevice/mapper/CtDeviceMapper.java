package com.cloudtimes.hardwaredevice.mapper;

import com.cloudtimes.hardwaredevice.domain.CtDevice;

import java.util.List;

/**
 * 电子设备Mapper接口
 *
 * @author tank
 * @date 2023-01-17
 */
public interface CtDeviceMapper {
    /**
     * 查询电子设备
     *
     * @param id 电子设备主键
     * @return 电子设备
     */
    public CtDevice selectCtDeviceById(String id);

    /**
     * 根据设备序列号查询电子设备
     *
     * @param deviceSerial 电子设备主键
     * @return 电子设备
     */
    public CtDevice selectCtDeviceByDeviceSerial(String deviceSerial);

    /**
     * 查询电子设备列表
     *
     * @param ctDevice 电子设备
     * @return 电子设备集合
     */
    public List<CtDevice> selectCtDeviceList(CtDevice ctDevice);

    /**
     * 新增电子设备
     *
     * @param ctDevice 电子设备
     * @return 结果
     */
    public int insertCtDevice(CtDevice ctDevice);

    /**
     * 修改电子设备
     *
     * @param ctDevice 电子设备
     * @return 结果
     */
    public int updateCtDevice(CtDevice ctDevice);

    /**
     * 删除电子设备
     *
     * @param id 电子设备主键
     * @return 结果
     */
    public int deleteCtDeviceById(Long id);

    /**
     * 批量删除电子设备
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtDeviceByIds(String[] ids);
}
