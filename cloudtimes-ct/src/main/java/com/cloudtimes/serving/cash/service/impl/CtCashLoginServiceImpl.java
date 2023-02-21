package com.cloudtimes.serving.cash.service.impl;

import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.serving.cash.service.ICtCashLoginService;
import com.cloudtimes.common.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CtCashLoginServiceImpl implements ICtCashLoginService {
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtStoreMapper storeMapper;

    @Override
    public boolean checkCashNew(String deviceSerial) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceByDeviceSerial(deviceSerial);
        if (dbDevice != null) {
            return false;
        }
        return true;
    }

    @Override
    public CtDevice cashLogin(String deviceSerial, String deviceName, String shopNo) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceByDeviceSerial(deviceSerial);
        if (dbDevice == null) {
            if (StringUtils.isEmpty(shopNo)) {
                throw new ServiceException("门店号不能为空");
            }
            CtStore dbStore = storeMapper.selectCtStoreByStoreNo(shopNo);
            if (dbStore == null) {
                throw new ServiceException("门店不存在");
            }
            CtDevice newDevice = new CtDevice();
            newDevice.setName(deviceName);
            newDevice.setStoreId(dbStore.getId());
            newDevice.setDeviceCode(NoUtils.genDeviceCode());
            newDevice.setDeviceType(DeviceType.CASH.getCode());
            newDevice.setDeviceModel("收银机");
            newDevice.setBrandName("");
            newDevice.setDeviceSerial(deviceSerial);
            newDevice.setDeviceArea("0");
            newDevice.setValidateCode("");
            newDevice.setDeviceAscription("0");
            newDevice.setState("0");
            newDevice.setDelFlag("0");
            newDevice.setCreateTime(new Date());
            newDevice.setUpdateTime(new Date());
            if (deviceMapper.insertCtDevice(newDevice) < 1) {
                throw new ServiceException("新增设备失败");
            }
            return newDevice;
        } else {
            return dbDevice;
        }
    }
}
