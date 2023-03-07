package com.cloudtimes.hardwaredevice.service;

import com.cloudtimes.hardwaredevice.domain.ActivateDeviceReq;
import com.cloudtimes.hardwaredevice.domain.CtDevice;

import java.util.List;

/**
 * 电子设备Service接口
 *
 * @author tank
 * @date 2023-01-17
 */
public interface ICtDeviceService {
    /**
     * 查询电子设备
     *
     * @param id 电子设备主键
     * @return 电子设备
     */
    public CtDevice selectCtDeviceById(String id);

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
     * 批量删除电子设备
     *
     * @param ids 需要删除的电子设备主键集合
     * @return 结果
     */
    public int deleteCtDeviceByIds(Long[] ids);

    /**
     * 删除电子设备信息
     *
     * @param id 电子设备主键
     * @return 结果
     */
    public int deleteCtDeviceById(Long id);

    /**
     * 新增电子设备
     *
     * @param param 电子设备
     * @return 结果
     */
    public int activateCtDevice(ActivateDeviceReq param);

}
