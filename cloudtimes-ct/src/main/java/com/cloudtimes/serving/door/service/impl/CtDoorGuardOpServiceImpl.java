package com.cloudtimes.serving.door.service.impl;

import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.enums.DoorOpenType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.partner.wiegand.ICtWiegandApiService;
import com.cloudtimes.partner.wiegand.WiegandReturning;
import com.cloudtimes.serving.door.service.ICtDoorGuardOpService;
import com.cloudtimes.util.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CtDoorGuardOpServiceImpl implements ICtDoorGuardOpService {
    @Autowired
    private CtStoreMapper storeMapper;

    @Autowired
    private CtDeviceMapper deviceMapper;

    @Autowired
    private ICtWiegandApiService wiegandApiService;

    @Autowired
    private CtOpenDoorLogsMapper openDoorLogsMapper;
    @Autowired
    private CtDeviceDoorMapper deviceDoorMapper;

    /**
     * 交易开门
     * 小程序，刷脸，支付订单成功后调用
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    @Override
    public boolean transOpen(String storeId, String userId, ChannelType channelType) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, DoorOpenType.TRANS_OPEN_DOOR);
        CtStore dbStore = storeMapper.selectCtStoreById(storeId);
        if (dbStore == null) {
            logFail(initLog, "", "无法获取门店信息");
            return false;
        }
        // todo 校验门店逻辑锁

        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        for (CtDevice doorGuard : doorGuardList) {
            String deviceSerial = doorGuard.getDeviceSerial();
            if (StringUtils.equals(dbStore.getIsSupervise(), "0")) {
                //解强锁
                ret = wiegandApiService.settingParams(deviceSerial, 0, 8);
                if (!ret.isSuccess()) {
                    logFail(initLog, doorGuard.getId(), ret.getMsg());
                    return false;
                }
                //远程开门
                ret = wiegandApiService.remoteOpenDoor(deviceSerial);
                if (!ret.isSuccess()) {
                    logFail(initLog, doorGuard.getId(), ret.getMsg());
                    return false;
                }

                //todo 延迟调用
//                ret = wiegandApiService.settingParams(doorGuard.getDeviceSerial(), 2, 0);
//                if (!ret.isSuccess()) {
//                    logFail(initLog, doorGuard.getId(), ret.getMsg());
//                    throw new ServiceException(ret.getMsg());
//                }
            } else {
                //远程开门
                ret = wiegandApiService.remoteOpenDoor(deviceSerial);
                if (!ret.isSuccess()) {
                    logFail(initLog, doorGuard.getId(), ret.getMsg());
                    return false;
                }
            }
            logOk(initLog, doorGuard.getId());
        }

        return true;
    }

    /**
     * 应急开门
     * 管理端开门
     *
     * @param storeId
     * @param userId
     * @return
     */
    @Override
    public boolean emergentOpen(String storeId, String userId) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, ChannelType.WEB, DoorOpenType.EMERGENCY_OPEN_DOOR);
        CtStore dbStore = storeMapper.selectCtStoreById(storeId);
        if (dbStore == null) {
            logFail(initLog, "", "无法获取门店信息");
            return false;
        }
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        for (CtDevice doorGuard : doorGuardList) {
            String deviceSerial = doorGuard.getDeviceSerial();
            if (StringUtils.equals(dbStore.getIsSupervise(), "0")) {
                //解强锁
                ret = wiegandApiService.settingParams(deviceSerial, 0, 8);
                if (!ret.isSuccess()) {
                    logFail(initLog, doorGuard.getId(), ret.getMsg());
                    return false;
                }
                //远程开门
                ret = wiegandApiService.remoteOpenDoor(deviceSerial);
                if (!ret.isSuccess()) {
                    logFail(initLog, doorGuard.getId(), ret.getMsg());
                    return false;
                }
                //todo 延迟调用
//                ret = wiegandApiService.settingParams(doorGuard.getDeviceSerial(), 2, 0);
//                if (!ret.isSuccess()) {
//                    logFail(initLog, doorGuard.getId(), ret.getMsg());
//                    throw new ServiceException(ret.getMsg());
//                }
            } else {
                //远程开门
                ret = wiegandApiService.remoteOpenDoor(deviceSerial);
                if (!ret.isSuccess()) {
                    logFail(initLog, doorGuard.getId(), ret.getMsg());
                    return false;
                }
            }
            logOk(initLog, doorGuard.getId());
        }
        return true;
    }

    /**
     * 店家开门
     * 店家app端
     *
     * @param storeId
     * @param userId
     * @return
     */
    @Override
    public boolean ownerOpen(String storeId, String userId) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, ChannelType.MOBILE, DoorOpenType.OWNER_OPEN_DOOR);
        CtStore dbStore = storeMapper.selectCtStoreById(storeId);
        if (dbStore == null) {
            logFail(initLog, "", "无法获取门店信息");
            return false;
        }
        if (StringUtils.equals(dbStore.getIsSupervise(), "1")) {
            logFail(initLog, "", "当前门店在值守中，无法开门");
            return false;
        }
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        for (CtDevice doorGuard : doorGuardList) {
            String deviceSerial = doorGuard.getDeviceSerial();
            //解强锁
            ret = wiegandApiService.settingParams(deviceSerial, 0, 8);
            if (!ret.isSuccess()) {
                logFail(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }
            //远程开门
            ret = wiegandApiService.remoteOpenDoor(deviceSerial);
            if (!ret.isSuccess()) {
                logFail(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }

            //todo 延迟调用
//                ret = wiegandApiService.settingParams(doorGuard.getDeviceSerial(), 2, 0);
//                if (!ret.isSuccess()) {
//                    logFail(initLog, doorGuard.getId(), ret.getMsg());
//                    throw new ServiceException(ret.getMsg());
//                }
            logOk(initLog, doorGuard.getId());
        }
        return true;
    }

    /**
     * 强锁
     * 设置后，门禁将无法远程开门，和触发开门
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    @Override
    public boolean forceLock(String storeId, String userId, ChannelType channelType) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, DoorOpenType.FORCE_LOCK_DOOR);
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        for (CtDevice doorGuard : doorGuardList) {
            //强锁
            ret = wiegandApiService.settingParams(doorGuard.getDeviceSerial(), 2, 0);
            if (!ret.isSuccess()) {
                logError(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }
        }
        return true;
    }

    /**
     * 解强锁
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    @Override
    public boolean unlock(String storeId, String userId, ChannelType channelType) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, DoorOpenType.UNLOCK_DOOR);
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        for (CtDevice doorGuard : doorGuardList) {
            //强锁
            ret = wiegandApiService.settingParams(doorGuard.getDeviceSerial(), 0, 8);
            if (!ret.isSuccess()) {
                logError(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }
        }
        return false;
    }

    /**
     * 设置门禁密码
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    @Override
    public boolean setDoorAccess(String storeId, String userId, ChannelType channelType, boolean once, String beginTime, String endTime) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, DoorOpenType.SETTING_DOOR_ACCESS);
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        int password = Integer.parseInt(NumberUtils.genRandNum(6));
        for (CtDevice doorGuard : doorGuardList) {
            String serial = doorGuard.getDeviceSerial();
            //开启密码设置
            ret = wiegandApiService.enablePassword(serial, true);
            if (!ret.isSuccess()) {
                logError(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }
            //设置门禁密码
            ret = wiegandApiService.settingAccess(doorGuard.getDeviceSerial(), password, once, beginTime, endTime);
            if (!ret.isSuccess()) {
                logError(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }
            CtDeviceDoor dbDoor = deviceDoorMapper.selectCtDeviceDoorById(doorGuard.getId());
            if (dbDoor == null) {
                CtDeviceDoor insert = new CtDeviceDoor();
                insert.setId(doorGuard.getId());
                insert.setDoorNo(0L);
                insert.setAccessPassword(String.valueOf(password));
                insert.setDelFlag("0");
                insert.setCreateTime(new Date());
                insert.setUpdateTime(new Date());
                insert.setRemark("");
                if (deviceDoorMapper.insertCtDeviceDoor(insert) < 0) {
                    logError(initLog, doorGuard.getId(), "设置门禁密码失败");
                    return false;
                }
            } else {
                dbDoor.setAccessPassword(String.valueOf(password));
                dbDoor.setUpdateTime(new Date());
                if (deviceDoorMapper.updateCtDeviceDoor(dbDoor) < 0) {
                    logError(initLog, doorGuard.getId(), "设置门禁密码失败");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 取消门禁密码
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    @Override
    public boolean disableDoorAccess(String storeId, String userId, ChannelType channelType) {
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, DoorOpenType.SETTING_DOOR_ACCESS);
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDeviceDoor> doorGuardList = deviceDoorMapper.selectCtDeviceDoorListByStoreId(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, "", "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        for (CtDeviceDoor doorGuard : doorGuardList) {
            String serial = doorGuard.getDeviceSerial();
            int password = Integer.parseInt(doorGuard.getAccessPassword());
            //删除门禁密码
            ret = wiegandApiService.deleteAccess(serial, password);
            if (!ret.isSuccess()) {
                logError(initLog, doorGuard.getId(), ret.getMsg());
                return false;
            }
            CtDeviceDoor dbDoor = new CtDeviceDoor();
            dbDoor.setId(doorGuard.getId());
            dbDoor.setAccessPassword("");
            dbDoor.setUpdateTime(new Date());
            if (deviceDoorMapper.updateCtDeviceDoor(dbDoor) < 0) {
                logError(initLog, doorGuard.getId(), "设置门禁密码失败");
                return false;
            }
        }
        return true;
    }


    private String logOk(CtOpenDoorLogs log, String deviceId) {
        log.setDeviceId(deviceId);
        log.setState("0");
        log.setRemark("操作成功");
        return insertLog(log);
    }

    private String logFail(CtOpenDoorLogs log, String deviceId, String remark) {
        log.setDeviceId(deviceId);
        log.setState("1");
        log.setRemark(remark);
        return insertLog(log);
    }

    private String logError(CtOpenDoorLogs log, String deviceId, String remark) {
        log.setDeviceId(deviceId);
        log.setState("2");
        log.setRemark(remark);
        return insertLog(log);
    }

    private String insertLog(CtOpenDoorLogs log) {
        log.setDate(new Date());
        log.setCreateTime(new Date());
        log.setUpdateTime(new Date());
        openDoorLogsMapper.insertCtOpenDoorLogs(log);
        return "";
    }

    private CtOpenDoorLogs initInsertLog(String storeId, String userId, ChannelType channelType, DoorOpenType openType) {
        CtOpenDoorLogs log = new CtOpenDoorLogs();
        log.setStoreId(storeId);
        log.setMemberId(userId);
        log.setOptChannel(channelType.getCode());
        log.setOptType(openType.getCode());
        log.setDelFlag("0");
        return log;
    }
}
