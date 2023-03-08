package com.cloudtimes.promotion.mapper

import com.cloudtimes.promotion.domain.CtActivityStoreRel

/**
 * 活动店铺关系Mapper接口
 *
 * @author tank
 * @date 2023-03-08
 */
interface CtActivityStoreRelMapper {
    /**
     * 查询活动店铺关系
     *
     * @param activityId 活动店铺关系主键
     * @return 活动店铺关系
     */
    fun selectCtActivityStoreRelByActivityId(activityId: String): CtActivityStoreRel?

    /**
     * 查询活动店铺关系列表
     *
     * @param ctActivityStoreRel 活动店铺关系
     * @return 活动店铺关系集合
     */
    fun selectCtActivityStoreRelList(ctActivityStoreRel: CtActivityStoreRel): List<CtActivityStoreRel>

    /**
     * 新增活动店铺关系
     *
     * @param ctActivityStoreRel 活动店铺关系
     * @return 结果
     */
    fun insertCtActivityStoreRel(ctActivityStoreRel: CtActivityStoreRel): Int

    /**
     * 修改活动店铺关系
     *
     * @param ctActivityStoreRel 活动店铺关系
     * @return 结果
     */
    fun updateCtActivityStoreRel(ctActivityStoreRel: CtActivityStoreRel): Int

    /**
     * 删除活动店铺关系
     *
     * @param activityId 活动店铺关系主键
     * @return 结果
     */
    fun deleteCtActivityStoreRelByActivityId(activityId: String): Int

    /**
     * 批量删除活动店铺关系
     *
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtActivityStoreRelByActivityIds(activityIds: Array<String>): Int
}
