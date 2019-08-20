package com.example.mppapp.ui

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.mppapp.util.AppLifeCycleObserver
import com.facebook.stetho.Stetho
import com.orhanobut.hawk.Hawk
import org.kotlin.mpp.mobile.appContext
import android.content.IntentFilter
import com.example.mppapp.util.NetworkBroadcastReceiver


class iDoctorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this

        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        this.registerReceiver(NetworkBroadcastReceiver(), intentFilter)

        val lifecycleObserver = AppLifeCycleObserver(applicationContext)

        //ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleObserver)

        Stetho.initializeWithDefaults(this)

        Hawk.init(this).build()
    }
}