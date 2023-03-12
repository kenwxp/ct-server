package com.cloudtimes.thirdpart.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "增量商品同步应答")
class YcygIncrementalProductSyncResponse {
    @Schema(description = "返回结果码")
    var code = 0

    @Schema(description = "返回结果说明")
    var message = "入库成功"
}
