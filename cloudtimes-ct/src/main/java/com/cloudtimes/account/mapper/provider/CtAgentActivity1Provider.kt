package com.cloudtimes.account.mapper.provider


import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update

import com.cloudtimes.account.table.activity1Table
import com.cloudtimes.account.table.agentActivityTable
import com.cloudtimes.common.enums.ActivityType

object CtAgentActivity1Provider {
    /**
        select *
        from ct_agent_activity1
        where id in (
            select activity_id
            from ct_agent_activity
            where user_id = 'e4011707-a691-11ed-8957-0242ac110003'
            and activity_type = '1'
        )
        order by create_time desc
     */
    fun selectActivitiesByUserId(userId: String): SelectStatementProvider {
        return with(activity1Table) {
            select(activity1Table.allColumns()) {
                from(activity1Table)
                where {
                    id isIn {
                        select(agentActivityTable.activityId) {
                            from(agentActivityTable)
                            where { agentActivityTable.userId isEqualTo userId }
                            and { agentActivityTable.activityType isEqualTo ActivityType.Activity1.code }
                        }
                    }
                }
                orderBy(createTime.descending())
            }
        }
    }
}