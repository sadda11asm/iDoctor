@file:Suppress("MemberVisibilityCanBePrivate")

package org.kotlin.mpp.mobile

import com.squareup.sqldelight.db.SqlDriver
import data.api.ChatListApi
import data.api.DoctorApi
import data.api.MessageApi
import data.db.ChatShortDao
import data.db.createDatabase
import data.repository.ChatRepository
import domain.usecases.MarkMessageAsRead
import domain.usecases.SendMessage
import io.ktor.client.engine.HttpClientEngine
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.LoginApi
import org.kotlin.mpp.mobile.domain.usecases.AuthorizeUser
import org.kotlin.mpp.mobile.domain.usecases.GetChatFull
import org.kotlin.mpp.mobile.domain.usecases.GetChatList
import org.kotlin.mpp.mobile.domain.usecases.GetDoctors
import org.kotlin.mpp.mobile.presentation.chat.ChatPresenter
import presentation.chatlist.ChatListPresenter
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListPresenter
import org.kotlin.mpp.mobile.presentation.login.LoginPresenter
import kotlin.native.concurrent.ThreadLocal

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
@ThreadLocal
object ServiceLocator {

    // TODO change comments to APIs, Presenters, UseCases etc.

    /**
     * Database
     */


    val database by lazy { createDatabase(PlatformServiceLocator.driver) }

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
     * Get chat
     */

    val chatFullApi by lazy { ChatFullApi(PlatformServiceLocator.httpClientEngine) }

    val getChatFull: GetChatFull
        get() = GetChatFull(chatFullApi)

    val chatPresenter: ChatPresenter
        get() = ChatPresenter(getChatFull, sendMessage, markMessageAsRead)

    /**
     * Get chat list
     */

    val chatListApi by lazy { ChatListApi(PlatformServiceLocator.httpClientEngine) }

    val chatShortDao by lazy { ChatShortDao(database) }

    val chatRepository by lazy { ChatRepository(chatListApi, chatShortDao) }

    val getChatList: GetChatList
        get() = GetChatList(chatRepository)

    val chatListPresenter: ChatListPresenter
        get() = ChatListPresenter(getChatList)

    /**
     * Send message
     */

    val messageApi by lazy { MessageApi(PlatformServiceLocator.httpClientEngine) }

    val sendMessage: SendMessage
        get() = SendMessage(messageApi)

    /**
     * Mark message as Read
     */

    val markMessageAsRead: MarkMessageAsRead
        get() = MarkMessageAsRead(messageApi)
}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val driver: SqlDriver
    val httpClientEngine: HttpClientEngine
}