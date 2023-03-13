package com.cloudtimes.promotion.mapper

import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.domain.CtUserDrawRel
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 用户中奖Mapper接口
 *
 * @author tank
 * @date 2023-03-08
 */
@Mapper
interface CtUserDrawRelMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtUserDrawRel>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtUserDrawRel>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    fun selectOne(selectStatement: SelectStatementProvider): CtUserDrawRel?

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    fun selectOneDrawRule(selectStatement: SelectStatementProvider): CtLuckyDrawRule?

    /**
     * 查询用户中奖
     *
     * @param userId 用户中奖主键
     * @return 用户中奖
     */
    fun selectCtUserDrawRelByUserId(userId: String): CtUserDrawRel?

    /**
     * 查询用户中奖列表
     *
     * @param ctUserDrawRel 用户中奖
     * @return 用户中奖集合
     */
    fun selectCtUserDrawRelList(ctUserDrawRel: CtUserDrawRel): List<CtUserDrawRel>
}
