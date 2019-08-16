package com.example.mppapp.util

import android.app.Service
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import data.entity.Chat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ChatListRequest
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.util.log
import java.lang.Exception
import java.util.*

class AppLifeCycleObserver(private val applicationContext: Context) : LifecycleObserver {

    private val sockets = ServiceLocator.sockets
    private val scope = CoroutineScope(defaultDispatcher)
    private val getChatList = ServiceLocator.getChatList

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateApplication() {
        log("Sockets", "enter-foreground")
        try {
            val status = getConnectivityStatusString(applicationContext)
            val token = try {
                getAccessToken()
            } catch (e: Exception) {
                ""
            }
            if (token.isNotEmpty()) {
                scope.launch {
                    getChatList(
                        params = ChatListRequest(token, status != NETWORK_STATUS_NOT_CONNECTED, false),
                        onSuccess = { subscribe(it) },
                        onFailure = { log("Sockets", "SOCKETS DID NOT SUBSCRIBED ${it.toString()}")}
                    )

                }
            }
        } catch (e: Exception) {
            log("Sockets", e.message!!)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        ServiceLocator.setSocketListener(null)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDeleteApplication() {
        scope.launch {
            sockets.unsubscribe()
        }
    }

    private fun subscribe(chatList: List<Chat>) {
        scope.launch {
            sockets.subscribe(chatList)
        }
    }
}