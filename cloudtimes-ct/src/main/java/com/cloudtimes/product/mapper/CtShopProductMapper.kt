package com.cloudtimes.product.mapper

import com.cloudtimes.product.domain.CtShopProduct
import com.cloudtimes.thirdpart.dto.response.YcygSuggestPurchase
import org.apache.ibatis.annotations.Param

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

/**
 * 店铺商品Mapper接口
 *
 * @author tank
 * @date 2023-02-15
 */
@Mapper
interface CtShopProductMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtShopProduct>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtShopProduct>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectOne(selectStatement: SelectStatementProvider): CtShopProduct?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectSuggestProducts(selectStatement: SelectStatementProvider): List<YcygSuggestPurchase>

    /**
     * 查询店铺商品
     *
     * @param id 店铺商品主键
     * @return 店铺商品
     */
    fun selectCtShopProductById(id: String): CtShopProduct?

    /**
     * 查询店铺商品列表
     *
     * @param ctShopProduct 店铺商品
     * @return 店铺商品集合
     */
    fun selectCtShopProductList(ctShopProduct: CtShopProduct): List<CtShopProduct>

    /**
     * 新增店铺商品
     *
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    fun insertCtShopProduct(ctShopProduct: CtShopProduct): Int

    /**
     * 修改店铺商品
     *
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    fun updateCtShopProduct(ctShopProduct: CtShopProduct): Int

    /**
     * 删除店铺商品
     *
     * @param id 店铺商品主键
     * @return 结果
     */
    fun deleteCtShopProductById(id: String): Int

    /**
     * 批量删除店铺商品
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtShopProductByIds(ids: Array<String>): Int

    /**
     * 查询商店商品列表
     *
     * @param shopNo
     * @return
     */
    fun selectCtShopProductListByStore(@Param("shopNo") shopNo: String): List<CtShopProduct>

    /**
     * 更新商品库存
     *
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    fun updateCtShopProductStock(ctShopProduct: CtShopProduct): Int
}