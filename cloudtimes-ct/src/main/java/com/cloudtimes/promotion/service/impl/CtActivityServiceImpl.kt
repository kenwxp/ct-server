package com.cloudtimes.promotion.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.promotion.domain.CtActivity
import com.cloudtimes.promotion.mapper.CtActivityMapper
import com.cloudtimes.promotion.service.ICtActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 营销活动Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtActivityServiceImpl : ICtActivityService {
    @Autowired
    private lateinit var ctActivityMapper: CtActivityMapper

    /**
     * 查询营销活动
     *
     * @param id 营销活动主键
     * @return 营销活动
     */
    override fun selectCtActivityById(id: String): CtActivity? {
        return ctActivityMapper.selectCtActivityById(id)
    }

    /**
     * 查询营销活动列表
     *
     * @param ctActivity 营销活动
     * @return 营销活动
     */
    override fun selectCtActivityList(ctActivity: CtActivity): List<CtActivity> {
        return ctActivityMapper.selectCtActivityList(ctActivity)
    }

    /**
     * 新增营销活动
     *
     * @param ctActivity 营销活动
     * @return 结果
     */
    override fun insertCtActivity(ctActivity: CtActivity): Int {
        ctActivity.createTime = DateUtils.getNowDate()
        return ctActivityMapper.insertCtActivity(ctActivity)
    }

    /**
     * 修改营销活动
     *
     * @param ctActivity 营销活动
     * @return 结果
     */
    override fun updateCtActivity(ctActivity: CtActivity): Int {
        ctActivity.updateTime = DateUtils.getNowDate()
        return ctActivityMapper.updateCtActivity(ctActivity)
    }

    /**
     * 批量删除营销活动
     *
     * @param ids 需要删除的营销活动主键
     * @return 结果
     */
    override fun deleteCtActivityByIds(ids: Array<String>): Int {
        return ctActivityMapper.deleteCtActivityByIds(ids)
    }

    /**
     * 删除营销活动信息
     *
     * @param id 营销活动主键
     * @return 结果
     */
    override fun deleteCtActivityById(id: String): Int {
        return ctActivityMapper.deleteCtActivityById(id)
    }
}
