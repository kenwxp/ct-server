package com.cloudtimes.promotion.service

import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.domain.CtUserDrawRel

/**
 * 用户中奖Service接口
 *
 * @author tank
 * @date 2023-03-08
 */
interface ICtUserDrawRelService {
    /* 查询用户中奖规则 */
    fun selectCtLuckyDrawRuleById(activityId: String, userId: String): CtLuckyDrawRule?

    /**
     * 查询用户中奖
     *
     * @param userId 用户中奖主键
     * @return 用户中奖
     */
    fun selectCtUserDrawRelById(activityId: String, userId: String): CtUserDrawRel?

    /**
     * 查询用户中奖列表
     *
     * @param ctUserDrawRel 用户中奖
     * @return 用户中奖集合
     */
    fun selectCtUserDrawRelList(ctUserDrawRel: CtUserDrawRel): List<CtUserDrawRel>

    /**
     * 新增用户中奖
     *
     * @param ctUserDrawRel 用户中奖
     * @return 结果
     */
    fun insertCtUserDrawRel(ctUserDrawRel: CtUserDrawRel): Int
}
