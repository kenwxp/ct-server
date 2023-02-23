package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtWithdrawalBook

import org.apache.ibatis.annotations.*

import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 提现登记薄Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Mapper
interface CtWithdrawalBookMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtWithdrawalBook>, CommonUpdateMapper {
        @SelectProvider(type=SqlProviderAdapter::class, method="select")
        @Results(id="CtWithdrawalBookResult", value = [
            Result(column="id", property="id", jdbcType=JdbcType.OTHER, id=true),
            Result(column="user_id", property="userId", jdbcType=JdbcType.OTHER),
            Result(column="user_type", property="userType", jdbcType=JdbcType.CHAR),
            Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            Result(column="pay_serial", property="paySerial", jdbcType=JdbcType.VARCHAR),
            Result(column="third_serial", property="thirdSerial", jdbcType=JdbcType.VARCHAR),
            Result(column="pay_state", property="payState", jdbcType=JdbcType.CHAR),
            Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            Result(column="apply_date", property="applyDate", jdbcType=JdbcType.DATE),
            Result(column="pay_date", property="payDate", jdbcType=JdbcType.DATE),
            Result(column="pay_time", property="payTime", jdbcType=JdbcType.DATE),
            Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
            Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            Result(column="del_flag", property="delFlag", jdbcType=JdbcType.CHAR)
        ])
        fun selectMany(selectStatement: SelectStatementProvider): List<CtWithdrawalBook>

        @SelectProvider(type=SqlProviderAdapter::class, method="select")
        @ResultMap("CtWithdrawalBookResult")
        fun selectOne(selectStatement: SelectStatementProvider): CtWithdrawalBook

    /**
     * 查询提现登记薄
     *
     * @param id 提现登记薄主键
     * @return 提现登记薄
     */
    fun selectCtWithdrawalBookById(id: String): CtWithdrawalBook?

    /**
     * 查询提现登记薄列表
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 提现登记薄集合
     */
    fun selectCtWithdrawalBookList(ctWithdrawalBook: CtWithdrawalBook): List<CtWithdrawalBook>

    /**
     * 新增提现登记薄
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    fun insertCtWithdrawalBook(ctWithdrawalBook: CtWithdrawalBook): Int

    /**
     * 修改提现登记薄
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    fun updateCtWithdrawalBook(ctWithdrawalBook: CtWithdrawalBook): Int

    /**
     * 删除提现登记薄
     *
     * @param id 提现登记薄主键
     * @return 结果
     */
    fun deleteCtWithdrawalBookById(id: String): Int

    /**
     * 批量删除提现登记薄
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtWithdrawalBookByIds(ids: Array<String>): Int
}