package com.cloudtimes.account.dto.response

import com.cloudtimes.account.domain.CtUserAssetsBook
import io.swagger.annotations.ApiModel

@ApiModel(value = "QueryAssetsBookResponse")
class QueryAssetsBookResponse : CtUserAssetsBook() {
    // :TODO: 类型为转账时增加字段
}