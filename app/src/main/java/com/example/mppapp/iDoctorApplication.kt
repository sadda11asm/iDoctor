package com.example.mppapp

import android.app.Application
import com.orhanobut.hawk.Hawk

class iDoctorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build()

    }
}