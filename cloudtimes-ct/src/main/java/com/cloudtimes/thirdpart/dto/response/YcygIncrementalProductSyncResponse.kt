package com.cloudtimes.thirdpart.dto.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "YcygIncrementalProductSyncResponse", description = "增量商品同步应答")
class YcygIncrementalProductSyncResponse {
    @ApiModelProperty(value = "返回结果码")
    var code = 0

    @ApiModelProperty(value = "返回结果说明")
    var message = "入库成功"
}