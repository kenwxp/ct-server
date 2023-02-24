package com.cloudtimes.mq.service;

import cn.hutool.core.date.DateUtil;
import com.cloudtimes.cache.CtDoorStateCache;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.mq.DoorMessageMqData;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.serving.common.CtTaskInnerService;
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
    private CtTaskInnerService taskInnerService;

    public void handleStateMessage(DoorMessageMqData data) {
        // 处理门禁状态
        log.info("更新门禁设备刷新时间：{}====>{}", data.getDeviceSerial(), data.getUpdateTime());
        doorStateCache.put(data.getDeviceSerial(), DateUtil.parseDateTime(data.getUpdateTime()));
    }

    public void handleTriggerMessage(DoorMessageMqData data) {
        // 处理触发开门逻辑状态
        log.info("触发开门：{}====>{}", data.getDeviceSerial(), data.getUpdateTime());
        CtDevice ctDevice = deviceMapper.selectCtDeviceByDeviceSerial(String.valueOf(data.getDeviceSerial()));
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
//        ctOpenDoorLogs.setMemberId();
        ctOpenDoorLogs.setOptChannel(ChannelType.OFFLINE.getCode());//线下渠道
        ctOpenDoorLogs.setOptType(OpenDoorOption.TRIGGER_OPEN_DOOR.getCode());//触发开门
        ctOpenDoorLogs.setState("0");
        ctOpenDoorLogs.setDelFlag("0");
        ctOpenDoorLogs.setDate(new Date());
        ctOpenDoorLogs.setCreateTime(DateUtil.parseTime(data.getUpdateTime()));
        ctOpenDoorLogs.setUpdateTime(new Date());
        ctOpenDoorLogs.setRemark("红外开门");
        if (doorLogsMapper.insertCtOpenDoorLogs(ctOpenDoorLogs) < 1) {
            log.error("新增开门日志失败");
            return;
        }
        // 任务调度
        taskInnerService.distributeTask(ctDevice.getStoreId(), ctOpenDoorLogs.getId());
    }
}
