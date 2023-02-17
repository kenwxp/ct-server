package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentActivity

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper

/**
 * 代理活动Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@Mapper
interface CtAgentActivityMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivity>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivityResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "is_enabled", property = "isEnabled", jdbcType = JdbcType.CHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "end_time", property = "endTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivity>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivityResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivity?

    /**
     * 查询代理活动
     *
     * @param id 代理活动主键
     * @return 代理活动
     */
    fun selectCtAgentActivityById(id: String): CtAgentActivity?

    /**
     * 查询代理活动列表
     *
     * @param ctAgentActivity 代理活动
     * @return 代理活动集合
     */
    fun selectCtAgentActivityList(ctAgentActivity: CtAgentActivity): List<CtAgentActivity>

    /**
     * 新增代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    fun insertCtAgentActivity(ctAgentActivity: CtAgentActivity): Int

    /**
     * 修改代理活动
     *
     * @param ctAgentActivity 代理活动
     * @return 结果
     */
    fun updateCtAgentActivity(ctAgentActivity: CtAgentActivity): Int

    /**
     * 删除代理活动
     *
     * @param id 代理活动主键
     * @return 结果
     */
    fun deleteCtAgentActivityById(id: String): Int

    /**
     * 批量删除代理活动
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivityByIds(ids: Array<String>): Int
}
