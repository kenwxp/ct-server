package com.cloudtimes.common.mq;

import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class OpenDoorMqData {
    private OpenDoorOption option; // 操作类型
    private String storeId; //门店编号
    private String deviceId; //设备编号，为空时，则操作门店下所有设备
    private String userId;  //操作人
    private ChannelType channelType; // 操作渠道
    private boolean once = false;   // 是否一次性，操作类型未设置密码时选填
    private String beginTime; // 生效时间 操作类型未设置密码时必填
    private String endTime;// 失效时间 操作类型未设置密码时必填
    private boolean reset = true;  // 是否取消旧密码，操作类型未设置密码时选填
    private Date sendTime;

    public OpenDoorMqData() {
        this.sendTime = DateUtils.getNowDate();
    }

    public OpenDoorMqData(OpenDoorOption option) {
        this.option = option;
    }

    public OpenDoorMqData(OpenDoorOption option, String storeId, String userId, ChannelType channelType) {
        this.option = option;
        this.storeId = storeId;
        this.userId = userId;
        this.channelType = channelType;
        this.sendTime = DateUtils.getNowDate();
    }

    public OpenDoorMqData(OpenDoorOption option, String storeId, String deviceId, String userId, ChannelType channelType) {
        this.option = option;
        this.storeId = storeId;
        this.deviceId = deviceId;
        this.userId = userId;
        this.channelType = channelType;
        this.sendTime = DateUtils.getNowDate();
    }
}
