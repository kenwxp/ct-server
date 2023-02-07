package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtWithdrawalBookMapper;
import com.cloudtimes.account.domain.CtWithdrawalBook;
import com.cloudtimes.account.service.ICtWithdrawalBookService;

/**
 * 提现登记薄Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtWithdrawalBookServiceImpl implements ICtWithdrawalBookService 
{
    @Autowired
    private CtWithdrawalBookMapper ctWithdrawalBookMapper;

    /**
     * 查询提现登记薄
     * 
     * @param id 提现登记薄主键
     * @return 提现登记薄
     */
    @Override
    public CtWithdrawalBook selectCtWithdrawalBookById(String id)
    {
        return ctWithdrawalBookMapper.selectCtWithdrawalBookById(id);
    }

    /**
     * 查询提现登记薄列表
     * 
     * @param ctWithdrawalBook 提现登记薄
     * @return 提现登记薄
     */
    @Override
    public List<CtWithdrawalBook> selectCtWithdrawalBookList(CtWithdrawalBook ctWithdrawalBook)
    {
        return ctWithdrawalBookMapper.selectCtWithdrawalBookList(ctWithdrawalBook);
    }

    /**
     * 新增提现登记薄
     * 
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    @Override
    public int insertCtWithdrawalBook(CtWithdrawalBook ctWithdrawalBook)
    {
        ctWithdrawalBook.setCreateTime(DateUtils.getNowDate());
        return ctWithdrawalBookMapper.insertCtWithdrawalBook(ctWithdrawalBook);
    }

    /**
     * 修改提现登记薄
     * 
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    @Override
    public int updateCtWithdrawalBook(CtWithdrawalBook ctWithdrawalBook)
    {
        ctWithdrawalBook.setUpdateTime(DateUtils.getNowDate());
        return ctWithdrawalBookMapper.updateCtWithdrawalBook(ctWithdrawalBook);
    }

    /**
     * 批量删除提现登记薄
     * 
     * @param ids 需要删除的提现登记薄主键
     * @return 结果
     */
    @Override
    public int deleteCtWithdrawalBookByIds(String[] ids)
    {
        return ctWithdrawalBookMapper.deleteCtWithdrawalBookByIds(ids);
    }

    /**
     * 删除提现登记薄信息
     * 
     * @param id 提现登记薄主键
     * @return 结果
     */
    @Override
    public int deleteCtWithdrawalBookById(String id)
    {
        return ctWithdrawalBookMapper.deleteCtWithdrawalBookById(id);
    }
}
