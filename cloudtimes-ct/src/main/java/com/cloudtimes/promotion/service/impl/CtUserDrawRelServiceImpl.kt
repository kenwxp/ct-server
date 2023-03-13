package com.cloudtimes.promotion.service.impl

import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.promotion.domain.CtLuckyDrawRule
import com.cloudtimes.promotion.domain.CtUserDrawRel
import com.cloudtimes.promotion.mapper.CtUserDrawRelMapper
import com.cloudtimes.promotion.mapper.provider.CtUserDrawRelProvider
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


    /* 查询用户中奖规则 */
    override fun selectCtLuckyDrawRuleById(activityId: String, userId: String): CtLuckyDrawRule? {
        return ctUserDrawRelMapper.selectOneDrawRule(
            CtUserDrawRelProvider.findDrawRuleByIdStmt(activityId, userId)
        )
    }

    /**
     * 查询用户中奖
     *
     * @param userId 用户中奖主键
     * @return 用户中奖
     */
    override fun selectCtUserDrawRelById(activityId: String, userId: String): CtUserDrawRel? {
        return ctUserDrawRelMapper.selectOne(
            CtUserDrawRelProvider.findDrawByIdStmt(activityId, userId)
        )
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
        return ctUserDrawRelMapper.generalInsert(
            CtUserDrawRelProvider.newDrawStmt(ctUserDrawRel)
        )
    }
}
