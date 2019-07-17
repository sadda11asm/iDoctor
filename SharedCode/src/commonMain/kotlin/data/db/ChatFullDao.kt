package data.db

import db.ChatFullModel
import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.Message

class ChatFullDao (database: MyDatabase) {
    private val dbChatFull = database.chatFullModelQueries
    private val dbMes = database.messageModelQueries

    fun getChatFull(chatId: Int): ChatFull {
        val vals = dbMes.selectByChatId(chatId.toLong()).executeAsList()
        val messages = mutableListOf<Message>()
        for (mes in vals) {
            messages.add(Message(mes.id.toInt(), mes.chat_id.toInt(), mes.user_id.toInt(), mes.text, mes.created_at, mes.updated_at))
        }
        val chat =  dbChatFull.select(chatId.toLong()).executeAsOne()
        return ChatFull(chat.title, chat.updated, chat.anonymous.toInt(), chat.avatar, null, messages, chat.id.toInt())
    }

    fun insertChatFull(chat: ChatFull) {
        val messages = chat.messages
        for (mes in messages)
            dbMes.insertItem(mes.id.toLong(), mes.chatId.toLong(), mes.userId.toLong(), mes.text, mes.createdAt, mes.updatedAt)
        dbChatFull.insertItem(chat.id.toLong(), chat.title, chat.updated, chat.anonymous.toLong(), chat.avatar)
    }
}