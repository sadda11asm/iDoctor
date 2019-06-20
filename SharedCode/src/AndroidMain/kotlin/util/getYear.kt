package org.kotlin.mpp.mobile.util

import java.util.*

actual val getYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)
