package com.reyesmagos.gl.core.extensions

import com.reyesmagos.gl.core.constants.DATE_FORMAT
import com.reyesmagos.gl.core.constants.POST_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.parseToDateFormat(): String {
    return SimpleDateFormat(DATE_FORMAT,Locale.getDefault()).format(time)
}

fun Calendar.toPostDateFormat(): String {
    return SimpleDateFormat(POST_DATE_FORMAT,Locale.getDefault()).format(time)
}
