package com.cloudtimes.app.process.cash;

import com.cloudtimes.app.process.BaseEventProcess;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.DeviceState;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 开门事件处理器
 */
@Slf4j
@Component("HEART-REPORT")
public class HeartReportEventProcess implements BaseEventProcess {
    @Autowired
    private CtDeviceMapper deviceMapper;

    @Override
    public String eventName() {
        return "收银机心跳状态处理";
    }

    @Override
    public String process(AuthUser authUser, Object object) {
        log.info("收银机心跳包：设备编号：" + authUser.getId());
        // 更新收银机状态
        CtDevice device = new CtDevice();
        device.setId(authUser.getId());
        device.setState(DeviceState.Online.getCode());
        device.setUpdateTime(DateUtils.getNowDate());
        deviceMapper.updateCtDevice(device);
        return null;
    }
}
