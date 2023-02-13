package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtAgentActivity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

@Mapper
interface CtAgentActivityMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivity>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtAgentActivityResult", value = [
        Result(column="user_id", property="userId", jdbcType=JdbcType.OTHER, id=true),
        Result(column="activity_id", property="activityId", jdbcType=JdbcType.OTHER)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivity>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtAgentActivityResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivity?
}
