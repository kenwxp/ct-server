package com.cloudtimes.hardwaredevice.service;

import java.util.List;

import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.domain.dto.CtDeviceDoorDto;

/**
 * 门禁设备密码Service接口
 *
 * @author tank
 * @date 2023-02-09
 */
public interface ICtDeviceDoorService {
    /**
     * 查询门禁设备密码
     *
     * @param id 门禁设备密码主键
     * @return 门禁设备密码
     */
    public CtDeviceDoor selectCtDeviceDoorById(String id);

    /**
     * 查询门禁设备密码列表
     *
     * @param ctDeviceDoor 门禁设备密码
     * @return 门禁设备密码集合
     */
    public List<CtDeviceDoor> selectCtDeviceDoorList(CtDeviceDoor ctDeviceDoor);

    public List<CtDeviceDoorDto> selectCtDeviceDoorListPlus(CtDeviceDoor ctDeviceDoor);

    /**
     * 新增门禁设备密码
     *
     * @param ctDeviceDoor 门禁设备密码
     * @return 结果
     */
    public int insertCtDeviceDoor(CtDeviceDoor ctDeviceDoor);

    /**
     * 修改门禁设备密码
     *
     * @param ctDeviceDoor 门禁设备密码
     * @return 结果
     */
    public int updateCtDeviceDoor(CtDeviceDoor ctDeviceDoor);

    /**
     * 批量删除门禁设备密码
     *
     * @param ids 需要删除的门禁设备密码主键集合
     * @return 结果
     */
    public int deleteCtDeviceDoorByIds(String[] ids);

    /**
     * 删除门禁设备密码信息
     *
     * @param id 门禁设备密码主键
     * @return 结果
     */
    public int deleteCtDeviceDoorById(String id);
}
