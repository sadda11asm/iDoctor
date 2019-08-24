@file:Suppress("MemberVisibilityCanBePrivate")

package org.kotlin.mpp.mobile

import com.squareup.sqldelight.db.SqlDriver
import data.api.ChatListApi
import data.api.DoctorApi
import data.api.UserApi
import data.api.MessageApi
import data.db.*
import data.repository.ChatFullRepository
import data.repository.ChatRepository
import data.repository.MessageRepository
import domain.usecases.MarkMessageAsRead
import domain.usecases.SendMessage
import data.repository.UserRepository
import domain.usecases.CreateChat
import domain.usecases.GetUserInfo
import io.ktor.client.engine.HttpClientEngine
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.LoginApi
import org.kotlin.mpp.mobile.domain.usecases.*
import org.kotlin.mpp.mobile.presentation.chat.ChatPresenter
import presentation.chatlist.ChatListPresenter
import org.kotlin.mpp.mobile.presentation.doctorlist.DoctorListPresenter
import org.kotlin.mpp.mobile.presentation.login.LoginPresenter
import org.kotlin.mpp.mobile.presentation.profile.ProfilePresenter
import org.kotlin.mpp.mobile.presentation.profile.edit.EditInfoPresenter
import org.kotlin.mpp.mobile.presentation.profile.password.PasswordChangePresenter
import presentation.doctorpage.DoctorPagePresenter
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
     *  Socket
     */

    var listener : SocketListener? = null

    fun setSocketListener(listenPresenter: SocketListener?) {
        listener = listenPresenter
    }


    val sockets by lazy {Sockets(PlatformServiceLocator.cioEngine)}



    /**
     * Load doctors
     */


    val doctorApi by lazy { DoctorApi(PlatformServiceLocator.httpClientEngine) }

    val getDoctors: GetDoctors
        get() = GetDoctors(doctorApi)

    val doctorListPresenter: DoctorListPresenter
        get() = DoctorListPresenter(getDoctors, createChat)

    /**
    * Get User
    */

    /**
     * Save Chat
     */

    val saveChat: SaveChat
        get() = SaveChat(chatRepository)



    /**
     * Get User Info
     */

    val userApi by lazy {UserApi(PlatformServiceLocator.httpClientEngine)}

    val userDao by lazy {UserDao(database) }

    val userRepository by lazy { UserRepository(userApi, userDao) }

    val getUserInfo: GetUserInfo
        get() = GetUserInfo(userRepository)

    /**
     * Authorize
     */

    val loginApi by lazy { LoginApi(PlatformServiceLocator.httpClientEngine) }

    val authorizeUser: AuthorizeUser
        get() = AuthorizeUser(loginApi)

    val loginPresenter: LoginPresenter
        get() = LoginPresenter(authorizeUser, getUserInfo)

    /**
     * Get chat full
     */

    val chatFullApi by lazy { ChatFullApi(PlatformServiceLocator.httpClientEngine) }

    val chatFullDao by lazy { ChatFullDao(database)}

    val chatFullRepository by lazy{ ChatFullRepository(chatFullApi, chatFullDao)}

    val getChatFull: GetChatFull
        get() = GetChatFull(chatFullRepository)

    val chatPresenter: ChatPresenter
        get() = ChatPresenter(getChatFull, sendMessage, receiveMessage, markMessageAsRead)

    /**
     * Get chat list
     */

    val chatListApi by lazy { ChatListApi(PlatformServiceLocator.httpClientEngine) }

    val chatShortDao by lazy { ChatShortDao(database) }

    val chatRepository by lazy { ChatRepository(chatListApi, chatShortDao) }

    val getChatList: GetChatList
        get() = GetChatList(chatRepository)

    val chatListPresenter: ChatListPresenter
        get() = ChatListPresenter(getChatList, receiveMessage, saveChat)

    /**
     * Profile page
     */

    val profilePresenter: ProfilePresenter
        get() = ProfilePresenter(fetchUserInfo)

    /**
     * Edit Page
     */

    val editUserInfo: EditUserInfo
        get() = EditUserInfo(userRepository)

    val fetchUserInfo: FetchUserInfo
        get() = FetchUserInfo(userRepository)

    val editInfoPresenter:EditInfoPresenter
        get() = EditInfoPresenter(fetchUserInfo, editUserInfo)

    /**
     * Change Password
     */

    val editPassword: EditPassword
        get() = EditPassword(userRepository)

    val passwordChangePresenter: PasswordChangePresenter
        get() = PasswordChangePresenter(editPassword)

    /**
     * Send message
     */

    val messageApi by lazy { MessageApi(PlatformServiceLocator.httpClientEngine) }

    val messageDao: MessageDao by lazy { MessageDao(database)}

    val messageRepository: MessageRepository by lazy { MessageRepository(messageApi, messageDao)}

    val sendMessage: SendMessage
        get() = SendMessage(messageRepository)


    /**
     * Receive message
     */

    val receiveMessage : ReceiveMessage
        get() = ReceiveMessage(messageRepository)


    /**
     * Mark message as Read
     */

    val markMessageAsRead: MarkMessageAsRead
        get() = MarkMessageAsRead(messageApi)
    /**
     *   Create Chat
     */

    val createChat: CreateChat
        get() = CreateChat(chatFullRepository)

    val doctorPagePresenter: DoctorPagePresenter by lazy { DoctorPagePresenter(createChat) }


    /**
     * Get Schedule
     */

    val getSchedule by lazy { GetSchedule(doctorApi)}


}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val driver: SqlDriver
    val httpClientEngine: HttpClientEngine
    val cioEngine: HttpClientEngine
}