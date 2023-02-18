package com.cloudtimes.agent.mapper.provider

import com.cloudtimes.agent.dto.request.ActivityListRequest
import com.cloudtimes.agent.table.activityTable
import com.cloudtimes.agent.table.agentActivityRelTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtAgentActivityProvider {
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
            and { activityTable.state isEqualToWhenPresent request.state }
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
}