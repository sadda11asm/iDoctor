package com.example.mppapp.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.orhanobut.hawk.Hawk
import org.kotlin.mpp.mobile.appContext

class iDoctorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        appContext = this

        Hawk.init(this).build()

    }
}