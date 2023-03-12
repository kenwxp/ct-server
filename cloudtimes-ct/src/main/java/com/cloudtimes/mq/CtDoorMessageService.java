package com.cloudtimes.mq;

import com.cloudtimes.cache.CtDoorStateCache;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.redislock.RedissonLock;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.serving.common.CtTaskDistributionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CtDoorMessageService {
    @Autowired
    private CtDoorStateCache doorStateCache;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtOpenDoorLogsMapper doorLogsMapper;
    @Autowired
    private CtTaskDistributionService taskInnerService;
    @Autowired
    private RedissonLock redissonLock;

    public void handleStateMessage(int deviceSerial, String updateTime) {
        // 处理门禁状态
        doorStateCache.put(deviceSerial, DateUtils.parseDateTime(updateTime));
    }

    public void handleTriggerMessage(int deviceSerial, String updateTime) {
        log.info("处理红外开门报文：门禁序列号：{}", deviceSerial);
        // 处理触发开门逻辑状态
        CtDevice ctDevice = deviceMapper.selectCtDeviceByDeviceSerial(String.valueOf(deviceSerial));
        if (ctDevice == null) {
            log.error("无法获取门禁设备");
            return;
        }
        if (StringUtils.isEmpty(ctDevice.getStoreId())) {
            log.error("门禁设备未绑定门店");
            return;
        }
        // 新增开门日志
        CtOpenDoorLogs ctOpenDoorLogs = new CtOpenDoorLogs();
        ctOpenDoorLogs.setStoreId(ctDevice.getStoreId());
        ctOpenDoorLogs.setDeviceId(ctDevice.getId());
        ctOpenDoorLogs.setMemberId(ctDevice.getId());//线下场景则记录设备号
        ctOpenDoorLogs.setOptChannel(ChannelType.OFFLINE.getCode());//线下渠道
        ctOpenDoorLogs.setOptType(OpenDoorOption.TRIGGER_OPEN_DOOR.getCode());//触发开门
        ctOpenDoorLogs.setState("0");
        ctOpenDoorLogs.setDelFlag("0");
        ctOpenDoorLogs.setDate(new Date());
        ctOpenDoorLogs.setCreateTime(DateUtils.parseDateTime(updateTime));
        ctOpenDoorLogs.setUpdateTime(new Date());
        ctOpenDoorLogs.setRemark("红外开门");
        if (doorLogsMapper.insertCtOpenDoorLogs(ctOpenDoorLogs) < 1) {
            log.error("新增开门日志失败");
            return;
        }
        String storeId = ctDevice.getStoreId();
        // 任务调度
        taskInnerService.distributeTask(storeId, ctOpenDoorLogs.getId());
    }
}
