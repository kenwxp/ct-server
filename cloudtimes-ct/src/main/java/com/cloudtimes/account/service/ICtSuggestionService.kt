package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtSuggestion

/**
 * 投诉建议Service接口
 *
 * @author 沈兵
 * @date 2023-02-14
 */
interface ICtSuggestionService {
    /**
     * 查询投诉建议
     *
     * @param id 投诉建议主键
     * @return 投诉建议
     */
    fun selectCtSuggestionById(id: String): CtSuggestion?

    /**
     * 查询投诉建议列表
     *
     * @param ctSuggestion 投诉建议
     * @return 投诉建议集合
     */
    fun selectCtSuggestionList(ctSuggestion: CtSuggestion): List<CtSuggestion>

    /**
     * 新增投诉建议
     *
     * @param ctSuggestion 投诉建议
     * @return 结果
     */
    fun insertCtSuggestion(ctSuggestion: CtSuggestion): Int

    /**
     * 修改投诉建议
     *
     * @param ctSuggestion 投诉建议
     * @return 结果
     */
    fun updateCtSuggestion(ctSuggestion: CtSuggestion): Int
}