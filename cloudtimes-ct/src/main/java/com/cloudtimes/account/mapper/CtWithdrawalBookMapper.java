package com.cloudtimes.account.mapper;

import java.util.List;
import com.cloudtimes.account.domain.CtWithdrawalBook;

/**
 * 提现登记薄Mapper接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface CtWithdrawalBookMapper 
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
     * 删除提现登记薄
     * 
     * @param id 提现登记薄主键
     * @return 结果
     */
    public int deleteCtWithdrawalBookById(String id);

    /**
     * 批量删除提现登记薄
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtWithdrawalBookByIds(String[] ids);
}
