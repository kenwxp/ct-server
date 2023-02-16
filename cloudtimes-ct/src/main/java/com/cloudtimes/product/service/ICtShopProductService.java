package com.cloudtimes.product.service;

import com.cloudtimes.product.domain.CtShopProduct;

import java.util.List;

/**
 * 店铺商品Service接口
 * 
 * @author tank
 * @date 2023-02-15
 */
public interface ICtShopProductService 
{
    /**
     * 查询店铺商品
     * 
     * @param id 店铺商品主键
     * @return 店铺商品
     */
    public CtShopProduct selectCtShopProductById(String id);

    /**
     * 查询店铺商品列表
     * 
     * @param ctShopProduct 店铺商品
     * @return 店铺商品集合
     */
    public List<CtShopProduct> selectCtShopProductList(CtShopProduct ctShopProduct);

    /**
     * 新增店铺商品
     * 
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    public int insertCtShopProduct(CtShopProduct ctShopProduct);

    /**
     * 修改店铺商品
     * 
     * @param ctShopProduct 店铺商品
     * @return 结果
     */
    public int updateCtShopProduct(CtShopProduct ctShopProduct);

    /**
     * 批量删除店铺商品
     * 
     * @param ids 需要删除的店铺商品主键集合
     * @return 结果
     */
    public int deleteCtShopProductByIds(String[] ids);

    /**
     * 删除店铺商品信息
     * 
     * @param id 店铺商品主键
     * @return 结果
     */
    public int deleteCtShopProductById(String id);
}