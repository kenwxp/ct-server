package com.cloudtimes.account.dto.response

import com.cloudtimes.account.domain.CtAgentActivity1
import com.cloudtimes.account.domain.CtAgentActivity2

data class AgentActivity (
    var activity1List: List<CtAgentActivity1> = emptyList(),
    var activity2List: List<CtAgentActivity2> = emptyList(),
)