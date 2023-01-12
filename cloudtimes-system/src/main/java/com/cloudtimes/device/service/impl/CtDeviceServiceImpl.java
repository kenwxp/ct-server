package com.cloudtimes.device.service.impl;

import java.util.List;

import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import com.cloudtimes.device.mapper.CtDeviceMapper;
import com.cloudtimes.device.domain.CtDevice;
import com.cloudtimes.device.service.ICtDeviceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 电子设备管理Service业务层处理
 * 
 * @author tank
 * @date 2023-01-12
 */
@DataSource(DataSourceType.CT)
@Service
public class CtDeviceServiceImpl implements ICtDeviceService 
{
    @Autowired
    private CtDeviceMapper ctDeviceMapper;

    /**
     * 查询电子设备管理
     * 
     * @param id 电子设备管理主键
     * @return 电子设备管理
     */
    @Override
    public CtDevice selectCtDeviceById(Long id)
    {
        return ctDeviceMapper.selectCtDeviceById(id);
    }

    /**
     * 查询电子设备管理列表
     * 
     * @param ctDevice 电子设备管理
     * @return 电子设备管理
     */
    @Override
    public List<CtDevice> selectCtDeviceList(CtDevice ctDevice)
    {
        return ctDeviceMapper.selectCtDeviceList(ctDevice);
    }

    /**
     * 新增电子设备管理
     * 
     * @param ctDevice 电子设备管理
     * @return 结果
     */
    @Override
    public int insertCtDevice(CtDevice ctDevice)
    {
        ctDevice.setCreateTime(DateUtils.getNowDate());
        return ctDeviceMapper.insertCtDevice(ctDevice);
    }

    /**
     * 修改电子设备管理
     * 
     * @param ctDevice 电子设备管理
     * @return 结果
     */
    @Override
    public int updateCtDevice(CtDevice ctDevice)
    {
        ctDevice.setUpdateTime(DateUtils.getNowDate());
        return ctDeviceMapper.updateCtDevice(ctDevice);
    }

    /**
     * 批量删除电子设备管理
     * 
     * @param ids 需要删除的电子设备管理主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceByIds(Long[] ids)
    {
        return ctDeviceMapper.deleteCtDeviceByIds(ids);
    }

    /**
     * 删除电子设备管理信息
     * 
     * @param id 电子设备管理主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceById(Long id)
    {
        return ctDeviceMapper.deleteCtDeviceById(id);
    }
}
