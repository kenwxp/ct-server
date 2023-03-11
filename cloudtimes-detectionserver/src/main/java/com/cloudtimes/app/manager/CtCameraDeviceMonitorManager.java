package com.cloudtimes.app.manager;

import com.cloudtimes.app.domain.DetectionData;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.DeviceState;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.cloudtimes.common.constant.CacheConstants.CAMERQ_DEVICE_MONITOR;

/**
 * 视频摄像头状态检测管理器
 */

@Slf4j
@Component
public class CtCameraDeviceMonitorManager {

    @Autowired
    private ICtDeviceService deviceService;

    @Value("${monitor.camera}")
    private boolean enabled;
    @Value("${monitor.loadgaptime}")
    private long loadGapTime;

    private long lastLoadDataTime;

    @Autowired
    private CtRocketMqProducer mqProducer;

    //读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wLock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();

    @Autowired
    private RedisCache redisCache;

    @PostConstruct
    public void init() {
        if (!enabled) {
            log.info("未开启摄像机设备监控");
            return;
        }
        this.loadData();
    }

    public String getCacheKey(CtDevice ctDevice) {
        return CAMERQ_DEVICE_MONITOR + ctDevice.getDeviceSerial();
    }

    public int getDeviceNumber() {
        return redisCache.keys(CAMERQ_DEVICE_MONITOR + ":*").size();
    }

    public boolean isLoadDataTime() {
        long now = DateUtils.getNowDate().getTime();
        long nextLoadTime = lastLoadDataTime + loadGapTime * 60L * 1000L;
        if (now >= nextLoadTime) {
            lastLoadDataTime = now;
            return true;
        } else {
            return false;
        }
    }

    public void loadData() {
        wLock.lock();
        try {
            if (!this.isLoadDataTime()) {
                return;
            }
            log.info("当前监控中的摄头机设备数量：[" + getDeviceNumber() + "]");
            CtDevice query = new CtDevice();
            // 查询普通摄像头列表
            query.setDeviceType(DeviceType.CAMERA.getCode());
            List<CtDevice> devices = deviceService.selectCtDeviceList(query);
            // 查询连接nvr的poe摄像头列表
            query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
            devices.addAll(deviceService.selectCtDeviceList(query));
            for (CtDevice ctDevice : devices) {
                String cacheKey = getCacheKey(ctDevice);
                if (redisCache.hasKey(cacheKey)) {
                    if (ctDevice.getState().equals(DeviceState.Forbidden.getCode())) {
                        redisCache.deleteObject(cacheKey);
                    }
                }
                if (!ctDevice.getState().equals(DeviceState.Forbidden.getCode())) {
                    if (!redisCache.hasKey(cacheKey)) {
                        redisCache.setCacheObject(cacheKey, ctDevice);
                        DetectionData detectionData = new DetectionData();
                        detectionData.setOption(1);
                        detectionData.setDevice(ctDevice);
                        sendToMQ(detectionData, 6);//2min延迟发送
                    }
                }
            }
            DetectionData detectionData = new DetectionData();
            detectionData.setOption(0);
            sendToMQ(detectionData, 4);// 30s检查新设备

        } finally {
            wLock.unlock();
        }
    }

    public void sendToMQ(DetectionData detectionData, int delayLevel) {
        mqProducer.sendDelayMsg(RocketMQConstants.CT_MONITOR_CAMERA_DEVICE, JacksonUtils.toString(detectionData), delayLevel);
    }

}
