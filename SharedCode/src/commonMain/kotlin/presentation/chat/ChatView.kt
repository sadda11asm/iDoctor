package org.kotlin.mpp.mobile.presentation.chat

import org.kotlin.mpp.mobile.data.entity.ChatFullResponse
import org.kotlin.mpp.mobile.data.entity.Message

interface ChatView {

    fun token(): String

    fun chatId(): Int

    fun showChat(chatFullResponse: ChatFullResponse)

    fun showChatLoadError(e: Exception)

    fun showMessage(message: Message)
}