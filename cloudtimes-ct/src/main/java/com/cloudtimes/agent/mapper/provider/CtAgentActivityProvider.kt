package com.cloudtimes.agent.mapper.provider

import com.cloudtimes.account.table.CtUserTable
import com.cloudtimes.agent.dto.request.ActivityListRequest
import com.cloudtimes.agent.dto.request.ActivityStoreRequest
import com.cloudtimes.agent.table.CtAgentActivityRuleStoreRelTable
import com.cloudtimes.agent.table.activityTable
import com.cloudtimes.agent.table.agentActivityRelTable
import com.cloudtimes.agent.table.agentTable
import com.cloudtimes.hardwaredevice.table.CtStoreTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtAgentActivityProvider {
    val storeTable = CtStoreTable().withAlias("st")
    val userTable = CtUserTable().withAlias("usr")
    val relTable = CtAgentActivityRuleStoreRelTable().withAlias("rel")

    fun selectAgentActivityListStmt(request: ActivityListRequest): SelectStatementProvider {
        return select(activityTable.allColumns()) {
            from(activityTable)
            where {
                activityTable.id isIn {
                    select(agentActivityRelTable.activityId) {
                        from(agentActivityRelTable)
                        where { agentActivityRelTable.userId isEqualTo request.userId!! }
                    }
                }
            }
            if ( !request.state.isNullOrEmpty()) {
                and { activityTable.state isEqualTo request.state!! }
            }
        }
    }

    fun selectByIdStmt(activityId: String): SelectStatementProvider {
        return select(activityTable.allColumns()) {
            from (activityTable)
            where {
                activityTable.id isEqualTo activityId
            }
        }
    }

    fun selectActivityStores(request: ActivityStoreRequest): SelectStatementProvider {
        return select(
            storeTable.id, storeTable.name, storeTable.address, storeTable.shortAddress,
            storeTable.buildState, storeTable.state, storeTable.regionCode,
            relTable.storeOnlineDate,
            userTable.nickName, userTable.realName, userTable.wxAvatar
        ) {
            from (relTable)
            where { relTable.activityRuleId isEqualTo request.activityRuleId!! }
            join(storeTable) { on(relTable.storeId) equalTo storeTable.id }
            join(userTable) { on(relTable.agentId) equalTo userTable.id }
            and {
               relTable.agentId isEqualTo request.userId!!
                or { relTable.parentAgentId isEqualTo request.userId!! }
            }
        }
    }
}