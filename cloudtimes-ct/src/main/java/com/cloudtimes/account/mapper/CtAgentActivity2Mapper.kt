package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtAgentActivity2

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

/**
 * 代理活动2Mapper接口
 *
 * @author tank
 * @date 2023-02-13
 */
@Mapper
interface CtAgentActivity2Mapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivity2>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivity2Result", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            Result(column = "activity_type", property = "activityType", jdbcType = JdbcType.CHAR),
            Result(column = "store_count", property = "storeCount", jdbcType = JdbcType.INTEGER),
            Result(column = "used_store_count", property = "usedStoreCount", jdbcType = JdbcType.INTEGER),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "is_enabled", property = "isEnabled", jdbcType = JdbcType.CHAR),
            Result(column = "state", property = "state", jdbcType = JdbcType.CHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "end_time", property = "endTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivity2>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivity2Result")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivity2?

    /**
     * 查询代理活动2
     *
     * @param id 代理活动2主键
     * @return 代理活动2
     */
    fun selectCtAgentActivity2ById(id: String): CtAgentActivity2?

    /**
     * 查询代理活动2列表
     *
     * @param ctAgentActivity2 代理活动2
     * @return 代理活动2集合
     */
    fun selectCtAgentActivity2List(ctAgentActivity2: CtAgentActivity2): List<CtAgentActivity2>

    /**
     * 新增代理活动2
     *
     * @param ctAgentActivity2 代理活动2
     * @return 结果
     */
    fun insertCtAgentActivity2(ctAgentActivity2: CtAgentActivity2): Int

    /**
     * 修改代理活动2
     *
     * @param ctAgentActivity2 代理活动2
     * @return 结果
     */
    fun updateCtAgentActivity2(ctAgentActivity2: CtAgentActivity2): Int

    /**
     * 删除代理活动2
     *
     * @param id 代理活动2主键
     * @return 结果
     */
    fun deleteCtAgentActivity2ById(id: String): Int

    /**
     * 批量删除代理活动2
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity2ByIds(ids: Array<String>): Int
}