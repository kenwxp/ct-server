package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtUserAssets
import org.apache.ibatis.annotations.Mapper

/**
 * 用户Mapper接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@Mapper
interface CtUserAssetsMapper {
    /**
     * 查询用户资产
     *
     * @param id 用户资产主键
     * @return 用户资产
     */
    fun selectCtUserAssetsById(id: String?): CtUserAssets?

    /**
     * 新增用户资产
     *
     * @param ctUserAssets 用户资产
     * @return 结果
     */
    fun insertCtUserAssets(ctUserAssets: CtUserAssets?): Int

    /**
     * 修改用户资产
     *
     * @param ctUserAssets 用户资产
     * @return 结果
     */
    fun updateCtUserAssets(ctUserAssets: CtUserAssets?): Int

    /**
     * 删除用户资产
     *
     * @param id 用户资产主键
     * @return 结果
     */
    fun deleteCtUserAssetsById(id: Long?): Int

    /**
     * 批量删除用户资产
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtUserAssetsByIds(ids: Array<Long?>?): Int
}