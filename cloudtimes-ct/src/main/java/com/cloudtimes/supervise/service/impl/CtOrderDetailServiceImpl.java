package com.cloudtimes.supervise.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.mapper.CtOrderDetailMapper;
import com.cloudtimes.supervise.service.ICtOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;


/**
 * 订单物品清单Service业务层处理
 * 
 * @author tank
 * @date 2023-02-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtOrderDetailServiceImpl implements ICtOrderDetailService
{
    @Autowired
    private CtOrderDetailMapper ctOrderDetailMapper;

    /**
     * 查询订单物品清单
     * 
     * @param id 订单物品清单主键
     * @return 订单物品清单
     */
    @Override
    public CtOrderDetail selectCtOrderDetailById(String id)
    {
        return ctOrderDetailMapper.selectCtOrderDetailById(id);
    }

    /**
     * 查询订单物品清单列表
     * 
     * @param ctOrderDetail 订单物品清单
     * @return 订单物品清单
     */
    @Override
    public List<CtOrderDetail> selectCtOrderDetailList(CtOrderDetail ctOrderDetail)
    {
        return ctOrderDetailMapper.selectCtOrderDetailList(ctOrderDetail);
    }

    /**
     * 新增订单物品清单
     * 
     * @param ctOrderDetail 订单物品清单
     * @return 结果
     */
    @Override
    public int insertCtOrderDetail(CtOrderDetail ctOrderDetail)
    {
        ctOrderDetail.setCreateTime(DateUtils.getNowDate());
        return ctOrderDetailMapper.insertCtOrderDetail(ctOrderDetail);
    }

    /**
     * 修改订单物品清单
     * 
     * @param ctOrderDetail 订单物品清单
     * @return 结果
     */
    @Override
    public int updateCtOrderDetail(CtOrderDetail ctOrderDetail)
    {
        ctOrderDetail.setUpdateTime(DateUtils.getNowDate());
        return ctOrderDetailMapper.updateCtOrderDetail(ctOrderDetail);
    }

    /**
     * 批量删除订单物品清单
     * 
     * @param ids 需要删除的订单物品清单主键
     * @return 结果
     */
    @Override
    public int deleteCtOrderDetailByIds(String[] ids)
    {
        return ctOrderDetailMapper.deleteCtOrderDetailByIds(ids);
    }

    /**
     * 删除订单物品清单信息
     * 
     * @param id 订单物品清单主键
     * @return 结果
     */
    @Override
    public int deleteCtOrderDetailById(String id)
    {
        return ctOrderDetailMapper.deleteCtOrderDetailById(id);
    }
}
