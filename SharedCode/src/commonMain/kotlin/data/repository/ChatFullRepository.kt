package data.repository

import data.db.ChatFullDao
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.entity.ChatFull
import util.convertTime
import org.kotlin.mpp.mobile.util.log

class ChatFullRepository(
    private val chatFullApi: ChatFullApi,
    private val chatFullDao: ChatFullDao
) {
    suspend fun getChatFull(token: String, chatId: Int, connection: Boolean, cached: Boolean): ChatFull {
        if (cached) {
            return try {
                selectFromDb(chatId)
            } catch (e: Exception) {
                log("Chat", e.message!!)
                fetchChatFull(token, chatId)
            }
        }
        return if (connection) {
            try {
                log("Chat", "api")
                fetchChatFull(token, chatId)
            } catch (e: Exception) {
                log("Chat", e.message!!)
                selectFromDb(chatId)
            }
        }
        else selectFromDb(chatId)
    }

    private suspend fun fetchChatFull(token: String, chatId: Int): ChatFull {
        log("Chat", "fetchChatFull")
        val chatFull =  chatFullApi.getChatFull(token, chatId).data
        chatFull.id = chatId
        for (message in chatFull.messages) {
            message.createdAt = convertTime(message.createdAt)
            log("SEND", message.createdAt.toString())
        }
        chatFullDao.insertChatFull(chatFull)
        log("Chat", "fetchChatFull $chatFull")
        return chatFull
    }

    private fun selectFromDb(chatId: Int): ChatFull {
        return chatFullDao.getChatFull(chatId)
    }

    suspend fun createChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int?):Int {
        return chatFullApi.createChat(token, title, userId, anonymous, doctorId)
    }
}