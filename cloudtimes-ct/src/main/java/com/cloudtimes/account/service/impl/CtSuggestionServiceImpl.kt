package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtSuggestion
import com.cloudtimes.account.mapper.CtSuggestionMapper
import com.cloudtimes.account.service.ICtSuggestionService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 投诉建议Service业务层处理
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@DataSource(DataSourceType.CT)
@Service
class CtSuggestionServiceImpl : ICtSuggestionService {
    @Autowired
    private lateinit var ctSuggestionMapper: CtSuggestionMapper

    /**
     * 查询投诉建议
     *
     * @param id 投诉建议主键
     * @return 投诉建议
     */
    override fun selectCtSuggestionById(id: String): CtSuggestion? {
        return ctSuggestionMapper.selectCtSuggestionById(id)
    }

    /**
     * 查询投诉建议列表
     *
     * @param ctSuggestion 投诉建议
     * @return 投诉建议
     */
    override fun selectCtSuggestionList(ctSuggestion: CtSuggestion): List<CtSuggestion> {
        return ctSuggestionMapper.selectCtSuggestionList(ctSuggestion)
    }

    /**
     * 新增投诉建议
     *
     * @param ctSuggestion 投诉建议
     * @return 结果
     */
    override fun insertCtSuggestion(ctSuggestion: CtSuggestion): Int {
        ctSuggestion.createTime = DateUtils.getNowDate()
        return ctSuggestionMapper.insertCtSuggestion(ctSuggestion)
    }

    /**
     * 修改投诉建议
     *
     * @param ctSuggestion 投诉建议
     * @return 结果
     */
    override fun updateCtSuggestion(ctSuggestion: CtSuggestion): Int {
        ctSuggestion.updateTime = DateUtils.getNowDate()
        return ctSuggestionMapper.updateCtSuggestion(ctSuggestion)
    }
}