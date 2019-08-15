package com.example.mppapp.util

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.util.log
import java.lang.Exception
import java.util.*

class AppLifeCycleObserver(private val applicationContext: Context) : LifecycleObserver {

    private val sockets = ServiceLocator.sockets
    private val scope = CoroutineScope(defaultDispatcher)
    private val getUserInfo = ServiceLocator.getUserInfo

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
        log("Sockets", "enter-foreground")
        try {
            val status = getConnectivityStatusString(applicationContext)
            if (status != NETWORK_STATUS_NOT_CONNECTED)
                scope.launch {
                    sockets.subscribe()
                }
        } catch (e: Exception) {
            log("Sockets", e.message!!)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateForeground() {
        lateinit var token: String
        try {
            getAccessToken()
        } catch (e: Exception) {
            return
        }
        if(token.isNotEmpty()) {
            scope.launch {
                getUserInfo(
                    params = getAccessToken(),
                    onSuccess = {},
                    onFailure = {}
                )
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        scope.launch {
            sockets.unsubscribe()
        }
    }
}