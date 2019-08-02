package data.repository

import data.api.ChatListApi
import data.db.ChatShortDao
import data.entity.Chat
import org.kotlin.mpp.mobile.util.log
import util.convertTime

class ChatRepository(
    private val chatListApi: ChatListApi,
    private val chatShortDao: ChatShortDao
) {

    suspend fun getChatList(token: String, connection: Boolean, cached: Boolean):List<Chat> {
        if (cached) {
            return try{
                log("ChatList", "KEKUS")
                selectFromDb()
            } catch (e: Exception) {
                log("ChatList", e.toString())
                fetchChatList(token)
            }
        }
        return if (connection) {
            try {
               fetchChatList(token)
            } catch (e: Exception) {
                log("ChatList", e.toString())
                selectFromDb()
            }

        } else {
            selectFromDb()
        }
    }


    private suspend fun fetchChatList(token: String): List<Chat> {
        log("CHATList", "API")
        val result = chatListApi.getChatList(token).toMutableList()
        for (chat in result) {
            if (chat.lastMessage!=null)
                chat.lastMessage!!.updatedAt = convertTime(chat.lastMessage!!.updatedAt)
            chatShortDao.insert(chat)
        }
        result.sortByDescending { it.lastMessage?.updatedAt }
        return result
    }

    private fun selectFromDb(): List<Chat> {
        log("ChatList", "DB")
        val chats = chatShortDao.selectAll().toMutableList()
        chats.sortByDescending { it.lastMessage?.updatedAt }
        log("ChatList", chats.toString())
        return chats
    }


    fun saveChat(chat: Chat) {
        chatShortDao.insert(chat)
    }
}