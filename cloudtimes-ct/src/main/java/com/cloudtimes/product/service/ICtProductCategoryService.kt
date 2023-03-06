package com.cloudtimes.product.service

import com.cloudtimes.product.domain.CtProductCategory

/**
 * 商品分类Service接口
 *
 * @author 沈兵
 * @date 2023-03-05
 */
interface ICtProductCategoryService {
    /**
     * 查询商品分类
     *
     * @param categoryCode 商品分类主键
     * @return 商品分类
     */
    fun selectCtProductCategoryByCategoryCode(categoryCode: String): CtProductCategory?

    /**
     * 查询商品分类列表
     *
     * @param ctProductCategory 商品分类
     * @return 商品分类集合
     */
    fun selectCtProductCategoryList(ctProductCategory: CtProductCategory): List<CtProductCategory>

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
     * 批量删除商品分类
     *
     * @param categoryCodes 需要删除的商品分类主键集合
     * @return 结果
     */
    fun deleteCtProductCategoryByCategoryCodes(categoryCodes: Array<String>): Int

    /**
     * 删除商品分类信息
     *
     * @param categoryCode 商品分类主键
     * @return 结果
     */
    fun deleteCtProductCategoryByCategoryCode(categoryCode: String): Int
}