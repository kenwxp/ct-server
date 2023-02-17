package com.cloudtimes.agent.mapper.provider

import com.cloudtimes.agent.dto.request.QueryActivityRequest
import com.cloudtimes.agent.table.activityTable
import com.cloudtimes.agent.table.agentActivityRelTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert

object CtAgentActivityProvider {
    fun selectAgentActivityListStmt(request: QueryActivityRequest): SelectStatementProvider {
        return select(activityTable.allColumns()) {
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
}