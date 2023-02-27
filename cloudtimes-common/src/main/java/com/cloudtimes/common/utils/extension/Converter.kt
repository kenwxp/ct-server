package com.cloudtimes.common.utils.extension

import java.time.LocalDate

fun String?.toInt(): Int? =
    if (! this.isNullOrEmpty() ) {
        Integer.parseInt(this)
    } else {
        null
    }

fun LocalDate.toYearMonth(): Int = year * 100 + monthValue
