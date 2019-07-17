package org.kotlin.mpp.mobile.presentation.chat

import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.Message

interface ChatView {

    fun token(): String

    fun chatId(): Int

    fun userId(): Int

    fun showChat(chatFull: ChatFull)
    fun userId(): Int

    fun showChatLoadError(e: Exception)

    fun showMessage(message: Message)

    fun getConnection(): Boolean
}