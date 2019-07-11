package org.kotlin.mpp.mobile.util

import android.util.Log


actual fun log(name: String, value: String) {
    Log.v(name, value)
}