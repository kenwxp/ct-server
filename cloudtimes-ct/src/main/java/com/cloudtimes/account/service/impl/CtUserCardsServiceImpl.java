package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtUserCardsMapper;
import com.cloudtimes.account.domain.CtUserCards;
import com.cloudtimes.account.service.ICtUserCardsService;

/**
 * 卡劵维护Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtUserCardsServiceImpl implements ICtUserCardsService 
{
    @Autowired
    private CtUserCardsMapper ctUserCardsMapper;

    /**
     * 查询卡劵维护
     * 
     * @param id 卡劵维护主键
     * @return 卡劵维护
     */
    @Override
    public CtUserCards selectCtUserCardsById(String id)
    {
        return ctUserCardsMapper.selectCtUserCardsById(id);
    }

    /**
     * 查询卡劵维护列表
     * 
     * @param ctUserCards 卡劵维护
     * @return 卡劵维护
     */
    @Override
    public List<CtUserCards> selectCtUserCardsList(CtUserCards ctUserCards)
    {
        return ctUserCardsMapper.selectCtUserCardsList(ctUserCards);
    }

    /**
     * 新增卡劵维护
     * 
     * @param ctUserCards 卡劵维护
     * @return 结果
     */
    @Override
    public int insertCtUserCards(CtUserCards ctUserCards)
    {
        ctUserCards.setCreateTime(DateUtils.getNowDate());
        return ctUserCardsMapper.insertCtUserCards(ctUserCards);
    }

    /**
     * 修改卡劵维护
     * 
     * @param ctUserCards 卡劵维护
     * @return 结果
     */
    @Override
    public int updateCtUserCards(CtUserCards ctUserCards)
    {
        ctUserCards.setUpdateTime(DateUtils.getNowDate());
        return ctUserCardsMapper.updateCtUserCards(ctUserCards);
    }

    /**
     * 批量删除卡劵维护
     * 
     * @param ids 需要删除的卡劵维护主键
     * @return 结果
     */
    @Override
    public int deleteCtUserCardsByIds(String[] ids)
    {
        return ctUserCardsMapper.deleteCtUserCardsByIds(ids);
    }

    /**
     * 删除卡劵维护信息
     * 
     * @param id 卡劵维护主键
     * @return 结果
     */
    @Override
    public int deleteCtUserCardsById(String id)
    {
        return ctUserCardsMapper.deleteCtUserCardsById(id);
    }
}
