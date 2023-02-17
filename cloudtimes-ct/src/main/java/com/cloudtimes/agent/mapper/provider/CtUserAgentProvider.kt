package com.cloudtimes.agent.mapper.provider

import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.table.agentTable
import com.cloudtimes.account.table.userTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.util.Date

object CtUserAgentProvider {
    fun selectById(id: String): SelectStatementProvider {
        return with(agentTable) {
            select(agentTable.allColumns()) {
                from(agentTable)
                where {
                    userId isEqualTo id
                }
            }
        }
    }

    fun selectByIds(ids: List<String>): SelectStatementProvider {
        return with(agentTable) {
            select(agentTable.allColumns()) {
                from(agentTable)
                where {
                    userId isIn ids
                }
            }
        }
    }


    /** 查询代理团队所有成员 */
    fun selectTeamMember(userId: String): SelectStatementProvider {
        return select(
            userTable.id, userTable.nickName, userTable.realName, userTable.mobile,
            userTable.sex, userTable.agentState, userTable.createDate,
            agentTable.totalSalesReward, agentTable.totalDividend, agentTable.totalActivityReward
        ) {
            from(userTable)
            leftJoin(agentTable) { on(userTable.id) equalTo agentTable.userId }
            where { userTable.id isEqualTo userId }
        }
    }

    /** 查询代理团队所有成员 */
    fun selectTeamMembers(userId: String): SelectStatementProvider {
       return select(
           userTable.id, userTable.nickName, userTable.realName, userTable.mobile,
           userTable.sex, userTable.agentState, userTable.createDate,
           agentTable.totalSalesReward, agentTable.totalDividend, agentTable.totalActivityReward
       ) {
           from(userTable)
           leftJoin(agentTable) { on(userTable.id) equalTo agentTable.userId }
           where {
                userTable.id isIn  {
                    select(agentTable.userId) {
                        from(agentTable)
                        where { agentTable.parentUserId isEqualTo userId }
                    }
                }
           }
       }
    }

    /** 更新代理现金余额语句 */
    fun updateAgentCashStmt(row: CtUserAgent): UpdateStatementProvider {
        return with(agentTable) {
            update(agentTable) {
                set(cashAmount) equalTo row.cashAmount!!
                set(updateTime) equalTo Date()
                where {
                    userId isEqualTo row.userId!!
                }
            }
        }
    }
}
