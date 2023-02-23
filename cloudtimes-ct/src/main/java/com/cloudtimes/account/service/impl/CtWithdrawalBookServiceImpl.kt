package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtWithdrawalBook
import com.cloudtimes.account.dto.request.QueryAgentWithdrawalRequest
import com.cloudtimes.account.mapper.CtWithdrawalBookMapper
import com.cloudtimes.account.mapper.provider.CtWithdrawalBookProvider
import com.cloudtimes.account.service.ICtWithdrawalBookService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 提现登记薄Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
class CtWithdrawalBookServiceImpl : ICtWithdrawalBookService {
    @Autowired
    private lateinit var ctWithdrawalBookMapper: CtWithdrawalBookMapper


    /** 查询代理提现记录 */
    override fun selectAgentWithdrawalList(request: QueryAgentWithdrawalRequest): List<CtWithdrawalBook> {
        return ctWithdrawalBookMapper.selectMany(
            CtWithdrawalBookProvider.selectAgentWithdrawList(request)
        )
    }


    /**
     * 查询提现登记薄
     *
     * @param id 提现登记薄主键
     * @return 提现登记薄
     */
    override fun selectCtWithdrawalBookById(id: String): CtWithdrawalBook? {
        return ctWithdrawalBookMapper.selectCtWithdrawalBookById(id)
    }

    /**
     * 查询提现登记薄列表
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 提现登记薄
     */
    override fun selectCtWithdrawalBookList(ctWithdrawalBook: CtWithdrawalBook): List<CtWithdrawalBook> {
        return ctWithdrawalBookMapper.selectCtWithdrawalBookList(ctWithdrawalBook)
    }

    /**
     * 新增提现登记薄
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    override fun insertCtWithdrawalBook(ctWithdrawalBook: CtWithdrawalBook): Int {
        ctWithdrawalBook.createTime = DateUtils.getNowDate()
        return ctWithdrawalBookMapper.insertCtWithdrawalBook(ctWithdrawalBook)
    }

    /**
     * 修改提现登记薄
     *
     * @param ctWithdrawalBook 提现登记薄
     * @return 结果
     */
    override fun updateCtWithdrawalBook(ctWithdrawalBook: CtWithdrawalBook): Int {
        ctWithdrawalBook.updateTime = DateUtils.getNowDate()
        return ctWithdrawalBookMapper.updateCtWithdrawalBook(ctWithdrawalBook)
    }

    /**
     * 批量删除提现登记薄
     *
     * @param ids 需要删除的提现登记薄主键
     * @return 结果
     */
    override fun deleteCtWithdrawalBookByIds(ids: Array<String>): Int {
        return ctWithdrawalBookMapper.deleteCtWithdrawalBookByIds(ids)
    }

    /**
     * 删除提现登记薄信息
     *
     * @param id 提现登记薄主键
     * @return 结果
     */
    override fun deleteCtWithdrawalBookById(id: String): Int {
        return ctWithdrawalBookMapper.deleteCtWithdrawalBookById(id)
    }
}