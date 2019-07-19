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
                log("chatRepository", e.toString())
                selectFromDb()
            }

        } else {
            selectFromDb()
        }
    }


    private suspend fun fetchChatList(token: String): List<Chat> {
        val result = chatListApi.getChatList(token).toMutableList()
        log("CHATLIST", result.toString())
        for (chat in result) {
            chatShortDao.insert(chat)
        }
        result.sortByDescending { it.lastMessage?.updatedAt }
        return result
    }

    private fun selectFromDb(): List<Chat> {
        val chats = chatShortDao.selectAll().toMutableList()
        chats.sortByDescending { it.lastMessage?.updatedAt }
        log("ChatRepository", chats.toString())
        return chats
    }
}