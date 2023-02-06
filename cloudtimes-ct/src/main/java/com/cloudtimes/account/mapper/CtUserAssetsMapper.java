package com.cloudtimes.account.mapper;

import com.cloudtimes.account.domain.CtUserAssets;

import java.util.List;

/**
 * 用户Mapper接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */
public interface CtUserAssetsMapper {
    /**
     * 查询用户资产
     *
     * @param id 用户资产主键
     * @return 用户资产
     */
    public CtUserAssets selectCtUserAssetsById(String id);


    /**
     * 新增用户资产
     *
     * @param ctUserAssets 用户资产
     * @return 结果
     */
    public int insertCtUserAssets(CtUserAssets ctUserAssets);

    /**
     * 修改用户资产
     *
     * @param ctUserAssets 用户资产
     * @return 结果
     */
    public int updateCtUserAssets(CtUserAssets ctUserAssets);

    /**
     * 删除用户资产
     *
     * @param id 用户资产主键
     * @return 结果
     */
    public int deleteCtUserAssetsById(Long id);

    /**
     * 批量删除用户资产
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtUserAssetsByIds(Long[] ids);
}
