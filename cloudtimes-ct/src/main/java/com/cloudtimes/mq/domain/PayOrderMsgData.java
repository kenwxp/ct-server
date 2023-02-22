package com.cloudtimes.mq.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PayOrderMsgData {
    private String terminalSN; //收钱吧终端ID
    private String terminalKey; //收钱吧终端Key
    private String paySn; //收钱吧系统订单号	必须在商户系统内唯一；且长度不超过32字节
    private String orderId; //商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
    private Date createTime; //创建时间                                 `
    private boolean cancelFlag; //是否撤单 0-否 1-是
}
