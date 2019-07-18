package data.repository

import data.db.ChatFullDao
import data.entity.Chat
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse

class ChatFullRepository(
    private val chatFullApi: ChatFullApi,
    private val chatFullDao: ChatFullDao
) {
    suspend fun getChatFull(token: String, chatId: Int, connection: Boolean): ChatFull {
        return if (connection) {
            try {
                fetchChatFull(token, chatId)
            } catch (e: Exception) {
                selectFromDb(chatId)
            }
        }
        else selectFromDb(chatId)
    }

    private suspend fun fetchChatFull(token: String, chatId: Int): ChatFull {
        val chatFull =  chatFullApi.getChatFull(token, chatId).data
        chatFull.id = chatId
        chatFullDao.insertChatFull(chatFull)
        return chatFull
    }

    private fun selectFromDb(chatId: Int): ChatFull {
        return chatFullDao.getChatFull(chatId)
    }

    suspend fun createChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int?):Int {
        return chatFullApi.createChat(token, title, userId, anonymous, doctorId)
    }
}