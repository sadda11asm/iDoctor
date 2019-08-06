package com.example.mppapp.util

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.domain.defaultDispatcher


class NetworkBroadcastReceiver : BroadcastReceiver() {

    private val sockets = ServiceLocator.sockets
    private val scope = CoroutineScope(defaultDispatcher)



    override fun onReceive(context: Context, intent: Intent) {

        val status = getConnectivityStatusString(context)
        Log.e("receiver", "r")
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.action) {
            if (status == NETWORK_STATUS_NOT_CONNECTED) {
                scope.launch {
                    sockets.unsubscribe()
                }
            } else {
                scope.launch {
                    sockets.subscribe()
                }
            }
        }
    }
}