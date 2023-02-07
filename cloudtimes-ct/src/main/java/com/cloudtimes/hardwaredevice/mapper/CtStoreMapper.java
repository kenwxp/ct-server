package com.cloudtimes.hardwaredevice.mapper;

import java.util.List;
import com.cloudtimes.hardwaredevice.domain.CtStore;

/**
 * 门店Mapper接口
 * 
 * @author tank
 * @date 2023-01-17
 */
public interface CtStoreMapper 
{
    /**
     * 查询门店
     * 
     * @param id 门店主键
     * @return 门店
     */
    public CtStore selectCtStoreById(String id);/**
     * 查询门店
     *
     * @param storeNo 门店号
     * @return 门店
     */
    public CtStore selectCtStoreByStoreNo(String storeNo);

    /**
     * 查询门店列表
     * 
     * @param ctStore 门店
     * @return 门店集合
     */
    public List<CtStore> selectCtStoreList(CtStore ctStore);

    /**
     * 新增门店
     * 
     * @param ctStore 门店
     * @return 结果
     */
    public int insertCtStore(CtStore ctStore);

    /**
     * 修改门店
     * 
     * @param ctStore 门店
     * @return 结果
     */
    public int updateCtStore(CtStore ctStore);

    /**
     * 删除门店
     * 
     * @param id 门店主键
     * @return 结果
     */
    public int deleteCtStoreById(String id);

    /**
     * 批量删除门店
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtStoreByIds(String[] ids);
}
