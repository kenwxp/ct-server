package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.account.dto.response.TransferRecord
import org.apache.ibatis.annotations.*

import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 转账登记簿Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-16
 */
@Mapper
interface CtTransferBookMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtTransferBook>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtTransferBookResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.OTHER, id=true),
        Result(column="payer", property="payer", jdbcType=JdbcType.OTHER),
        Result(column="payee", property="payee", jdbcType=JdbcType.OTHER),
        Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        Result(column="year_month", property="yearMonth", jdbcType=JdbcType.INTEGER),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.DATE),
        Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtTransferBook>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectTransferRecords(selectStatement: SelectStatementProvider): List<TransferRecord>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtTransferBookResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtTransferBook?

    @InsertProvider(type = SqlProviderAdapter::class, method = "insert")
    @SelectKey(statement = ["select uuid() from dual"], keyProperty="row.id", resultType=String::class, before=true)
    override fun insert(insertStatement: InsertStatementProvider<CtTransferBook>): Int
}
