package data.db

import com.squareup.sqldelight.Query
import data.entity.*
import db.ChatShortModel
import org.kotlin.mpp.mobile.util.log

class ChatShortDao(database: MyDatabase) {

    private val dbChat = database.chatShortModelQueries
    private val dbMes = database.lastMessageModelQueries
    private val dbMember = database.memberModelQueries

    internal fun insert(item: Chat) {
        dbChat.insertItem(
            item.id.toLong(),
            item.title ?: "anonymous user",
            item.updated,
            item.isAnonymous.toLong(),
            item.avatar
        )
        for (member in item.members)
            dbMember.insertItem(
                member.userId.toLong(),
                member.userName,
                item.id.toLong(),
                member.lastReadMes.toLong(),
                member.lastAttempt,
                member.unreadCount.toLong()
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
            val memberModels = dbMember.selectById(chat.id).executeAsList()
            val mes = if (lastMessage.executeAsList().isNotEmpty())
                lastMessage.executeAsList()[0].to()
            else null
            val members = mutableListOf<Member>()
            for (member in memberModels)
                members.add(member.to())
            ansList.add(toChat(chat, mes, members))
        }
        return ansList
    }
}