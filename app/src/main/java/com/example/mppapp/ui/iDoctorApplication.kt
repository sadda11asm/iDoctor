package com.example.mppapp.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.Sockets
import org.kotlin.mpp.mobile.appContext

class iDoctorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = this

        Stetho.initializeWithDefaults(this)

        Hawk.init(this).build()
    }
}