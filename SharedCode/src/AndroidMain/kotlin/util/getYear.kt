package org.kotlin.mpp.mobile.util

import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

actual val getYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)
