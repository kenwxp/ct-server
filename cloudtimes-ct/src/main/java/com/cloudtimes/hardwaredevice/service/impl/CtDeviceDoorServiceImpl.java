package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.service.ICtDeviceDoorService;

/**
 * 门禁设备密码Service业务层处理
 * 
 * @author tank
 * @date 2023-02-09
 */
@DataSource(DataSourceType.CT)
@Service
public class CtDeviceDoorServiceImpl implements ICtDeviceDoorService 
{
    @Autowired
    private CtDeviceDoorMapper ctDeviceDoorMapper;

    /**
     * 查询门禁设备密码
     * 
     * @param id 门禁设备密码主键
     * @return 门禁设备密码
     */
    @Override
    public CtDeviceDoor selectCtDeviceDoorById(String id)
    {
        return ctDeviceDoorMapper.selectCtDeviceDoorById(id);
    }

    /**
     * 查询门禁设备密码列表
     * 
     * @param ctDeviceDoor 门禁设备密码
     * @return 门禁设备密码
     */
    @Override
    public List<CtDeviceDoor> selectCtDeviceDoorList(CtDeviceDoor ctDeviceDoor)
    {
        return ctDeviceDoorMapper.selectCtDeviceDoorList(ctDeviceDoor);
    }

    /**
     * 新增门禁设备密码
     * 
     * @param ctDeviceDoor 门禁设备密码
     * @return 结果
     */
    @Override
    public int insertCtDeviceDoor(CtDeviceDoor ctDeviceDoor)
    {
        ctDeviceDoor.setCreateTime(DateUtils.getNowDate());
        return ctDeviceDoorMapper.insertCtDeviceDoor(ctDeviceDoor);
    }

    /**
     * 修改门禁设备密码
     * 
     * @param ctDeviceDoor 门禁设备密码
     * @return 结果
     */
    @Override
    public int updateCtDeviceDoor(CtDeviceDoor ctDeviceDoor)
    {
        ctDeviceDoor.setUpdateTime(DateUtils.getNowDate());
        return ctDeviceDoorMapper.updateCtDeviceDoor(ctDeviceDoor);
    }

    /**
     * 批量删除门禁设备密码
     * 
     * @param ids 需要删除的门禁设备密码主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceDoorByIds(String[] ids)
    {
        return ctDeviceDoorMapper.deleteCtDeviceDoorByIds(ids);
    }

    /**
     * 删除门禁设备密码信息
     * 
     * @param id 门禁设备密码主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceDoorById(String id)
    {
        return ctDeviceDoorMapper.deleteCtDeviceDoorById(id);
    }
}
