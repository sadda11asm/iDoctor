package data.repository

import data.entity.Chat
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse

class ChatFullRepository(
    private val chatFullApi: ChatFullApi
) {
    suspend fun getChatFull(token: String, chatId: Int): ChatFullResponse {
        return chatFullApi.getChatFull(token, chatId)
    }

    suspend fun createChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int?):Int {
        return chatFullApi.createChat(token, title, userId, anonymous, doctorId)
    }
}