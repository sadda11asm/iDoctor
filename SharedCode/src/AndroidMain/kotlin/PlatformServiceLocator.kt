package org.kotlin.mpp.mobile

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import data.db.MyDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
//import okhttp3.logging.HttpLoggingInterceptor
import android.content.Context


/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */

lateinit var appContext: Context

actual object PlatformServiceLocator {


    actual val httpClientEngine: HttpClientEngine by lazy {
        OkHttp.create()
//        {
//            val networkInterceptor = HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            }
            //addNetworkInterceptor(networkInterceptor)
//        }
    }
    actual val driver: SqlDriver
       by lazy{ AndroidSqliteDriver(MyDatabase.Schema, appContext, "idoctor.db")}

}