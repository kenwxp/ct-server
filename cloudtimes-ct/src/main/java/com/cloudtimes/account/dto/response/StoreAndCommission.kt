package com.cloudtimes.account.dto.response

import com.cloudtimes.account.domain.CtAgentCommissionSettlement
import com.cloudtimes.hardwaredevice.domain.CtStore

class StoreAndCommission {
    var store: CtStore? = null;

    var commission: CtAgentCommissionSettlement? = null;
}