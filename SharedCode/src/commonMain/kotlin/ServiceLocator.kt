@file:Suppress("MemberVisibilityCanBePrivate")

package org.kotlin.mpp.mobile
import data.ChatListApi
import data.DoctorApi
import io.ktor.client.engine.HttpClientEngine
import org.kotlin.mpp.mobile.data.LoginApi
import org.kotlin.mpp.mobile.domain.usecases.AuthorizeUser
import org.kotlin.mpp.mobile.domain.usecases.GetChatList
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import presentation.chatlist.ChatListPresenter
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
     * Get chat list
     */

    val chatListApi by lazy { ChatListApi(PlatformServiceLocator.httpClientEngine)}

    val getChatList: GetChatList
        get() = GetChatList(chatListApi)
    val chatListPresenter: ChatListPresenter
        get() = ChatListPresenter(getChatList)

}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}