package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtUserAssetsBookMapper;
import com.cloudtimes.account.domain.CtUserAssetsBook;
import com.cloudtimes.account.service.ICtUserAssetsBookService;

/**
 * 用户资产簿记Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
public class CtUserAssetsBookServiceImpl implements ICtUserAssetsBookService 
{
    @Autowired
    private CtUserAssetsBookMapper ctUserAssetsBookMapper;

    /**
     * 查询用户资产簿记
     * 
     * @param id 用户资产簿记主键
     * @return 用户资产簿记
     */
    @Override
    public CtUserAssetsBook selectCtUserAssetsBookById(String id)
    {
        return ctUserAssetsBookMapper.selectCtUserAssetsBookById(id);
    }

    /**
     * 查询用户资产簿记列表
     * 
     * @param ctUserAssetsBook 用户资产簿记
     * @return 用户资产簿记
     */
    @Override
    public List<CtUserAssetsBook> selectCtUserAssetsBookList(CtUserAssetsBook ctUserAssetsBook)
    {
        return ctUserAssetsBookMapper.selectCtUserAssetsBookList(ctUserAssetsBook);
    }

    /**
     * 新增用户资产簿记
     * 
     * @param ctUserAssetsBook 用户资产簿记
     * @return 结果
     */
    @Override
    public int insertCtUserAssetsBook(CtUserAssetsBook ctUserAssetsBook)
    {
        ctUserAssetsBook.setCreateTime(DateUtils.getNowDate());
        return ctUserAssetsBookMapper.insertCtUserAssetsBook(ctUserAssetsBook);
    }

    /**
     * 修改用户资产簿记
     * 
     * @param ctUserAssetsBook 用户资产簿记
     * @return 结果
     */
    @Override
    public int updateCtUserAssetsBook(CtUserAssetsBook ctUserAssetsBook)
    {
        ctUserAssetsBook.setUpdateTime(DateUtils.getNowDate());
        return ctUserAssetsBookMapper.updateCtUserAssetsBook(ctUserAssetsBook);
    }

    /**
     * 批量删除用户资产簿记
     * 
     * @param ids 需要删除的用户资产簿记主键
     * @return 结果
     */
    @Override
    public int deleteCtUserAssetsBookByIds(String[] ids)
    {
        return ctUserAssetsBookMapper.deleteCtUserAssetsBookByIds(ids);
    }

    /**
     * 删除用户资产簿记信息
     * 
     * @param id 用户资产簿记主键
     * @return 结果
     */
    @Override
    public int deleteCtUserAssetsBookById(String id)
    {
        return ctUserAssetsBookMapper.deleteCtUserAssetsBookById(id);
    }
}
