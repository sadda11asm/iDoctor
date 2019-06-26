package com.example.mppapp.ui

import android.app.Application
import com.orhanobut.hawk.Hawk

class iDoctorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build()

    }
}