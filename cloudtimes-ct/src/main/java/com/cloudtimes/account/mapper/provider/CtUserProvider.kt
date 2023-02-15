package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.request.VerifyRealNameRequest
import com.cloudtimes.account.table.userTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import java.util.Date

object CtUserProvider {
    fun selectUserByUnionId(unionId: String): SelectStatementProvider {
        return select(userTable.allColumns()) {
            from(userTable)
            where { userTable.wxUnionId isEqualTo unionId }
        }
    }

    fun selectUserBySsn(ssn: String): SelectStatementProvider {
        return select(userTable.allColumns()) {
            from(userTable)
            where { userTable.idNo isEqualTo ssn }
        }
    }

    fun insertWxUser(ctUser: CtUser): InsertStatementProvider<CtUser> {
        return  with (userTable) {
            insert(ctUser) {
                into(userTable)
                map(id) toProperty "id"
                map(wxUnionId) toProperty "wxUnionId"
                map(wxAvatar) toProperty "wxAvatar"
                map(nickName) toProperty "nickName"
            }
        }
    }

    fun updateRealName(request: VerifyRealNameRequest): UpdateStatementProvider {
        return with(userTable) {
            update(userTable) {
                set(realName).equalTo(request.name!!)
                set(idNo).equalTo(request.ssn!!)
                set(updateTime).equalTo(Date())
                where {
                    id isEqualTo request.userId!!
                }
            }
        }
    }
}