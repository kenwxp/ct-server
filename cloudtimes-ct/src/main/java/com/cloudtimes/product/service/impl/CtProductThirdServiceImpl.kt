package com.cloudtimes.product.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.mapper.CtProductThirdMapper
import com.cloudtimes.product.service.ICtProductThirdService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 第三方商品Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-05
 */
@DataSource(DataSourceType.CT)
@Service
class CtProductThirdServiceImpl : ICtProductThirdService {
    @Autowired
    private lateinit var ctProductThirdMapper: CtProductThirdMapper

    /**
     * 查询第三方商品
     *
     * @param thirdCode 第三方商品主键
     * @return 第三方商品
     */
    override fun selectCtProductThirdByThirdCode(thirdCode: String): CtProductThird? {
        return ctProductThirdMapper.selectCtProductThirdByThirdCode(thirdCode)
    }

    /**
     * 查询第三方商品列表
     *
     * @param ctProductThird 第三方商品
     * @return 第三方商品
     */
    override fun selectCtProductThirdList(ctProductThird: CtProductThird): List<CtProductThird> {
        return ctProductThirdMapper.selectCtProductThirdList(ctProductThird)
    }

    /**
     * 新增第三方商品
     *
     * @param ctProductThird 第三方商品
     * @return 结果
     */
    override fun insertCtProductThird(ctProductThird: CtProductThird): Int {
        ctProductThird.createTime = DateUtils.getNowDate()
        return ctProductThirdMapper.insertCtProductThird(ctProductThird)
    }

    /**
     * 修改第三方商品
     *
     * @param ctProductThird 第三方商品
     * @return 结果
     */
    override fun updateCtProductThird(ctProductThird: CtProductThird): Int {
        ctProductThird.updateTime = DateUtils.getNowDate()
        return ctProductThirdMapper.updateCtProductThird(ctProductThird)
    }

    /**
     * 批量删除第三方商品
     *
     * @param thirdCodes 需要删除的第三方商品主键
     * @return 结果
     */
    override fun deleteCtProductThirdByThirdCodes(thirdCodes: Array<String>): Int {
        return ctProductThirdMapper.deleteCtProductThirdByThirdCodes(thirdCodes)
    }

    /**
     * 删除第三方商品信息
     *
     * @param thirdCode 第三方商品主键
     * @return 结果
     */
    override fun deleteCtProductThirdByThirdCode(thirdCode: String): Int {
        return ctProductThirdMapper.deleteCtProductThirdByThirdCode(thirdCode)
    }
}