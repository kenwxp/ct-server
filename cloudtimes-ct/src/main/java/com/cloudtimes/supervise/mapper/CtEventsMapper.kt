package com.cloudtimes.supervise.mapper

import com.cloudtimes.supervise.domain.CtEvents

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
 * 事件Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@Mapper
interface CtEventsMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtEvents>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtEventsResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "event_type", property = "eventType", jdbcType = JdbcType.CHAR),
            Result(column = "event_name", property = "eventName", jdbcType = JdbcType.VARCHAR),
            Result(column = "context", property = "context", jdbcType = JdbcType.VARCHAR),
            Result(column = "sender", property = "sender", jdbcType = JdbcType.OTHER),
            Result(column = "sender_name", property = "senderName", jdbcType = JdbcType.VARCHAR),
            Result(column = "receiver", property = "receiver", jdbcType = JdbcType.OTHER),
            Result(column = "receiver_name", property = "receiverName", jdbcType = JdbcType.VARCHAR),
            Result(column = "task_id", property = "taskId", jdbcType = JdbcType.OTHER),
            Result(column = "shopping_id", property = "shoppingId", jdbcType = JdbcType.OTHER),
            Result(column = "user_type", property = "userType", jdbcType = JdbcType.CHAR),
            Result(column = "source_type", property = "sourceType", jdbcType = JdbcType.CHAR),
            Result(column = "is_stopped", property = "isStopped", jdbcType = JdbcType.CHAR),
            Result(column = "valid_days", property = "validDays", jdbcType = JdbcType.INTEGER),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtEvents>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtEventsResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtEvents?

    /**
     * 查询事件
     *
     * @param id 事件主键
     * @return 事件
     */
    fun selectCtEventsById(id: String): CtEvents?

    /**
     * 查询事件列表
     *
     * @param ctEvents 事件
     * @return 事件集合
     */
    fun selectCtEventsList(ctEvents: CtEvents): List<CtEvents>

    /**
     * 新增事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    fun insertCtEvents(ctEvents: CtEvents): Int

    /**
     * 修改事件
     *
     * @param ctEvents 事件
     * @return 结果
     */
    fun updateCtEvents(ctEvents: CtEvents): Int

    /**
     * 删除事件
     *
     * @param id 事件主键
     * @return 结果
     */
    fun deleteCtEventsById(id: String): Int

    /**
     * 批量删除事件
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtEventsByIds(ids: Array<String>): Int
}