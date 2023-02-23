package com.cloudtimes.mq.listener;

import cn.hutool.core.date.DateUtil;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OperatorType;
import com.cloudtimes.common.mq.DoorStateData;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DoorOpType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.serving.common.CtTaskInnerService;
import com.cloudtimes.supervise.domain.CtTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 门禁触发开门报文处理
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.DOOR_TRIGGER_MESSAGE, messageModel = MessageModel.CLUSTERING)
public class DoorTriggerMessageHandler implements RocketMQListener<DoorStateData>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtOpenDoorLogsMapper doorLogsMapper;
    @Autowired
    private CtTaskInnerService taskInnerService;

    @Override
    public void onMessage(DoorStateData data) {
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
        ctOpenDoorLogs.setOptType(DoorOpType.TRIGGER_OPEN_DOOR.getCode());//触发开门
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

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(this.getClass().getName());
    }
}
