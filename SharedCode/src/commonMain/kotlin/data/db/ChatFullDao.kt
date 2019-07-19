package data.db

import data.entity.Member
import data.entity.to
import db.ChatFullModel
import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.Message

class ChatFullDao (database: MyDatabase) {
    private val dbChatFull = database.chatFullModelQueries
    private val dbMember = database.memberModelQueries
    private val dbMes = database.messageModelQueries

    fun getChatFull(chatId: Int): ChatFull {
        val vals = dbMes.selectByChatId(chatId.toLong()).executeAsList()
        val messages = mutableListOf<Message>()
        for (mes in vals) {
            messages.add(Message(mes.id.toInt(), mes.user_id.toInt(), mes.text, mes.created_at))
        }
        val chat =  dbChatFull.select(chatId.toLong()).executeAsOne()
        val memberModels = dbMember.selectById(chatId.toLong()).executeAsList()
        val members = mutableListOf<Member>()
        for (member in memberModels)
            members.add(member.to())
        return ChatFull(chat.id.toInt(), chat.title, chat.updated, chat.anonymous.toInt(), chat.avatar, members, messages)
    }

    fun insertChatFull(chat: ChatFull) {
        val messages = chat.messages
        for (mes in messages)
            dbMes.insertItem(mes.id.toLong(), chat.id.toLong(), mes.userId.toLong(), mes.text, mes.createdAt)
        for (member in chat.members)
            dbMember.insertItem(
                member.userId.toLong(),
                member.userName,
                chat.id.toLong(),
                member.lastReadMes.toLong(),
                member.lastAttempt,
                member.unreadCount.toLong()
            )
        dbChatFull.insertItem(chat.id.toLong(), chat.title, chat.updated, chat.anonymous.toLong(), chat.avatar)
    }
}