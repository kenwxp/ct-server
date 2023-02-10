package com.cloudtimes.mq.service;

import com.alibaba.fastjson.JSON;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.CashMqData;
import com.cloudtimes.common.utils.HolidayUtil;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.common.mq.DutyStatusData;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashMqSender {
    static Logger log = LoggerFactory.getLogger(HolidayUtil.class);
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private RocketMQTemplate mqTemplate;

    public void notifyCashDutyStatus(String storeId, String isSupervise) {
        log.info("notifyCashDutyStatus: storeId=" + storeId);
        CtDevice query = new CtDevice();
        query.setStoreId(storeId);
        query.setDeviceType(DeviceType.CASH.getCode());
        query.setDelFlag("0");
        List<CtDevice> ctDevices = deviceMapper.selectCtDeviceList(query);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(RocketMQConstants.SEND_DUTY_STATUS);
            DutyStatusData dutyStatusData = new DutyStatusData();
            dutyStatusData.setIsSupervise(isSupervise);
            cashMqData.setData(dutyStatusData);

            mqTemplate.convertAndSend(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }
}
