package com.cloudtimes.hardwaredevice.service.impl;

import java.util.Date;
import java.util.List;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.dto.CtDeviceDoorDto;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.service.ICtDeviceDoorService;

import static com.cloudtimes.common.utils.SecurityUtils.getUserId;

/**
 * 门禁设备密码Service业务层处理
 *
 * @author tank
 * @date 2023-02-09
 */
@DataSource(DataSourceType.CT)
@Service
public class CtDeviceDoorServiceImpl implements ICtDeviceDoorService {
    @Autowired
    private CtDeviceDoorMapper ctDeviceDoorMapper;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtRocketMqProducer producer;

    /**
     * 查询门禁设备密码
     *
     * @param id 门禁设备密码主键
     * @return 门禁设备密码
     */
    @Override
    public CtDeviceDoor selectCtDeviceDoorById(String id) {
        return ctDeviceDoorMapper.selectCtDeviceDoorById(id);
    }

    /**
     * 查询门禁设备密码列表
     *
     * @param ctDeviceDoor 门禁设备密码
     * @return 门禁设备密码
     */
    @Override
    public List<CtDeviceDoor> selectCtDeviceDoorList(CtDeviceDoor ctDeviceDoor) {
        return ctDeviceDoorMapper.selectCtDeviceDoorList(ctDeviceDoor);
    }

    @Override
    public List<CtDeviceDoorDto> selectCtDeviceDoorListPlus(CtDeviceDoor ctDeviceDoor) {
        return ctDeviceDoorMapper.selectCtDeviceDoorListPlus(ctDeviceDoor);
    }

    /**
     * 新增门禁设备密码
     *
     * @param ctDeviceDoor 门禁设备密码
     * @return 结果
     */
    @Override
    public int insertCtDeviceDoor(CtDeviceDoor ctDeviceDoor) {
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
    public int updateCtDeviceDoor(CtDeviceDoor ctDeviceDoor) {
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
    public int deleteCtDeviceDoorByIds(String[] ids) {
        return ctDeviceDoorMapper.deleteCtDeviceDoorByIds(ids);
    }

    /**
     * 删除门禁设备密码信息
     *
     * @param id 门禁设备密码主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceDoorById(String id) {
        return ctDeviceDoorMapper.deleteCtDeviceDoorById(id);
    }

    /**
     * @param userId
     * @param deviceId
     * @return
     */
    @Override
    public int emergencyOpenDoor(Long userId, String deviceId) {
        CtDevice ctDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (ctDevice == null) {
            throw new ServiceException("无法获取门禁设备");
        }
        if (StringUtils.isEmpty(ctDevice.getStoreId())) {
            throw new ServiceException("设备未绑定门店");
        }
        producer.send(RocketMQConstants.CT_OPEN_DOOR, new OpenDoorMqData(OpenDoorOption.EMERGENCY_OPEN_DOOR, ctDevice.getStoreId(), getUserId().toString(), ChannelType.WEB));
        return 1;
    }

    /**
     * 设置门禁密码
     *
     * @return
     */
    @Override
    public int setDoorAccess(Long userId, String deviceId) {
        CtDevice ctDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (ctDevice == null) {
            throw new ServiceException("无法获取门禁设备");
        }
        if (StringUtils.isEmpty(ctDevice.getStoreId())) {
            throw new ServiceException("设备未绑定门店");
        }
        OpenDoorMqData openDoorMqData = new OpenDoorMqData();
        openDoorMqData.setOption(OpenDoorOption.SETTING_DOOR_ACCESS);
        openDoorMqData.setStoreId(ctDevice.getStoreId());
        openDoorMqData.setDeviceId(ctDevice.getId());
        openDoorMqData.setUserId(String.valueOf(userId));
        openDoorMqData.setChannelType(ChannelType.WEB);
        Date nowDate = DateUtils.getNowDate();
        openDoorMqData.setBeginTime(DateUtils.formatDateTime(nowDate));
        openDoorMqData.setEndTime(DateUtils.formatDateTime(DateUtils.addDays(nowDate, 9999)));
        producer.send(RocketMQConstants.CT_OPEN_DOOR, openDoorMqData);
        return 1;
    }
}
