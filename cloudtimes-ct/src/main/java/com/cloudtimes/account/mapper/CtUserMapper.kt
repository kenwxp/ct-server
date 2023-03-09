package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.response.CtUserDto
import org.apache.ibatis.annotations.*

import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 用户Mapper接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */

@Mapper
interface CtUserMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtUser>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(
        id = "CtUserResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "account", property = "account", jdbcType = JdbcType.VARCHAR),
            Result(column = "wx_open_id", property = "wxOpenId", jdbcType = JdbcType.VARCHAR),
            Result(column = "wx_union_id", property = "wxUnionId", jdbcType = JdbcType.VARCHAR),
            Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
            Result(column = "money_amount", property = "moneyAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "score_amount", property = "scoreAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "total_withdrawal", property = "totalWithdrawal", jdbcType = JdbcType.DECIMAL),
            Result(column = "credit_score", property = "creditScore", jdbcType = JdbcType.INTEGER),
            Result(column = "nick_name", property = "nickName", jdbcType = JdbcType.VARCHAR),
            Result(column = "real_name", property = "realName", jdbcType = JdbcType.VARCHAR),
            Result(column = "sex", property = "sex", jdbcType = JdbcType.CHAR),
            Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
            Result(column = "is_real", property = "isReal", jdbcType = JdbcType.CHAR),
            Result(column = "violation_count", property = "violationCount", jdbcType = JdbcType.INTEGER),
            Result(column = "id_no", property = "idNo", jdbcType = JdbcType.VARCHAR),
            Result(column = "register_store_id", property = "registerStoreId", jdbcType = JdbcType.OTHER),
            Result(column = "is_agent", property = "isAgent", jdbcType = JdbcType.CHAR),
            Result(column = "is_shop_boss", property = "isShopBoss", jdbcType = JdbcType.CHAR),
            Result(column = "last_login_ip", property = "lastLoginIp", jdbcType = JdbcType.VARCHAR),
            Result(column = "last_login_time", property = "lastLoginTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_boss_time", property = "createBossTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_agent_time", property = "createAgentTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "agent_state", property = "agentState", jdbcType = JdbcType.CHAR),
            Result(column = "agent_type", property = "agentType", jdbcType = JdbcType.CHAR),
            Result(column = "boss_state", property = "bossState", jdbcType = JdbcType.CHAR),
            Result(column = "customer_state", property = "customerState", jdbcType = JdbcType.CHAR),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtUser>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtUserResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtUser?

    @InsertProvider(type = SqlProviderAdapter::class, method = "insert")
    @SelectKey(statement = ["select uuid() from dual"], keyProperty="row.id", resultType=String::class, before=true)
    override fun insert(insertStatement: InsertStatementProvider<CtUser>): Int

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    fun selectCtUserById(id: String): CtUser?

    /**
     * 通过登录帐号查询用户
     *
     * @param account
     * @return
     */
    fun selectCtUserByAccount(account: String): CtUser?

    /**
     * 通过wxOpenId查询用户
     *
     * @param wxOpenId
     * @return
     */
    fun selectCtUserByWxOpenId(wxOpenId: String): CtUser?

    /**
     * 通过wxUnionId查询用户
     *
     * @param wxUnionId
     * @return
     */
    fun selectCtUserByWxUnionId(wxUnionId: String): CtUser?

    /**
     * 通过mobile查询用户
     *
     * @param mobile
     * @return
     */
    fun selectCtUserByMobile(mobile: String): CtUser?

    /**
     * 查询用户列表
     *
     * @param ctUser 用户
     * @return 用户集合
     */
    fun selectCtUserList(ctUser: CtUser): List<CtUser>


    fun selectCtUserListPlus(ctUser: CtUser): List<CtUserDto>

    /**
     * 新增用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    fun insertCtUser(ctUser: CtUser): Int

    /**
     * 修改用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    fun updateCtUser(ctUser: CtUser): Int

    /**
     * 删除用户
     *
     * @param id 用户主键
     * @return 结果
     */
    fun deleteCtUserById(id: Long): Int

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtUserByIds(ids: Array<Long>): Int
}