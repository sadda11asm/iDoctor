package org.kotlin.mpp.mobile

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import data.db.MyDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
actual object PlatformServiceLocator {

    actual val httpClientEngine: HttpClientEngine by lazy { Ios.create() }
    actual val driver: SqlDriver = NativeSqliteDriver(MyDatabase.Schema, "idoctor.db")
    actual val cioEngine: HttpClientEngine by lazy { Ios.create() }
}