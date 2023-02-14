package com.cloudtimes.account.mapper.provider


import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update

import com.cloudtimes.account.table.activity2Table
import com.cloudtimes.account.table.agentActivityTable
import com.cloudtimes.common.enums.ActivityType

object CtAgentActivity2Provider {
    /**
        select *
        from ct_agent_activity2
        where id in (
            select activity_id
            from ct_agent_activity
            where user_id = 'e4022707-a692-22ed-8957-0242ac220003'
            and activity_type = '2'
        )
        order by create_time desc
     */
    fun selectActivitiesByUserId(userId: String): SelectStatementProvider {
        return with(activity2Table) {
            select(activity2Table.allColumns()) {
                from(activity2Table)
                where {
                    id isIn {
                        select(agentActivityTable.activityId) {
                            from(agentActivityTable)
                            where { agentActivityTable.userId isEqualTo userId }
                            and { agentActivityTable.activityType isEqualTo ActivityType.Activity2.code }
                        }
                    }
                }
                orderBy(createTime.descending())
            }
        }
    }
}