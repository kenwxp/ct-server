package com.cloudtimes.resources.service;

import java.util.List;
import com.cloudtimes.resources.domain.CtRegion;

/**
 * 地区信息Service接口
 * 
 * @author tank
 * @date 2023-01-17
 */
public interface ICtRegionService 
{
    /**
     * 查询地区信息
     * 
     * @param id 地区信息主键
     * @return 地区信息
     */
    public CtRegion selectCtRegionById(String id);

    /**
     * 查询地区信息列表
     * 
     * @param ctRegion 地区信息
     * @return 地区信息集合
     */
    public List<CtRegion> selectCtRegionList(CtRegion ctRegion);

    /**
     * 新增地区信息
     * 
     * @param ctRegion 地区信息
     * @return 结果
     */
    public int insertCtRegion(CtRegion ctRegion);

    /**
     * 修改地区信息
     * 
     * @param ctRegion 地区信息
     * @return 结果
     */
    public int updateCtRegion(CtRegion ctRegion);

    /**
     * 批量删除地区信息
     * 
     * @param ids 需要删除的地区信息主键集合
     * @return 结果
     */
    public int deleteCtRegionByIds(String[] ids);

    /**
     * 删除地区信息信息
     * 
     * @param id 地区信息主键
     * @return 结果
     */
    public int deleteCtRegionById(String id);
}
