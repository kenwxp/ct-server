package com.cloudtimes.product.mapper

import com.cloudtimes.product.domain.CtProductCatalog

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

/**
 * 商品目录Mapper接口
 *
 * @author tank
 * @date 2023-03-06
 */
@Mapper
interface CtProductCatalogMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtProductCatalog>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtProductCatalog>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectOne(selectStatement: SelectStatementProvider): CtProductCatalog?

    /**
     * 查询商品目录
     *
     * @param id 商品目录主键
     * @return 商品目录
     */
    fun selectCtProductCatalogByBarcode(barcode: String): CtProductCatalog?

    /**
     * 查询商品目录列表
     *
     * @param ctProductCatalog 商品目录
     * @return 商品目录集合
     */
    fun selectCtProductCatalogList(ctProductCatalog: CtProductCatalog): List<CtProductCatalog>

    /**
     * 新增商品目录
     *
     * @param ctProductCatalog 商品目录
     * @return 结果
     */
    fun insertCtProductCatalog(ctProductCatalog: CtProductCatalog): Int

    /**
     * 修改商品目录
     *
     * @param ctProductCatalog 商品目录
     * @return 结果
     */
    fun updateCtProductCatalog(ctProductCatalog: CtProductCatalog): Int
}