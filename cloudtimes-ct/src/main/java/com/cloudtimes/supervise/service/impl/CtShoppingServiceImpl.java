package com.cloudtimes.supervise.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import com.cloudtimes.supervise.service.ICtShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;


/**
 * 购物Service业务层处理
 * 
 * @author wangxp
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
public class CtShoppingServiceImpl implements ICtShoppingService
{
    @Autowired
    private CtShoppingMapper ctShoppingMapper;

    /**
     * 查询购物
     * 
     * @param id 购物主键
     * @return 购物
     */
    @Override
    public CtShopping selectCtShoppingById(String id)
    {
        return ctShoppingMapper.selectCtShoppingById(id);
    }

    /**
     * 查询购物列表
     * 
     * @param ctShopping 购物
     * @return 购物
     */
    @Override
    public List<CtShopping> selectCtShoppingList(CtShopping ctShopping)
    {
        return ctShoppingMapper.selectCtShoppingList(ctShopping);
    }

    /**
     * 新增购物
     * 
     * @param ctShopping 购物
     * @return 结果
     */
    @Override
    public int insertCtShopping(CtShopping ctShopping)
    {
        ctShopping.setCreateTime(DateUtils.getNowDate());
        return ctShoppingMapper.insertCtShopping(ctShopping);
    }

    /**
     * 修改购物
     * 
     * @param ctShopping 购物
     * @return 结果
     */
    @Override
    public int updateCtShopping(CtShopping ctShopping)
    {
        ctShopping.setUpdateTime(DateUtils.getNowDate());
        return ctShoppingMapper.updateCtShopping(ctShopping);
    }

    /**
     * 批量删除购物
     * 
     * @param ids 需要删除的购物主键
     * @return 结果
     */
    @Override
    public int deleteCtShoppingByIds(String[] ids)
    {
        return ctShoppingMapper.deleteCtShoppingByIds(ids);
    }

    /**
     * 删除购物信息
     * 
     * @param id 购物主键
     * @return 结果
     */
    @Override
    public int deleteCtShoppingById(String id)
    {
        return ctShoppingMapper.deleteCtShoppingById(id);
    }
}
