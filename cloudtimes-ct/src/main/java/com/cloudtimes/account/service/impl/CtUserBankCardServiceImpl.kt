package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtUserBankCard
import com.cloudtimes.account.mapper.CtUserBankCardMapper
import com.cloudtimes.account.service.ICtUserBankCardService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户银行卡Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class CtUserBankCardServiceImpl : ICtUserBankCardService {
    @Autowired
    private lateinit var ctUserBankCardMapper: CtUserBankCardMapper

    /**
     * 查询用户银行卡
     *
     * @param id 用户银行卡主键
     * @return 用户银行卡
     */
    override fun selectCtUserBankCardById(id: String): CtUserBankCard? {
        return ctUserBankCardMapper.selectCtUserBankCardById(id)
    }

    /**
     * 查询用户银行卡
     *
     * @param userId 用户银行卡主键
     * @return 用户银行卡
     */
    override fun selectCtUserBankCardByUserId(userId: String): CtUserBankCard? {
        return ctUserBankCardMapper.selectCtUserBankCardByUserId(userId)
    }

    /**
     * 查询用户银行卡列表
     *
     * @param ctUserBankCard 用户银行卡
     * @return 用户银行卡
     */
    override fun selectCtUserBankCardList(ctUserBankCard: CtUserBankCard): List<CtUserBankCard> {
        return ctUserBankCardMapper.selectCtUserBankCardList(ctUserBankCard)
    }

    /**
     * 新增用户银行卡
     *
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    override fun insertCtUserBankCard(ctUserBankCard: CtUserBankCard): CtUserBankCard? {
        ctUserBankCard.createTime = DateUtils.getNowDate()
        return ctUserBankCardMapper.insertCtUserBankCard(ctUserBankCard)
    }

    /**
     * 修改用户银行卡
     *
     * @param ctUserBankCard 用户银行卡
     * @return 结果
     */
    override fun updateCtUserBankCard(ctUserBankCard: CtUserBankCard): Int {
        ctUserBankCard.updateTime = DateUtils.getNowDate()
        return ctUserBankCardMapper.updateCtUserBankCard(ctUserBankCard)
    }

    /**
     * 批量删除用户银行卡
     *
     * @param ids 需要删除的用户银行卡主键
     * @return 结果
     */
    override fun deleteCtUserBankCardByIds(ids: Array<String>): Int {
        return ctUserBankCardMapper.deleteCtUserBankCardByIds(ids)
    }

    /**
     * 删除用户银行卡信息
     *
     * @param id 用户银行卡主键
     * @return 结果
     */
    override fun deleteCtUserBankCardById(id: String): Int {
        return ctUserBankCardMapper.deleteCtUserBankCardById(id)
    }
}