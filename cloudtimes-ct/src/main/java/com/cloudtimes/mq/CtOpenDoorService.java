package com.cloudtimes.mq;

import com.alibaba.fastjson.JSON;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.partner.wiegand.ICtWiegandApiService;
import com.cloudtimes.partner.wiegand.domain.WiegandReturning;
import com.cloudtimes.supervise.domain.CtTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CtOpenDoorService {
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
    @Autowired
    private CtRocketMqProducer producer;
    @Autowired
    private CtTaskCache taskCache;

    /**
     * 交易开门
     * 小程序，刷脸，支付订单成功后调用
     */
    public boolean transOpen(OpenDoorMqData param) {
        String storeId = param.getStoreId();
        String userId = param.getUserId();
        ChannelType channelType = param.getChannelType();
        log.info("准备交易开门，门店编号:{}，操作者：{}", storeId, userId);
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, OpenDoorOption.TRANS_OPEN_DOOR);
        CtStore dbStore = storeMapper.selectCtStoreById(storeId);
        if (dbStore == null) {
            logFail(initLog, "", "无法获取门店信息");
            return false;
        }
        // 校验门店逻辑锁
        Map<String, CtTask> tasksOfStore = taskCache.getAllTasksOfStore(storeId);
        for (CtTask task :
                tasksOfStore.values()) {
            if (task.isOpenLock()) {
                logFail(initLog, "", "任务已加锁，禁止开门");
                return false;
            }
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
        // 延迟调用 锁门
        if (StringUtils.equals(dbStore.getIsSupervise(), "0")) {
            OpenDoorMqData mqData = new OpenDoorMqData(OpenDoorOption.FORCE_LOCK_DOOR, storeId, userId, ChannelType.WEB);
            producer.sendDelayMsg(RocketMQConstants.CT_OPEN_DOOR, JSON.toJSONString(mqData), 3);
        }
        return true;
    }

    /**
     * 应急开门
     * 管理端开门
     *
     * @return
     */
    public boolean emergentOpen(OpenDoorMqData param) {
        String storeId = param.getStoreId();
        String userId = param.getUserId();
        ChannelType channelType = ChannelType.WEB;
        log.info("准备应急开门，门店编号:{}，操作员：{}", storeId, userId);
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, OpenDoorOption.EMERGENCY_OPEN_DOOR);
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
        // 延迟调用 锁门
        if (StringUtils.equals(dbStore.getIsSupervise(), "0")) {
            OpenDoorMqData mqData = new OpenDoorMqData(OpenDoorOption.FORCE_LOCK_DOOR, storeId, userId, channelType);
            producer.sendDelayMsg(RocketMQConstants.CT_OPEN_DOOR, JSON.toJSONString(mqData), 3);
        }
        return true;
    }

    /**
     * 店家开门
     * 店家app端
     *
     * @return
     */
    public boolean ownerOpen(OpenDoorMqData param) {
        String storeId = param.getStoreId();
        String userId = param.getUserId();
        ChannelType channelType = ChannelType.MOBILE;
        log.info("准备店家开门，门店编号:{}，店家编号：{}", storeId, userId);
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, OpenDoorOption.OWNER_OPEN_DOOR);
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
            logOk(initLog, doorGuard.getId());
        }
        // 延迟调用 锁门
        OpenDoorMqData mqData = new OpenDoorMqData(OpenDoorOption.FORCE_LOCK_DOOR, storeId, userId, channelType);
        producer.sendDelayMsg(RocketMQConstants.CT_OPEN_DOOR, JSON.toJSONString(mqData), 3);
        return true;
    }

    /**
     * 强锁
     * 设置后，门禁将无法远程开门，和触发开门
     *
     * @return
     */
    public boolean forceLock(OpenDoorMqData param) {
        String storeId = param.getStoreId();
        String userId = param.getUserId();
        ChannelType channelType = param.getChannelType();
        log.info("开启强锁模式，门店编号:{}", storeId);
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, OpenDoorOption.FORCE_LOCK_DOOR);
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
     * @return
     */
    public boolean unlock(OpenDoorMqData param) {
        String storeId = param.getStoreId();
        String userId = param.getUserId();
        ChannelType channelType = param.getChannelType();
        log.info("关闭强锁模式，门店编号:{}", storeId);
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, OpenDoorOption.UNLOCK_DOOR);
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
     * @return
     */
    public boolean setDoorAccess(OpenDoorMqData param) {
        String storeId = param.getStoreId();
        String deviceId = param.getDeviceId();
        String userId = param.getUserId();
        ChannelType channelType = param.getChannelType();
        boolean once = param.isOnce();
        String beginTime = param.getBeginTime();
        String endTime = param.getEndTime();
        boolean reset = param.isReset();
        log.info("设置门禁密码，门店编号:{}", storeId);
        CtOpenDoorLogs initLog = initInsertLog(storeId, userId, channelType, OpenDoorOption.SETTING_DOOR_ACCESS);
        CtDevice queryDevice = new CtDevice();
        queryDevice.setStoreId(storeId);
        queryDevice.setDeviceType(DeviceType.DOOR_GUARD.getCode());
        queryDevice.setDelFlag("0");
        List<CtDevice> doorGuardList = deviceMapper.selectCtDeviceList(queryDevice);
        if (StringUtils.isEmpty(doorGuardList)) {
            logFail(initLog, deviceId, "无法获取门禁设备");
            return false;
        }
        WiegandReturning ret;
        int password = Integer.parseInt(NumberUtils.genRandNum(6));
        for (CtDevice doorGuard : doorGuardList) {
            if (StringUtils.isEmpty(deviceId) || StringUtils.equals(deviceId, doorGuard.getId())) {
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
                    insert.setRemark("新增密码成功");
                    if (deviceDoorMapper.insertCtDeviceDoor(insert) < 0) {
                        logError(initLog, doorGuard.getId(), "设置门禁密码失败");
                        return false;
                    }
                } else {
                    //删除门禁密码
                    if (reset) {
                        ret = wiegandApiService.deleteAccess(serial, Integer.parseInt(dbDoor.getAccessPassword()));
                        if (!ret.isSuccess()) {
                            logError(initLog, doorGuard.getId(), ret.getMsg());
                            return false;
                        }
                    }
                    dbDoor.setAccessPassword(String.valueOf(password));
                    dbDoor.setUpdateTime(new Date());
                    dbDoor.setRemark("更换密码成功");
                    if (deviceDoorMapper.updateCtDeviceDoor(dbDoor) < 0) {
                        logError(initLog, doorGuard.getId(), "设置门禁密码失败");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private String logOk(CtOpenDoorLogs log, String deviceId) {
        if (StringUtils.isNotEmpty(deviceId)) {
            log.setDeviceId(deviceId);
        }
        log.setState("0");
        log.setRemark("操作成功");
        return insertLog(log);
    }

    private String logFail(CtOpenDoorLogs log, String deviceId, String remark) {
        if (StringUtils.isNotEmpty(deviceId)) {
            log.setDeviceId(deviceId);
        }
        log.setState("1");
        log.setRemark(remark);
        return insertLog(log);
    }

    private String logError(CtOpenDoorLogs log, String deviceId, String remark) {
        if (StringUtils.isNotEmpty(deviceId)) {
            log.setDeviceId(deviceId);
        }
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

    private CtOpenDoorLogs initInsertLog(String storeId, String userId, ChannelType channelType, OpenDoorOption openType) {
        CtOpenDoorLogs log = new CtOpenDoorLogs();
        if (StringUtils.isNotEmpty(storeId)) {
            log.setStoreId(storeId);
        }
        if (StringUtils.isNotEmpty(userId)) {
            log.setMemberId(userId);
        }
        log.setOptChannel(channelType.getCode());
        log.setOptType(openType.getCode());
        log.setDelFlag("0");
        return log;
    }
}
