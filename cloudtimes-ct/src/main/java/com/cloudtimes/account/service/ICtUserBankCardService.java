package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtUserBankCard;

/**
 * 用户银行卡Service接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface ICtUserBankCardService 
{
    /**
     * 查询用户银行卡
     * 
     * @param id 用户银行卡主键
     * @return 用户银行卡
     */
    public CtUserBankCard selectCtUserBankCardById(String id);

    /**
     * 查询用户银行卡列表
     * 
     * @param ctUserBankCard 用户银行卡
     * @return 用户银行卡集合
     */
    public List<CtUserBankCard> selectCtUserBankCardList(CtUserBankCard ctUserBankCard);

    /**
     * 新增用户银行卡
     * 
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    public int insertCtUserBankCard(CtUserBankCard ctUserBankCard);

    /**
     * 修改用户银行卡
     * 
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    public int updateCtUserBankCard(CtUserBankCard ctUserBankCard);

    /**
     * 批量删除用户银行卡
     * 
     * @param ids 需要删除的用户银行卡主键集合
     * @return 结果
     */
    public int deleteCtUserBankCardByIds(String[] ids);

    /**
     * 删除用户银行卡信息
     * 
     * @param id 用户银行卡主键
     * @return 结果
     */
    public int deleteCtUserBankCardById(String id);
}
