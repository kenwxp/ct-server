package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtAgentActivity
import com.cloudtimes.account.dto.response.AgentActivity
import org.bouncycastle.bcpg.sig.PrimaryUserID

/**
 * 代理活动Service接口
 *
 * @author 沈兵
 * @date 2023-02-02
 */
interface ICtAgentActivityService {
    fun selectAgentActivity(userID: String): AgentActivity?
}
