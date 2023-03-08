package com.cloudtimes.partner.wiegand.domain;

import lombok.Data;

@Data
public class CommonResp {
    private String jsonrpc;
    private int id;
    private WiegandReturning result;
}
