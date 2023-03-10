package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtSuggestion
import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.account.domain.CtUserAppRel
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 用户与应用关系Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-08
 */
interface CtUserAppRelMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtTransferBook>, CommonUpdateMapper {
    @SelectProvider(type= SqlProviderAdapter::class, method="select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtUserAppRel>

    @SelectProvider(type= SqlProviderAdapter::class, method="select")
    fun selectOne(selectStatement: SelectStatementProvider): CtUserAppRel?

    /**
     * 查询用户与应用关系
     *
     * @param userId 用户与应用关系主键
     * @return 用户与应用关系
     */
    fun selectCtUserAppRelByUserId(userId: String): CtUserAppRel?

    /**
     * 查询用户与应用关系列表
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 用户与应用关系集合
     */
    fun selectCtUserAppRelList(ctUserAppRel: CtUserAppRel): List<CtUserAppRel>

    /**
     * 新增用户与应用关系
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 结果
     */
    fun insertCtUserAppRel(ctUserAppRel: CtUserAppRel): Int

    /**
     * 修改用户与应用关系
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 结果
     */
    fun updateCtUserAppRel(ctUserAppRel: CtUserAppRel): Int

    /**
     * 删除用户与应用关系
     *
     * @param userId 用户与应用关系主键
     * @return 结果
     */
    fun deleteCtUserAppRelByUserId(userId: String): Int

    /**
     * 批量删除用户与应用关系
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtUserAppRelByUserIds(userIds: Array<String>): Int
}