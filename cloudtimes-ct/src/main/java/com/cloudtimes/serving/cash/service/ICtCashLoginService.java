package com.cloudtimes.serving.cash.service;

import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.serving.cash.service.domain.CashLoginCheckResp;
import com.cloudtimes.serving.cash.service.domain.CashLoginReq;
import com.cloudtimes.serving.cash.service.domain.CashLoginResp;

public interface ICtCashLoginService {

    public CashLoginCheckResp checkCashNew(CashLoginReq param);

    public CashLoginResp cashLogin(CashLoginReq param);
}
