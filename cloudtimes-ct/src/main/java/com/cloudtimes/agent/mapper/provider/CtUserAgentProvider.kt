package com.cloudtimes.agent.mapper.provider

import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.table.agentTable
import com.cloudtimes.account.table.userTable
import com.cloudtimes.agent.dto.request.AgentStoreRequest
import com.cloudtimes.agent.table.commissionSettlementTable
import com.cloudtimes.hardwaredevice.table.storeTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.elements.sortColumn
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

    fun selectAgentStoresStmt(request: AgentStoreRequest): SelectStatementProvider {
        return select(
            storeTable.id,
            storeTable.storeNo,
            storeTable.name,
            storeTable.agentId,
            storeTable.saleAmount,
            storeTable.buildState,
            storeTable.state,
            storeTable.createDate,
            storeTable.storeOnlineDate,
            storeTable.contactName,
            // 佣金信息
            commissionSettlementTable.id.`as`("commission_id"),
            commissionSettlementTable.state.`as`("commission_state"),
            commissionSettlementTable.isAgentOk,
            commissionSettlementTable.isPlatformOk,
            commissionSettlementTable.amount,
            commissionSettlementTable.beforeTaxAmount,
            commissionSettlementTable.taxAmount,
            commissionSettlementTable.taxRatio,
        ) {
            from(storeTable)
            leftJoin(commissionSettlementTable) {
                on(storeTable.id) equalTo commissionSettlementTable.storeId
            }
            where {
                storeTable.agentId isIn {
                    select(agentTable.userId) {
                        from(agentTable)
                        where { agentTable.userId isEqualTo request.userId}
                        or { agentTable.parentUserId isEqualTo request.userId }
                    }
                }
            }
            if (!request.buildState.isNullOrEmpty()) {
                and { storeTable.buildState isEqualTo request.buildState!! }
            }

            if (!request.storeName.isNullOrEmpty()) {
                and { storeTable.name isLike  "%${request.storeName}%" }
            }
            orderBy(sortColumn("st", storeTable.createTime).descending())
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
