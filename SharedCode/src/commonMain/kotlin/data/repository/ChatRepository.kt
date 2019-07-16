package data.repository

import com.squareup.sqldelight.db.SqlDriver
import data.ChatListApi
import data.db.ChatShortDao
import data.entity.Chat
import org.kotlin.mpp.mobile.data.entity.Message
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
                log("chatRepository", e.toString())
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