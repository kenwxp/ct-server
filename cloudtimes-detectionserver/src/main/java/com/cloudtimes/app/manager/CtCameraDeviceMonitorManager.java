package com.cloudtimes.app.manager;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import com.cloudtimes.partner.hik.domain.DeviceInfoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
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
public class CtCameraDeviceMonitorManager implements RocketMQListener<CtDevice> {

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
            query.setDeviceType("0");
            List<CtDevice> devices = deviceService.selectCtDeviceList(query);
            for (CtDevice ctDevice : devices) {
                if (ctCameraDevices.containsKey(ctDevice.getDeviceSerial())) {
                    if (ctDevice.getState().equals("4")) {
                        ctCameraDevices.remove(ctDevice.getDeviceSerial());
                    }
                }
                if (!ctDevice.getState().equals("4")) {
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

    public void checkCamare(CtDevice ctDevice) {

        try {
            DeviceInfoData deviceInfoData = ctHikApiService.getDeviceInfo(ctDevice.getDeviceSerial());

            log.info("摄像机[DeviceSerial:" + ctDevice.getDeviceSerial() + " name: " + ctDevice.getName() + "],状态检测三方返回结果:[" + JSONObject.toJSONString(deviceInfoData) + "]");
            ctDevice.setState(String.valueOf(deviceInfoData.getStatus()));

            CtDevice update = new CtDevice();
            update.setId(ctDevice.getId());
            update.setState(ctDevice.getState());
            ctDevice.setName(deviceInfoData.getDeviceName());
            deviceService.updateCtDevice(ctDevice);
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
        this.checkCamare(ctDevice);
        this.loadData();
    }
}
