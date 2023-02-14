package com.cloudtimes.account.service.impl;

import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.dto.response.AgentActivity
import com.cloudtimes.account.mapper.CtAgentActivity1Mapper
import com.cloudtimes.account.mapper.CtAgentActivity2Mapper
import com.cloudtimes.account.mapper.provider.CtAgentActivity1Provider
import com.cloudtimes.account.mapper.provider.CtAgentActivity2Provider
import com.cloudtimes.account.service.ICtAgentActivityService
import org.springframework.beans.factory.annotation.Autowired

/**
 * 提现登记薄Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class ICtAgentActivityServiceImpl : ICtAgentActivityService
{
    @Autowired
    private lateinit var activity1Mapper: CtAgentActivity1Mapper

    @Autowired
    private lateinit var activity2Mapper: CtAgentActivity2Mapper

    override fun selectAgentActivity(userID: String): AgentActivity? {
        val agentActivity = AgentActivity();
        // Step 1. 查询activity1
        val activity1List = activity1Mapper.selectMany(
            CtAgentActivity1Provider.selectActivitiesByUserId(userID)
        )

        // Step 2. 查询activity2
        val activity2List = activity2Mapper.selectMany(
            CtAgentActivity2Provider.selectActivitiesByUserId(userID)
        )

        // Step 3. 返回查询结果
        return AgentActivity(activity1List, activity2List)
    }
}
