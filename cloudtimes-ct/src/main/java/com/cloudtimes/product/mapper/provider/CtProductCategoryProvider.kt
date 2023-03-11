package com.cloudtimes.product.mapper.provider

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.request.VerifyRealNameRequest
import com.cloudtimes.account.table.userTable
import com.cloudtimes.agent.dto.request.AgentRegisterRequest
import com.cloudtimes.common.enums.AgentState
import com.cloudtimes.common.enums.YesNoState
import com.cloudtimes.common.utils.extension.beginLikeWith
import com.cloudtimes.common.utils.extension.likeWith
import com.cloudtimes.common.utils.extension.valueOrNull
import com.cloudtimes.product.domain.CtProductCategory
import com.cloudtimes.product.table.CtProductCategoryTable
import org.mybatis.dynamic.sql.SqlColumn
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.elements.column
import org.mybatis.dynamic.sql.util.kotlin.elements.constant
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import java.sql.JDBCType
import java.util.Date


object CtProductCategoryProvider {
    private val oneColumn = constant<Int>("1")
    private val categoryTable = CtProductCategoryTable()

    fun selectManyStmt(request: CtProductCategory): SelectStatementProvider {
        return select(categoryTable.allColumns()) {
            from(categoryTable)
            where { oneColumn isEqualTo 1 }
            and { categoryTable.tier isEqualToWhenPresent request.tier.valueOrNull() }
            and { categoryTable.categoryCode isLikeWhenPresent request.categoryCode.beginLikeWith() }
            and { categoryTable.categoryName isLikeWhenPresent request.categoryName.likeWith() }
        }
    }
}