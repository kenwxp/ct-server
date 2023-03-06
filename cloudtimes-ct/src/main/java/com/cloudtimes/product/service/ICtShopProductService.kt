package com.cloudtimes.product.service

import com.cloudtimes.product.domain.CtShopProduct

/**
 * 店铺商品Service接口
 *
 * @author tank
 * @date 2023-02-15
 */
interface ICtShopProductService {
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
     * 批量删除店铺商品
     *
     * @param ids 需要删除的店铺商品主键集合
     * @return 结果
     */
    fun deleteCtShopProductByIds(ids: Array<String>): Int

    /**
     * 删除店铺商品信息
     *
     * @param id 店铺商品主键
     * @return 结果
     */
    fun deleteCtShopProductById(id: String): Int
}