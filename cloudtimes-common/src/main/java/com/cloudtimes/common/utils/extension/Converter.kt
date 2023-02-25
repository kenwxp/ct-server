package com.cloudtimes.common.utils.extension

fun String?.toInt(): Int? =
    if (! this.isNullOrEmpty() ) {
        Integer.parseInt(this)
    } else {
        null
    }