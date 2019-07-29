package com.example.mppapp.ui

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.mppapp.util.AppLifeCycleObserver
import com.facebook.stetho.Stetho
import com.orhanobut.hawk.Hawk
import org.kotlin.mpp.mobile.appContext

class iDoctorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = this

        val lifecycleObserver =  AppLifeCycleObserver(applicationContext)

        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleObserver)

        Stetho.initializeWithDefaults(this)

        Hawk.init(this).build()
    }
}