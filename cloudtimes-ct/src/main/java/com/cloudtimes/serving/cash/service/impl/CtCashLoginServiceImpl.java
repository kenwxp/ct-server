package com.cloudtimes.serving.cash.service.impl;

import com.cloudtimes.common.enums.DeviceState;
import com.cloudtimes.common.enums.YesNoState;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import com.cloudtimes.serving.cash.service.ICtCashLoginService;
import com.cloudtimes.common.NoUtils;
import com.cloudtimes.serving.cash.service.domain.CashLoginCheckResp;
import com.cloudtimes.serving.cash.service.domain.CashLoginReq;
import com.cloudtimes.serving.cash.service.domain.CashLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CtCashLoginServiceImpl implements ICtCashLoginService {
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private ICtCashBusinessService cashBusinessService;

    @Override
    public CashLoginCheckResp checkCashNew(CashLoginReq param) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceByDeviceSerial(param.getDeviceSerial());
        CashLoginCheckResp cashLoginCheckResp = new CashLoginCheckResp();
        if (dbDevice == null) {
            cashLoginCheckResp.setIsNew(YesNoState.Yes.getCode());
        } else {
            cashLoginCheckResp.setIsNew(YesNoState.No.getCode());
        }
        return cashLoginCheckResp;
    }

    @Override
    public CashLoginResp cashLogin(CashLoginReq param) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceByDeviceSerial(param.getDeviceSerial());
        CtStore dbStore = null;
        if (dbDevice == null) {
            if (StringUtils.isEmpty(param.getShopNo())) {
                throw new ServiceException("门店号不能为空");
            }
            dbStore = storeMapper.selectCtStoreByStoreNo(param.getShopNo());
            if (dbStore == null) {
                throw new ServiceException("门店不存在");
            }
            CtDevice newDevice = new CtDevice();
            newDevice.setName(param.getDeviceName());
            newDevice.setStoreId(dbStore.getId());
            newDevice.setDeviceCode(NoUtils.genDeviceCode());
            newDevice.setDeviceType(DeviceType.CASH.getCode());
            newDevice.setDeviceModel("收银机");
            newDevice.setBrandName("");
            newDevice.setDeviceSerial(param.getDeviceSerial());
            newDevice.setDeviceArea("0");
            newDevice.setValidateCode("");
            newDevice.setDeviceAscription("0");
            newDevice.setState(DeviceState.Offline.getCode());
            newDevice.setLoginType("0");
            newDevice.setDelFlag("0");
            newDevice.setCreateTime(new Date());
            newDevice.setUpdateTime(new Date());
            if (deviceMapper.insertCtDevice(newDevice) < 1) {
                throw new ServiceException("新增设备失败");
            }
        } else {
            dbStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
        }
        if (dbStore == null) {
            throw new ServiceException("无法获取门店信息");
        }
        CashLoginResp loginResp = new CashLoginResp();
        //门店信息
        loginResp.setDeviceId(dbDevice.getId());
        loginResp.setLoginType(dbDevice.getLoginType());
        loginResp.setIsSupervise(dbStore.getIsSupervise());
        loginResp.setShopNo(dbStore.getStoreNo());
        loginResp.setShopName(dbStore.getName());
        loginResp.setContactName(dbStore.getContactName());
        loginResp.setContactPhone(dbStore.getContactPhone());
        String qrCodeUrl = cashBusinessService.genDynamicQrCodeUrl(dbDevice.getId(), dbStore.getStoreNo());
        loginResp.setDynamicQrCode(qrCodeUrl);
        return loginResp;
    }
}
