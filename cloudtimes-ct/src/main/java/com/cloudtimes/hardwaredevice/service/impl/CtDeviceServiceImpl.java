package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;

/**
 * 电子设备Service业务层处理
 * 
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtDeviceServiceImpl implements ICtDeviceService 
{
    @Autowired
    private CtDeviceMapper ctDeviceMapper;

    /**
     * 查询电子设备
     * 
     * @param id 电子设备主键
     * @return 电子设备
     */
    @Override
    public CtDevice selectCtDeviceById(String id)
    {
        return ctDeviceMapper.selectCtDeviceById(id);
    }

    /**
     * 查询电子设备列表
     * 
     * @param ctDevice 电子设备
     * @return 电子设备
     */
    @Override
    public List<CtDevice> selectCtDeviceList(CtDevice ctDevice)
    {
        return ctDeviceMapper.selectCtDeviceList(ctDevice);
    }

    /**
     * 新增电子设备
     * 
     * @param ctDevice 电子设备
     * @return 结果
     */
    @Override
    public int insertCtDevice(CtDevice ctDevice)
    {
        ctDevice.setCreateTime(DateUtils.getNowDate());
        return ctDeviceMapper.insertCtDevice(ctDevice);
    }

    /**
     * 修改电子设备
     * 
     * @param ctDevice 电子设备
     * @return 结果
     */
    @Override
    public int updateCtDevice(CtDevice ctDevice)
    {
        ctDevice.setUpdateTime(DateUtils.getNowDate());
        return ctDeviceMapper.updateCtDevice(ctDevice);
    }

    /**
     * 批量删除电子设备
     * 
     * @param ids 需要删除的电子设备主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceByIds(Long[] ids)
    {
        return ctDeviceMapper.deleteCtDeviceByIds(ids);
    }

    /**
     * 删除电子设备信息
     * 
     * @param id 电子设备主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceById(Long id)
    {
        return ctDeviceMapper.deleteCtDeviceById(id);
    }
}
