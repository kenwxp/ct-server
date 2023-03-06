package com.cloudtimes.product.mapper

import com.cloudtimes.product.domain.CtShopPurchase

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

/**
 * 店铺商品采购Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@Mapper
interface CtShopPurchaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtShopPurchase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtShopPurchase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun selectOne(selectStatement: SelectStatementProvider): CtShopPurchase?
    /**
     * 查询店铺商品采购
     *
     * @param id 店铺商品采购主键
     * @return 店铺商品采购
     */
    fun selectCtShopPurchaseById(id: String): CtShopPurchase?

    /**
     * 查询店铺商品采购列表
     *
     * @param ctShopPurchase 店铺商品采购
     * @return 店铺商品采购集合
     */
    fun selectCtShopPurchaseList(ctShopPurchase: CtShopPurchase): List<CtShopPurchase>

    /**
     * 新增店铺商品采购
     *
     * @param ctShopPurchase 店铺商品采购
     * @return 结果
     */
    fun insertCtShopPurchase(ctShopPurchase: CtShopPurchase): Int

    /**
     * 修改店铺商品采购
     *
     * @param ctShopPurchase 店铺商品采购
     * @return 结果
     */
    fun updateCtShopPurchase(ctShopPurchase: CtShopPurchase): Int

    /**
     * 删除店铺商品采购
     *
     * @param id 店铺商品采购主键
     * @return 结果
     */
    fun deleteCtShopPurchaseById(id: String): Int

    /**
     * 批量删除店铺商品采购
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtShopPurchaseByIds(ids: Array<String>): Int
}