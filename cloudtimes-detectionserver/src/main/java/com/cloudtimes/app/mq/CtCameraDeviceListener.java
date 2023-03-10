package com.cloudtimes.app.mq;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.app.domain.DetectionData;
import com.cloudtimes.app.manager.CtCameraDeviceMonitorManager;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.DeviceState;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import com.cloudtimes.partner.hik.domain.DeviceInfoData;
import com.cloudtimes.partner.hik.domain.NvrChannelStatus;
import com.cloudtimes.partner.hik.domain.NvrDeviceInfoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 视频摄像头状态检测管理器
 */

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "CtCameraDeviceListener", topic = "${mq_topic.env}" + RocketMQConstants.CT_MONITOR_CAMERA_DEVICE, messageModel = MessageModel.BROADCASTING)
public class CtCameraDeviceListener implements RocketMQListener<DetectionData>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private ICtDeviceService deviceService;

    @Value("${monitor.camera}")
    private boolean enabled;

    @Autowired
    private ICtHikApiService ctHikApiService;

    @Autowired
    private CtCameraDeviceMonitorManager monitorManager;

    public void checkCamera(CtDevice ctDevice) {
        try {
            if (StringUtils.equals(ctDevice.getDeviceType(), DeviceType.CAMERA.getCode())) {
                // IPC摄像头
                DeviceInfoData deviceInfoData = ctHikApiService.getDeviceInfo(ctDevice.getDeviceSerial());
                log.info("摄像机[DeviceSerial:" + ctDevice.getDeviceSerial() + " name: " + ctDevice.getName() + "],状态检测三方返回结果:[" + JSONObject.toJSONString(deviceInfoData) + "]");
                ctDevice.setState(String.valueOf(deviceInfoData.getStatus()));
                CtDevice update = new CtDevice();
                update.setId(ctDevice.getId());
                if (deviceInfoData.getStatus() == 1) {
                    update.setState(DeviceState.Online.getCode());
                } else {
                    update.setState(DeviceState.Offline.getCode());
                }
                ctDevice.setName(deviceInfoData.getDeviceName());
                deviceService.updateCtDevice(ctDevice);
            } else if (StringUtils.equals(ctDevice.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
                // POE摄像头，实际查询nvr状态
                NvrDeviceInfoData nvrChannelStatus = ctHikApiService.getNvrChannelStatus(ctDevice.getDeviceSerial());
                List<NvrChannelStatus> channelInfoList = nvrChannelStatus.getChannelInfoList();
                CtDevice query = new CtDevice();
                query.setDeviceSerial(ctDevice.getDeviceSerial());
                query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
                List<CtDevice> poeDeviceList = deviceService.selectCtDeviceList(query);
                for (CtDevice device :
                        poeDeviceList) {
                    for (NvrChannelStatus channel :
                            channelInfoList) {
                        if (channel.getSuperDevChannel() == device.getDeviceChannel()) {
                            CtDevice update = new CtDevice();
                            update.setId(ctDevice.getId());
                            if (channel.getStatus() == 0) {
                                update.setState(DeviceState.Offline.getCode());
                            } else if (channel.getStatus() == 0) {
                                update.setState(DeviceState.Online.getCode());
                            } else {
                                update.setState(DeviceState.Error.getCode());
                            }
                            deviceService.updateCtDevice(ctDevice);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("摄像机设备状态更新失败！", e);
        }
        DetectionData detectionData = new DetectionData();
        detectionData.setOption(1);
        detectionData.setDevice(ctDevice);
        monitorManager.sendToMQ(detectionData, 6);//2min一次设备检查
    }

    @Override
    public void onMessage(DetectionData data) {
        if (!enabled) {
            return;
        }
        if (data.getOption() == 0) {
            // 获取新设备
            monitorManager.loadData();
        } else {
            log.info("正在检测摄像机状态:[DeviceSerial:" + data.getDevice().getDeviceSerial() + " name:" + data.getDevice().getName() + "] ");
            this.checkCamera(data.getDevice());
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName(this.getClass().getName());
    }
}