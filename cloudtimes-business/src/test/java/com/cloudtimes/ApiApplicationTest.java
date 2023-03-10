package com.cloudtimes;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.mq.DoorMessageMqData;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.hardwaredevice.domain.DeviceActivateReq;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import com.cloudtimes.mq.service.CtDoorMessageService;
import com.cloudtimes.partner.hik.domain.NvrDeviceInfoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ApiApplicationTest {
    @Autowired
    RocketMQTemplate rocketMQTemplate;
    @Autowired
    JWTManager jwtManager;
    @Autowired
    ICtHikApiService hikApiService;
    @Autowired
    ICtDeviceService deviceService;

    @Test
    public void testJSON() {
        AuthUser authUser = new AuthUser();
        authUser.setId("1315161");
        authUser.setChannelType(ChannelType.WEB);
        String s = JSONObject.toJSONString(authUser);
        System.out.println(s);
        AuthUser authUser1 = JSONObject.parseObject(s, AuthUser.class);
        System.out.println(authUser1);
    }

    @Autowired
    CtDoorMessageService doorMessageService;

    @Test
    public void testTrigger() {
        doorMessageService.handleTriggerMessage(new DoorMessageMqData(1, 153204320, "2023-02-24 12:01:11"));
    }

    @Test
    public void testNvrStatus() {
        NvrDeviceInfoData data = hikApiService.getNvrChannelStatus("K93279101");
        log.info(data.toString());
    }
    @Test
    public void testActivateCtDevice() {
        DeviceActivateReq query = new DeviceActivateReq();
        query.setDeviceId("e5b00b8d-b0c8-11ed-8957-0242ac110003");
        query.setCode("24029562");
        deviceService.activateCtDevice(query);
    }
}