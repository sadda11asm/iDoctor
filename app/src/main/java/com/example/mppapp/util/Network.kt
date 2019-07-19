package com.example.mppapp.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentActivity

fun getNetworkConnection(activity: FragmentActivity?): Boolean {
    return verifyAvailableNetwork(activity)
}

private fun verifyAvailableNetwork(activity: FragmentActivity?): Boolean {
    val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}