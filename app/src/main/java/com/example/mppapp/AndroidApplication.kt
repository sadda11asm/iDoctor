//package com.example.mppapp
//
//import android.app.Application
//import com.example.mppapp.di.appModule
//import com.example.mppapp.di.authModule
//import com.example.mppapp.di.mainModule
//import com.example.mppapp.di.networkingModule
//import org.koin.core.context.startKoin
//
//class AndroidApplication : Application() {
//
//    override fun onCreate() {
//        super.onCreate()
//
//        startKoin(this, listOf(networkingModule, appModule, authModule, mainModule))
//
//    }
//}