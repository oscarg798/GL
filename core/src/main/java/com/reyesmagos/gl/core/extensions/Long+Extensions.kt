package com.reyesmagos.gl.core.extensions

import java.util.*

fun Long.toPostDateFormat(): String{
    return Calendar.getInstance().apply {
        timeInMillis = this@toPostDateFormat* 1000
    }.toPostDateFormat()
}
