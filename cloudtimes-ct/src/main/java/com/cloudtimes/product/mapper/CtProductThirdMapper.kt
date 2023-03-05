package com.cloudtimes.product.mapper

import com.cloudtimes.product.domain.CtProductThird

/**
 * 第三方商品Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-05
 */
interface CtProductThirdMapper {
    /**
     * 查询第三方商品
     *
     * @param thirdCode 第三方商品主键
     * @return 第三方商品
     */
    fun selectCtProductThirdByThirdCode(thirdCode: String): CtProductThird?

    /**
     * 查询第三方商品列表
     *
     * @param ctProductThird 第三方商品
     * @return 第三方商品集合
     */
    fun selectCtProductThirdList(ctProductThird: CtProductThird): List<CtProductThird>

    /**
     * 新增第三方商品
     *
     * @param ctProductThird 第三方商品
     * @return 结果
     */
    fun insertCtProductThird(ctProductThird: CtProductThird): Int

    /**
     * 修改第三方商品
     *
     * @param ctProductThird 第三方商品
     * @return 结果
     */
    fun updateCtProductThird(ctProductThird: CtProductThird): Int

    /**
     * 删除第三方商品
     *
     * @param thirdCode 第三方商品主键
     * @return 结果
     */
    fun deleteCtProductThirdByThirdCode(thirdCode: String): Int

    /**
     * 批量删除第三方商品
     *
     * @param thirdCodes 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtProductThirdByThirdCodes(thirdCodes: Array<String>): Int
}