package com.cloudtimes.stats.dto.response

import java.math.BigDecimal

class CommissionAndDividend {
    /* 已结算佣金 */
    var historyCommission: BigDecimal = BigDecimal.ZERO

    /* 已结算分润 */
    val historyDividend: BigDecimal = BigDecimal.ZERO

    /* 待结算分润 */
    val futureDividend: BigDecimal = BigDecimal.ZERO

    /* 待结算佣金 */
    var futureCommission: BigDecimal = BigDecimal.ZERO
}