package data.db

import com.squareup.sqldelight.Query
import data.entity.Chat
import data.entity.LastMessage
import data.entity.to
import data.entity.toChat
import db.ChatShortModel
import org.kotlin.mpp.mobile.util.log

class ChatShortDao(database: MyDatabase) {

    private val dbChat = database.chatShortModelQueries
    private val dbMes = database.lastMessageModelQueries

    internal fun insert(item: Chat) {
        dbChat.insertItem(
            item.id.toLong(),
            item.title ?: "anonymous user",
            item.createdAt,
            item.updatedAt,
            item.deletedAt,
            item.users,
            item.avatar
        )
        if (item.lastMessage != null)
            dbMes.insertItem(
                item.lastMessage.id.toLong(),
                item.lastMessage.chatId?.toLong()!!,
                item.lastMessage.userId?.toLong()!!,
                item.lastMessage.message,
                item.lastMessage.createdAt,
                item.lastMessage.updatedAt
            )

    }


    internal fun selectAll(): MutableList<Chat> {
        val chats = dbChat.selectAll()
            .executeAsList()
        val ansList: MutableList<Chat> = mutableListOf()
        for (chat in chats) {
            val lastMessage = dbMes.selectByChatId(chat.id)
            val mes = if (lastMessage.executeAsList().isNotEmpty())
                lastMessage.executeAsList()[0].to()
            else null
            ansList.add(toChat(chat, mes))
        }
        return ansList
    }
}