package data.repository

import data.api.ChatListApi
import data.db.ChatShortDao
import data.entity.Chat
import org.kotlin.mpp.mobile.util.log

class ChatRepository(
    private val chatListApi: ChatListApi,
    private val chatShortDao: ChatShortDao
) {

    suspend fun getChatList(token: String, connection: Boolean):List<Chat> {
        return if (connection) {
            try {
               fetchChatList(token)
            } catch (e: Exception) {
                selectFromDb()
            }

        } else {
            selectFromDb()
        }
    }


    private suspend fun fetchChatList(token: String): List<Chat> {
        val result = chatListApi.getChatList(token)
        log("CHATLIST", result.toString())
        for (chat in result) {
            chatShortDao.insert(chat)
        }
        return result
    }

    private fun selectFromDb(): List<Chat> {
        val chats = chatShortDao.selectAll().toList()
        log("ChatRepository", chats.toString())
        return chats
    }
}