package com.cloudtimes.promotion.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.promotion.domain.CtActivityStoreRel
import com.cloudtimes.promotion.mapper.CtActivityStoreRelMapper
import com.cloudtimes.promotion.service.ICtActivityStoreRelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 活动店铺关系Service业务层处理
 *
 * @author tank
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtActivityStoreRelServiceImpl : ICtActivityStoreRelService {
    @Autowired
    private lateinit var ctActivityStoreRelMapper: CtActivityStoreRelMapper

    /**
     * 查询活动店铺关系
     *
     * @param activityId 活动店铺关系主键
     * @return 活动店铺关系
     */
    override fun selectCtActivityStoreRelByActivityId(activityId: String): CtActivityStoreRel? {
        return ctActivityStoreRelMapper.selectCtActivityStoreRelByActivityId(activityId)
    }

    /**
     * 查询活动店铺关系列表
     *
     * @param ctActivityStoreRel 活动店铺关系
     * @return 活动店铺关系
     */
    override fun selectCtActivityStoreRelList(ctActivityStoreRel: CtActivityStoreRel): List<CtActivityStoreRel> {
        return ctActivityStoreRelMapper.selectCtActivityStoreRelList(ctActivityStoreRel)
    }

    /**
     * 新增活动店铺关系
     *
     * @param ctActivityStoreRel 活动店铺关系
     * @return 结果
     */
    override fun insertCtActivityStoreRel(ctActivityStoreRel: CtActivityStoreRel): Int {
        ctActivityStoreRel.createTime = DateUtils.getNowDate()
        return ctActivityStoreRelMapper.insertCtActivityStoreRel(ctActivityStoreRel)
    }

    /**
     * 修改活动店铺关系
     *
     * @param ctActivityStoreRel 活动店铺关系
     * @return 结果
     */
    override fun updateCtActivityStoreRel(ctActivityStoreRel: CtActivityStoreRel): Int {
        ctActivityStoreRel.updateTime = DateUtils.getNowDate()
        return ctActivityStoreRelMapper.updateCtActivityStoreRel(ctActivityStoreRel)
    }

    /**
     * 批量删除活动店铺关系
     *
     * @param activityIds 需要删除的活动店铺关系主键
     * @return 结果
     */
    override fun deleteCtActivityStoreRelByActivityIds(activityIds: Array<String>): Int {
        return ctActivityStoreRelMapper.deleteCtActivityStoreRelByActivityIds(activityIds)
    }

    /**
     * 删除活动店铺关系信息
     *
     * @param activityId 活动店铺关系主键
     * @return 结果
     */
    override fun deleteCtActivityStoreRelByActivityId(activityId: String): Int {
        return ctActivityStoreRelMapper.deleteCtActivityStoreRelByActivityId(activityId)
    }
}
