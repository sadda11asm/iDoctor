@file:Suppress("MemberVisibilityCanBePrivate")

package org.kotlin.mpp.mobile
import data.DoctorApi
import io.ktor.client.engine.HttpClientEngine
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.LoginApi
import org.kotlin.mpp.mobile.domain.usecases.AuthorizeUser
import org.kotlin.mpp.mobile.domain.usecases.GetChatFull
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import org.kotlin.mpp.mobile.presentation.chat.ChatPresenter
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListPresenter
import org.kotlin.mpp.mobile.presentation.login.LoginPresenter
import kotlin.native.concurrent.ThreadLocal

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
@ThreadLocal
object ServiceLocator {

    /**
     * Load doctors
     */

    val doctorApi by lazy { DoctorApi(PlatformServiceLocator.httpClientEngine) }

    val getDoctors: GetDoctors
        get() = GetDoctors(doctorApi)

    val doctorListPresenter: DoctorListPresenter
        get() = DoctorListPresenter(getDoctors)

    /**
     * Authorize
     */

    val loginApi by lazy { LoginApi(PlatformServiceLocator.httpClientEngine) }

    val authorizeUser: AuthorizeUser
        get() = AuthorizeUser(loginApi)

    val loginPresenter: LoginPresenter
        get() = LoginPresenter(authorizeUser)

    /**
     * Get chat full
     */

    val chatFullApi by lazy { ChatFullApi(PlatformServiceLocator.httpClientEngine) }

    val getChatFull: GetChatFull
        get() = GetChatFull(chatFullApi)

    val chatPresenter : ChatPresenter
        get() = ChatPresenter(getChatFull)
}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}