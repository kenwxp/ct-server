package com.cloudtimes.mq.service;

import com.alibaba.fastjson.JSON;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.CallDoData;
import com.cloudtimes.common.mq.CashMqData;
import com.cloudtimes.common.mq.SendOrderData;
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
        log.info("通知收银机切换值守状态: 门店id=" + storeId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(RocketMQConstants.SEND_DUTY_STATUS);
            DutyStatusData data = new DutyStatusData();
            data.setIsSupervise(isSupervise);
            cashMqData.setData(data);
            log.info("发送mq信息：" + cashMqData.toString());
            mqTemplate.convertAndSend(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    public void notifyCashDoCall(String storeId, String isSupervise) {
        log.info("通知收银机加入/离开频道: 门店id=" + storeId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(RocketMQConstants.SEND_CALL_DO);
            CallDoData data = new CallDoData();
            data.setDoJoin(isSupervise);
            cashMqData.setData(data);
            log.info("发送mq信息：" + cashMqData);
            mqTemplate.convertAndSend(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    public void sendBillSerial(String storeId, String orderId, String dynamicQrCode) {
        log.info("推送收银机单号: 门店id=" + storeId + " 单号：" + orderId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(RocketMQConstants.SEND_BILL_SERIAL);
            SendOrderData data = new SendOrderData();
            data.setOrderId(orderId);
            data.setDynamicQrCode(dynamicQrCode);
            cashMqData.setData(data);
            log.info("发送mq信息：" + cashMqData);
            mqTemplate.convertAndSend(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    public void sendSyncProduct(String storeId) {
        log.info("同步商品列表: 门店id=" + storeId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(RocketMQConstants.SEND_SYNC_PRODUCT);
            log.info("发送mq信息：" + cashMqData);
            mqTemplate.convertAndSend(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    private List<CtDevice> getCashDevicesOfShop(String storeId) {
        CtDevice query = new CtDevice();
        query.setStoreId(storeId);
        query.setDeviceType(DeviceType.CASH.getCode());
        query.setDelFlag("0");
        List<CtDevice> ctDevices = deviceMapper.selectCtDeviceList(query);
        return ctDevices;
    }

}
