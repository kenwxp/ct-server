package com.cloudtimes.account.mapper;

import java.util.List;
import com.cloudtimes.account.domain.CtUserAssetsBook;

/**
 * 用户资产簿记Mapper接口
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
public interface CtUserAssetsBookMapper 
{
    /**
     * 查询用户资产簿记
     * 
     * @param id 用户资产簿记主键
     * @return 用户资产簿记
     */
    public CtUserAssetsBook selectCtUserAssetsBookById(String id);

    /**
     * 查询用户资产簿记列表
     * 
     * @param ctUserAssetsBook 用户资产簿记
     * @return 用户资产簿记集合
     */
    public List<CtUserAssetsBook> selectCtUserAssetsBookList(CtUserAssetsBook ctUserAssetsBook);

    /**
     * 新增用户资产簿记
     * 
     * @param ctUserAssetsBook 用户资产簿记
     * @return 结果
     */
    public int insertCtUserAssetsBook(CtUserAssetsBook ctUserAssetsBook);

    /**
     * 修改用户资产簿记
     * 
     * @param ctUserAssetsBook 用户资产簿记
     * @return 结果
     */
    public int updateCtUserAssetsBook(CtUserAssetsBook ctUserAssetsBook);

    /**
     * 删除用户资产簿记
     * 
     * @param id 用户资产簿记主键
     * @return 结果
     */
    public int deleteCtUserAssetsBookById(String id);

    /**
     * 批量删除用户资产簿记
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtUserAssetsBookByIds(String[] ids);
}
