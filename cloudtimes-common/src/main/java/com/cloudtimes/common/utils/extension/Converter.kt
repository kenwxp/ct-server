package com.cloudtimes.common.utils.extension

import java.time.LocalDate

fun String?.toInt(): Int? =
    if (! this.isNullOrEmpty() ) {
        Integer.parseInt(this)
    } else {
        null
    }

fun String?.likeWith(): String? =
    if (! this.isNullOrEmpty() ) {
        "%$this%"
    } else {
        null
    }

fun String?.beginLikeWith(): String? =
    if (! this.isNullOrEmpty() ) {
        "$this%"
    } else {
        null
    }

fun String?.valueOrNull(): String? =
    if (! this.isNullOrEmpty() ) {
        this
    } else {
        null
    }

fun String?.endLikeWith(): String? =
    if (! this.isNullOrEmpty() ) {
        "%$this"
    } else {
        null
    }

fun LocalDate.toYearMonth(): Int = year * 100 + monthValue
