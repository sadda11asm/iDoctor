package com.example.mppapp.util

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkInfo
import data.entity.Chat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ChatListRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import java.lang.Exception


class NetworkBroadcastReceiver : BroadcastReceiver() {

    private val sockets = ServiceLocator.sockets
    private val scope = CoroutineScope(defaultDispatcher)
    private val getChatList = ServiceLocator.getChatList



    override fun onReceive(context: Context, intent: Intent) {

        val status = getConnectivityStatusString(context)
        Log.e("receiver", "r")
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.action) {
            if (status == NETWORK_STATUS_NOT_CONNECTED) {
                scope.launch {
                    sockets.unsubscribe()
                }
            } else {
                val token = try {
                    getAccessToken()
                } catch (e: Exception) {
                    ""
                }
                if (token.isNotEmpty()) {
                    scope.launch {
                        getChatList(
                            params = ChatListRequest(token, true, false),
                            onSuccess = { subscribe(it) },
                            onFailure = {}
                        )

                    }
                }
            }
        }
    }

    private fun subscribe(chatList: List<Chat>) {
        scope.launch {
            sockets.subscribe(chatList)
        }
    }
}