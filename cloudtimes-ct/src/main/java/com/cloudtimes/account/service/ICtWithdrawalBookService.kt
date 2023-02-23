package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtWithdrawalBook;

/**
 * 提现登记薄Service接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface ICtWithdrawalBookService 
{
    /**
     * 查询提现登记薄
     * 
     * @param id 提现登记薄主键
     * @return 提现登记薄
     */
    public CtWithdrawalBook selectCtWithdrawalBookById(String id);

    /**
     * 查询提现登记薄列表
     * 
     * @param ctWithdrawalBook 提现登记薄
     * @return 提现登记薄集合
     */
    public List<CtWithdrawalBook> selectCtWithdrawalBookList(CtWithdrawalBook ctWithdrawalBook);

    /**
     * 新增提现登记薄
     * 
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    public int insertCtWithdrawalBook(CtWithdrawalBook ctWithdrawalBook);

    /**
     * 修改提现登记薄
     * 
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    public int updateCtWithdrawalBook(CtWithdrawalBook ctWithdrawalBook);

    /**
     * 批量删除提现登记薄
     * 
     * @param ids 需要删除的提现登记薄主键集合
     * @return 结果
     */
    public int deleteCtWithdrawalBookByIds(String[] ids);

    /**
     * 删除提现登记薄信息
     * 
     * @param id 提现登记薄主键
     * @return 结果
     */
    public int deleteCtWithdrawalBookById(String id);
}
