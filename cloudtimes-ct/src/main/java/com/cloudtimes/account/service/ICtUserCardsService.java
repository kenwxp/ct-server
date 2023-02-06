package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtUserCards;

/**
 * 卡劵维护Service接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface ICtUserCardsService 
{
    /**
     * 查询卡劵维护
     * 
     * @param id 卡劵维护主键
     * @return 卡劵维护
     */
    public CtUserCards selectCtUserCardsById(String id);

    /**
     * 查询卡劵维护列表
     * 
     * @param ctUserCards 卡劵维护
     * @return 卡劵维护集合
     */
    public List<CtUserCards> selectCtUserCardsList(CtUserCards ctUserCards);

    /**
     * 新增卡劵维护
     * 
     * @param ctUserCards 卡劵维护
     * @return 结果
     */
    public int insertCtUserCards(CtUserCards ctUserCards);

    /**
     * 修改卡劵维护
     * 
     * @param ctUserCards 卡劵维护
     * @return 结果
     */
    public int updateCtUserCards(CtUserCards ctUserCards);

    /**
     * 批量删除卡劵维护
     * 
     * @param ids 需要删除的卡劵维护主键集合
     * @return 结果
     */
    public int deleteCtUserCardsByIds(String[] ids);

    /**
     * 删除卡劵维护信息
     * 
     * @param id 卡劵维护主键
     * @return 结果
     */
    public int deleteCtUserCardsById(String id);
}
