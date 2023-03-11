package com.cloudtimes.product.mapper

import com.cloudtimes.product.domain.CtProductCategory

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

/**
 * 商品分类Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@Mapper
interface CtProductCategoryMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtProductCategory>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtProductCategory>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectOne(selectStatement: SelectStatementProvider): CtProductCategory?
    /**
     * 查询商品分类
     *
     * @param categoryCode 商品分类主键
     * @return 商品分类
     */
    fun selectCtProductCategoryByCategoryCode(categoryCode: String): CtProductCategory?

    /**
     * 新增商品分类
     *
     * @param ctProductCategory 商品分类
     * @return 结果
     */
    fun insertCtProductCategory(ctProductCategory: CtProductCategory): Int

    /**
     * 修改商品分类
     *
     * @param ctProductCategory 商品分类
     * @return 结果
     */
    fun updateCtProductCategory(ctProductCategory: CtProductCategory): Int

    /**
     * 删除商品分类
     *
     * @param categoryCode 商品分类主键
     * @return 结果
     */
    fun deleteCtProductCategoryByCategoryCode(categoryCode: String): Int

    /**
     * 批量删除商品分类
     *
     * @param categoryCodes 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtProductCategoryByCategoryCodes(categoryCodes: Array<String>): Int
}