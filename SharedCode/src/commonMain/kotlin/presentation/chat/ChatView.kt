package org.kotlin.mpp.mobile.presentation.chat

import org.kotlin.mpp.mobile.data.entity.ChatFullResponse

interface ChatView {

    fun token(): String

    fun showChat(chatFullResponse: ChatFullResponse)

    fun showError(e: Exception)
}