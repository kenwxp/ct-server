package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtUserAppRel

/**
 * 用户与应用关系Mapper接口
 *
 * @author 沈兵
 * @date 2023-03-08
 */
interface CtUserAppRelMapper {
    /**
     * 查询用户与应用关系
     *
     * @param userId 用户与应用关系主键
     * @return 用户与应用关系
     */
    fun selectCtUserAppRelByUserId(userId: String): CtUserAppRel?

    /**
     * 查询用户与应用关系列表
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 用户与应用关系集合
     */
    fun selectCtUserAppRelList(ctUserAppRel: CtUserAppRel): List<CtUserAppRel>

    /**
     * 新增用户与应用关系
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 结果
     */
    fun insertCtUserAppRel(ctUserAppRel: CtUserAppRel): Int

    /**
     * 修改用户与应用关系
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 结果
     */
    fun updateCtUserAppRel(ctUserAppRel: CtUserAppRel): Int

    /**
     * 删除用户与应用关系
     *
     * @param userId 用户与应用关系主键
     * @return 结果
     */
    fun deleteCtUserAppRelByUserId(userId: String): Int

    /**
     * 批量删除用户与应用关系
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtUserAppRelByUserIds(userIds: Array<String>): Int
}