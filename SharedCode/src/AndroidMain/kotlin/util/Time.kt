package org.kotlin.mpp.mobile.util

import java.text.SimpleDateFormat
import java.util.*

actual val getYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)

actual val currentTime: String
    get() {
        // TODO better implementation?
        val pattern = "yyyy-MM-dd hh:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
        return simpleDateFormat.format(Date())
    }
