package com.cloudtimes.app.manager;

import com.alibaba.fastjson2.JSONObject;
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
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 视频摄像头状态检测管理器
 */

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = RocketMQConstants.CT_MONITOR_CAMARE_DEVICE, messageModel = MessageModel.BROADCASTING)
public class CtCameraDeviceMonitorManager implements RocketMQListener<CtDevice>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private ICtDeviceService deviceService;

    @Value("${monitor.camera}")
    private boolean enabled;

    @Value("${monitor.loadgaptime}")
    private long loadGapTime = 0;

    private long lastLoadTime = 0;

    @Autowired
    private ICtHikApiService ctHikApiService;

    @Autowired
    private RocketMQTemplate mqTemplate;

    private ConcurrentMap<String, CtDevice> ctCameraDevices = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        if (!enabled) {
            log.info("未开启摄像机设备监控");
            return;
        }
        this.loadData();
    }

    public void loadData() {
        long nowTime = System.currentTimeMillis();
        long msTime = lastLoadTime + loadGapTime * 60 * 1000L;
        if (nowTime - msTime > 0) {
            CtDevice query = new CtDevice();
            // 查询普通摄像头列表
            query.setDeviceType(DeviceType.CAMERA.getCode());
            List<CtDevice> devices = deviceService.selectCtDeviceList(query);
            // 查询连接nvr的poe摄像头列表
            query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
            devices.addAll(deviceService.selectCtDeviceList(query));
            for (CtDevice ctDevice : devices) {
                if (ctCameraDevices.containsKey(ctDevice.getDeviceSerial())) {
                    if (ctDevice.getState().equals(DeviceState.forbidden.getCode())) {
                        ctCameraDevices.remove(ctDevice.getDeviceSerial());
                    }
                }
                if (!ctDevice.getState().equals(DeviceState.forbidden.getCode())) {
                    if (!ctCameraDevices.containsKey(ctDevice.getDeviceSerial())) {
                        ctCameraDevices.put(ctDevice.getDeviceSerial(), ctDevice);
                        sendToMQ(ctDevice);
                    }
                }
            }
        }
        this.lastLoadTime = nowTime;
        log.info("当前监控中的摄头机设备数量：[" + ctCameraDevices.size() + "]");
    }

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
        sendToMQ(ctDevice);
    }

    public void sendToMQ(CtDevice ctDevice) {
        mqTemplate.syncSend(RocketMQConstants.CT_MONITOR_CAMARE_DEVICE, MessageBuilder.withPayload(ctDevice).build(), 10000, 6);
        log.info("摄像机状态检测:[DeviceSerial:" + ctDevice.getDeviceSerial() + " name:" + ctDevice.getName() + "] 发送到延时消息队列成功...");
    }

    @Override
    public void onMessage(CtDevice ctDevice) {
        if (!enabled) {
            return;
        }
        log.info("正在检测摄像机状态:[DeviceSerial:" + ctDevice.getDeviceSerial() + " name:" + ctDevice.getName() + "] ");
        this.checkCamera(ctDevice);
        this.loadData();
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName(this.getClass().getName());
    }
}
