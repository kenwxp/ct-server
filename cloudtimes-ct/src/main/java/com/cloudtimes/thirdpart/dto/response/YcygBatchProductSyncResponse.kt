package com.cloudtimes.thirdpart.dto.response

import com.cloudtimes.thirdpart.dto.request.RcygProductRecord
import io.swagger.annotations.ApiModel

@ApiModel(value = "YcygBatchProductSyncResponseData", description = "批量商品同步应答数据")
class YcygBatchProductSyncResponseData {
    var hasNextPage: Boolean = false
    var hasPreviousPage: Boolean = false
    var isFirstPage: Boolean = false
    var isLastPage: Boolean = false
    var nextPage: Int = 0
    var pageNum: Int = 0
    var pageSize: Int = 0
    var pages: Int = 0
    var prePage: Int = 0
    var size: Int = 0
    var total: Int = 0

    var list: List<RcygProductRecord>? = emptyList()

    override fun toString(): String {
        return "YcygBatchProductSyncResponseData(hasNextPage=$hasNextPage, hasPreviousPage=$hasPreviousPage, isFirstPage=$isFirstPage, isLastPage=$isLastPage, nextPage=$nextPage, pageNum=$pageNum, pageSize=$pageSize, pages=$pages, prePage=$prePage, size=$size, total=$total, list=$list)"
    }


}

@ApiModel(value = "YcygBatchProductSyncResponse", description = "批量商品同步应答")
class YcygBatchProductSyncResponse {
    var code: Int = 0
    var msg: String = ""
    var data: YcygBatchProductSyncResponseData? = null

    override fun toString(): String {
        return "YcygBatchProductSyncResponse(code=$code, msg='$msg', data=$data)"
    }
}