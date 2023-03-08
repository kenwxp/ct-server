package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtStoreDiscount

/**
 * 店铺会员折扣Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-08
 */
interface CtStoreDiscountMapper {
    /**
     * 查询店铺会员折扣
     *
     * @param storeId 店铺会员折扣主键
     * @return 店铺会员折扣
     */
    fun selectCtStoreDiscountByStoreId(storeId: String): CtStoreDiscount?

    /**
     * 查询店铺会员折扣列表
     *
     * @param ctStoreDiscount 店铺会员折扣
     * @return 店铺会员折扣集合
     */
    fun selectCtStoreDiscountList(ctStoreDiscount: CtStoreDiscount): List<CtStoreDiscount>

    /**
     * 新增店铺会员折扣
     *
     * @param ctStoreDiscount 店铺会员折扣
     * @return 结果
     */
    fun insertCtStoreDiscount(ctStoreDiscount: CtStoreDiscount): Int

    /**
     * 修改店铺会员折扣
     *
     * @param ctStoreDiscount 店铺会员折扣
     * @return 结果
     */
    fun updateCtStoreDiscount(ctStoreDiscount: CtStoreDiscount): Int

    /**
     * 删除店铺会员折扣
     *
     * @param storeId 店铺会员折扣主键
     * @return 结果
     */
    fun deleteCtStoreDiscountByStoreId(storeId: String): Int

    /**
     * 批量删除店铺会员折扣
     *
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtStoreDiscountByStoreIds(storeIds: Array<String>): Int
}