package com.cloudtimes.promotion.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.promotion.domain.CtUserDrawRel
import com.cloudtimes.promotion.mapper.CtUserDrawRelMapper
import com.cloudtimes.promotion.service.ICtUserDrawRelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户中奖Service业务层处理
 *
 * @author tank
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtUserDrawRelServiceImpl : ICtUserDrawRelService {
    @Autowired
    private lateinit var ctUserDrawRelMapper: CtUserDrawRelMapper

    /**
     * 查询用户中奖
     *
     * @param userId 用户中奖主键
     * @return 用户中奖
     */
    override fun selectCtUserDrawRelByUserId(userId: String): CtUserDrawRel? {
        return ctUserDrawRelMapper.selectCtUserDrawRelByUserId(userId)
    }

    /**
     * 查询用户中奖列表
     *
     * @param ctUserDrawRel 用户中奖
     * @return 用户中奖
     */
    override fun selectCtUserDrawRelList(ctUserDrawRel: CtUserDrawRel): List<CtUserDrawRel> {
        return ctUserDrawRelMapper.selectCtUserDrawRelList(ctUserDrawRel)
    }

    /**
     * 新增用户中奖
     *
     * @param ctUserDrawRel 用户中奖
     * @return 结果
     */
    override fun insertCtUserDrawRel(ctUserDrawRel: CtUserDrawRel): Int {
        ctUserDrawRel.createTime = DateUtils.getNowDate()
        return ctUserDrawRelMapper.insertCtUserDrawRel(ctUserDrawRel)
    }

    /**
     * 修改用户中奖
     *
     * @param ctUserDrawRel 用户中奖
     * @return 结果
     */
    override fun updateCtUserDrawRel(ctUserDrawRel: CtUserDrawRel): Int {
        ctUserDrawRel.updateTime = DateUtils.getNowDate()
        return ctUserDrawRelMapper.updateCtUserDrawRel(ctUserDrawRel)
    }

    /**
     * 批量删除用户中奖
     *
     * @param userIds 需要删除的用户中奖主键
     * @return 结果
     */
    override fun deleteCtUserDrawRelByUserIds(userIds: Array<String>): Int {
        return ctUserDrawRelMapper.deleteCtUserDrawRelByUserIds(userIds)
    }

    /**
     * 删除用户中奖信息
     *
     * @param userId 用户中奖主键
     * @return 结果
     */
    override fun deleteCtUserDrawRelByUserId(userId: String): Int {
        return ctUserDrawRelMapper.deleteCtUserDrawRelByUserId(userId)
    }
}
