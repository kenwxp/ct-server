package com.cloudtimes.product.service

import com.cloudtimes.product.domain.CtShopPurchase

/**
 * 店铺商品采购Service接口
 *
 * @author 沈兵
 * @date 2023-03-05
 */
interface ICtShopPurchaseService {
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
     * 批量删除店铺商品采购
     *
     * @param ids 需要删除的店铺商品采购主键集合
     * @return 结果
     */
    fun deleteCtShopPurchaseByIds(ids: Array<String>): Int

    /**
     * 删除店铺商品采购信息
     *
     * @param id 店铺商品采购主键
     * @return 结果
     */
    fun deleteCtShopPurchaseById(id: String): Int
}