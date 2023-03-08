package com.cloudtimes.promotion.service

import com.cloudtimes.promotion.domain.CtActivity

/**
 * 营销活动Service接口
 *
 * @author 沈兵
 * @date 2023-03-08
 */
interface ICtActivityService {
    /**
     * 查询营销活动
     *
     * @param id 营销活动主键
     * @return 营销活动
     */
    fun selectCtActivityById(id: String): CtActivity?

    /**
     * 查询营销活动列表
     *
     * @param ctActivity 营销活动
     * @return 营销活动集合
     */
    fun selectCtActivityList(ctActivity: CtActivity): List<CtActivity>

    /**
     * 新增营销活动
     *
     * @param ctActivity 营销活动
     * @return 结果
     */
    fun insertCtActivity(ctActivity: CtActivity): Int

    /**
     * 修改营销活动
     *
     * @param ctActivity 营销活动
     * @return 结果
     */
    fun updateCtActivity(ctActivity: CtActivity): Int

    /**
     * 批量删除营销活动
     *
     * @param ids 需要删除的营销活动主键集合
     * @return 结果
     */
    fun deleteCtActivityByIds(ids: Array<String>): Int

    /**
     * 删除营销活动信息
     *
     * @param id 营销活动主键
     * @return 结果
     */
    fun deleteCtActivityById(id: String): Int
}
