package org.kotlin.mpp.mobile

import data.entity.Chat
import org.kotlin.mpp.mobile.data.entity.Message

interface SocketListener {
    fun onMessage(mes: Message) {}
    fun onChatCreated(chat: Chat) {}
    fun onMessageWasRead(message: Message) {}
}