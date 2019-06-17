@file:Suppress("MemberVisibilityCanBePrivate")

package org.kotlin.mpp.mobile
import io.ktor.client.engine.HttpClientEngine
import org.kotlin.mpp.mobile.data.DoctorApi
import org.kotlin.mpp.mobile.usecases.GetDoctors
import kotlin.native.concurrent.ThreadLocal

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
@ThreadLocal
object ServiceLocator {

    val doctorApi by lazy { DoctorApi(PlatformServiceLocator.httpClientEngine) }

    val getDoctors: GetDoctors
        get() = GetDoctors(doctorApi)

}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}