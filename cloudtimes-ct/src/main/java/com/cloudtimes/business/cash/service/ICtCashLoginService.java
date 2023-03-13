package com.cloudtimes.business.cash.service;

import com.cloudtimes.business.cash.service.domain.CashLoginCheckResp;
import com.cloudtimes.business.cash.service.domain.CashLoginReq;
import com.cloudtimes.business.cash.service.domain.CashLoginResp;

public interface ICtCashLoginService {

    public CashLoginCheckResp checkCashNew(CashLoginReq param);

    public CashLoginResp cashLogin(CashLoginReq param);
}
