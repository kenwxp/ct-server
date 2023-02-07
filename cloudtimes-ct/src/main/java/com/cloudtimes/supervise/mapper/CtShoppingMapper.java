package com.cloudtimes.supervise.mapper;

import com.cloudtimes.supervise.domain.CtShopping;

import java.util.List;

/**
 * 购物Mapper接口
 *
 * @author wangxp
 * @date 2023-02-07
 */
public interface CtShoppingMapper {
    /**
     * 查询购物
     *
     * @param id 购物主键
     * @return 购物
     */
    public CtShopping selectCtShoppingById(String id);

    /**
     * 查询购物列表
     *
     * @param ctShopping 购物
     * @return 购物集合
     */
    public List<CtShopping> selectCtShoppingList(CtShopping ctShopping);

    /**
     * 新增购物
     *
     * @param ctShopping 购物
     * @return 结果
     */
    public int insertCtShopping(CtShopping ctShopping);

    /**
     * 修改购物
     *
     * @param ctShopping 购物
     * @return 结果
     */
    public int updateCtShopping(CtShopping ctShopping);

    /**
     * 删除购物
     *
     * @param id 购物主键
     * @return 结果
     */
    public int deleteCtShoppingById(String id);

    /**
     * 批量删除购物
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtShoppingByIds(String[] ids);
}
