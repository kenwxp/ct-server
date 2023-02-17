package com.cloudtimes.supervise.mapper;

import com.cloudtimes.supervise.domain.CtOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单物品清单Mapper接口
 * 
 * @author tank
 * @date 2023-02-17
 */
public interface CtOrderDetailMapper 
{
    /**
     * 查询订单物品清单
     * 
     * @param id 订单物品清单主键
     * @return 订单物品清单
     */
    public CtOrderDetail selectCtOrderDetailById(String id);

    /**
     * 查询订单物品清单列表
     * 
     * @param ctOrderDetail 订单物品清单
     * @return 订单物品清单集合
     */
    public List<CtOrderDetail> selectCtOrderDetailList(CtOrderDetail ctOrderDetail);

    /**
     * 新增订单物品清单
     * 
     * @param ctOrderDetail 订单物品清单
     * @return 结果
     */
    public int insertCtOrderDetail(CtOrderDetail ctOrderDetail);

    /**
     * 修改订单物品清单
     * 
     * @param ctOrderDetail 订单物品清单
     * @return 结果
     */
    public int updateCtOrderDetail(CtOrderDetail ctOrderDetail);

    /**
     * 删除订单物品清单
     * 
     * @param id 订单物品清单主键
     * @return 结果
     */
    public int deleteCtOrderDetailById(String id);

    /**
     * 批量删除订单物品清单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtOrderDetailByIds(String[] ids);


    /**
     * 根据任务状态或订单查询订单物品清单
     *
     * @param orderId
     * @param taskState
     * @return
     */
    public List<CtOrderDetail> selectCtOrderDetailListByTaskOrOrder(@Param("orderId") String orderId, @Param("taskState") String taskState);

}
