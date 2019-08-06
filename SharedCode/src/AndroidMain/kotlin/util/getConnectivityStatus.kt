package org.kotlin.mpp.mobile.util

import android.content.Context
import android.net.ConnectivityManager
import org.kotlin.mpp.mobile.appContext

actual fun isConnected(): Boolean {
    val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    if (null != activeNetwork) {
        return true
    }
    return false
}