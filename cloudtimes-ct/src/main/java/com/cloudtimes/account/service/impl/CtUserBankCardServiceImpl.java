package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtUserBankCardMapper;
import com.cloudtimes.account.domain.CtUserBankCard;
import com.cloudtimes.account.service.ICtUserBankCardService;

/**
 * 用户银行卡Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtUserBankCardServiceImpl implements ICtUserBankCardService 
{
    @Autowired
    private CtUserBankCardMapper ctUserBankCardMapper;

    /**
     * 查询用户银行卡
     * 
     * @param id 用户银行卡主键
     * @return 用户银行卡
     */
    @Override
    public CtUserBankCard selectCtUserBankCardById(String id)
    {
        return ctUserBankCardMapper.selectCtUserBankCardById(id);
    }

    /**
     * 查询用户银行卡列表
     * 
     * @param ctUserBankCard 用户银行卡
     * @return 用户银行卡
     */
    @Override
    public List<CtUserBankCard> selectCtUserBankCardList(CtUserBankCard ctUserBankCard)
    {
        return ctUserBankCardMapper.selectCtUserBankCardList(ctUserBankCard);
    }

    /**
     * 新增用户银行卡
     * 
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    @Override
    public int insertCtUserBankCard(CtUserBankCard ctUserBankCard)
    {
        ctUserBankCard.setCreateTime(DateUtils.getNowDate());
        return ctUserBankCardMapper.insertCtUserBankCard(ctUserBankCard);
    }

    /**
     * 修改用户银行卡
     * 
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    @Override
    public int updateCtUserBankCard(CtUserBankCard ctUserBankCard)
    {
        ctUserBankCard.setUpdateTime(DateUtils.getNowDate());
        return ctUserBankCardMapper.updateCtUserBankCard(ctUserBankCard);
    }

    /**
     * 批量删除用户银行卡
     * 
     * @param ids 需要删除的用户银行卡主键
     * @return 结果
     */
    @Override
    public int deleteCtUserBankCardByIds(String[] ids)
    {
        return ctUserBankCardMapper.deleteCtUserBankCardByIds(ids);
    }

    /**
     * 删除用户银行卡信息
     * 
     * @param id 用户银行卡主键
     * @return 结果
     */
    @Override
    public int deleteCtUserBankCardById(String id)
    {
        return ctUserBankCardMapper.deleteCtUserBankCardById(id);
    }
}
