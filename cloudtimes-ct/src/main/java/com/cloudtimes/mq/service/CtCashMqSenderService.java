package com.cloudtimes.mq.service;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.*;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CtCashMqSenderService {
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtRocketMqProducer mqProducer;
    /**
     * 指令选项
     */

    public final String SEND_DUTY_STATUS = "DUTY-STATUS";// 收银机值守状态
    public final String SEND_CALL_DO = "CALL-DO"; // 加入频道
    public final String SEND_BILL_SERIAL = "BILL-SERIAL"; // 推送单号
    public final String SEND_SYNC_PRODUCT = "SYNC-PRODUCT";// 同步商品列表

    public void notifyCashDutyStatus(String storeId, String isSupervise) {
        log.info("通知收银机切换值守状态: 门店id=" + storeId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(SEND_DUTY_STATUS);
            DutyStatusData data = new DutyStatusData();
            data.setIsSupervise(isSupervise);
            cashMqData.setData(data);
            log.info("发送mq信息：" + cashMqData.toString());
            mqProducer.send(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    public void notifyCashDoCall(String storeId, String isJoin) {
        log.info("通知收银机加入/离开频道: 门店id=" + storeId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(SEND_CALL_DO);
            CallDoData data = new CallDoData();
            data.setDoJoin(isJoin);
            cashMqData.setData(data);
            log.info("发送mq信息：" + cashMqData);
            mqProducer.send(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    public void sendBillSerial(String storeId, String orderId, String dynamicQrCode, String phone) {
        log.info("推送收银机单号: 门店id=" + storeId + " 单号：" + orderId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(SEND_BILL_SERIAL);
            SendOrderData data = new SendOrderData();
            data.setOrderId(orderId);
            data.setDynamicQrCode(dynamicQrCode);
            data.setCustomerPhone(NumberUtils.getHiddenPhone(phone));
            cashMqData.setData(data);
            log.info("发送mq信息：" + cashMqData);
            mqProducer.send(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
        }
    }

    public void sendSyncProduct(String storeId) {
        log.info("同步商品列表: 门店id=" + storeId);
        List<CtDevice> ctDevices = getCashDevicesOfShop(storeId);
        for (CtDevice device :
                ctDevices) {
            CashMqData cashMqData = new CashMqData();
            cashMqData.setDeviceId(device.getId());
            cashMqData.setOption(SEND_SYNC_PRODUCT);
            log.info("发送mq信息：" + cashMqData);
            mqProducer.send(RocketMQConstants.WS_CASH_DEVICE, cashMqData);
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
