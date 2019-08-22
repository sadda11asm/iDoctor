package org.kotlin.mpp.mobile.presentation.chatlist

import data.entity.Chat
import org.kotlin.mpp.mobile.data.entity.Message

interface ChatListView {

    fun showLoading(loading: Boolean) // TODO add boolean loading param

    fun showChats(chats: MutableList<Chat>)

    fun showLoadFailed(e: Exception)

    fun showChat(chat: Chat)

    fun getToken(): String

    fun showMessage(mes: Message)
}