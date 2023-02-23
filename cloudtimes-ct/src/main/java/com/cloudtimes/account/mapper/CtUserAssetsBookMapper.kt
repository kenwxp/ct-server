package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtUserAssetsBook

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 用户资产簿记Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-07
 */
@Mapper
interface CtUserAssetsBookMapper : CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtUserAssetsBookResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "year_month", property = "yearMonth", jdbcType = JdbcType.INTEGER, id = true),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.OTHER),
            Result(column = "card_id", property = "cardId", jdbcType = JdbcType.OTHER),
            Result(column = "book_type", property = "bookType", jdbcType = JdbcType.CHAR),
            Result(column = "operate_type", property = "operateType", jdbcType = JdbcType.CHAR),
            Result(column = "assets_type", property = "assetsType", jdbcType = JdbcType.CHAR),
            Result(column = "before_amount", property = "beforeAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "alter_amount", property = "alterAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "amount", property = "amount", jdbcType = JdbcType.DECIMAL),
            Result(column = "transfer_id", property = "transferId", jdbcType = JdbcType.OTHER),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "remark", property = "remark", jdbcType = JdbcType.LONGVARCHAR)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtUserAssetsBook>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtUserAssetsBookResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtUserAssetsBook?


    @Select(
        "\${insertStatement} returning *"
    )
    @ResultMap("CtUserAssetsBookResult")
    fun insertOne(insertStatement: GeneralInsertStatementProvider): CtUserAssetsBook?
}