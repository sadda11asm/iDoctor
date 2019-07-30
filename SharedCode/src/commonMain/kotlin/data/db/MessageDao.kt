package data.db

import data.entity.SendMessageRequest
import org.kotlin.mpp.mobile.data.entity.Message
import util.currentTime

class MessageDao (database: MyDatabase) {
    private val dbMes = database.messageModelQueries
    private val dbLast = database.lastMessageModelQueries

    internal fun insert(mes: Message) {
        dbMes.insertItem(mes.id.toLong(), mes.chatId!!.toLong(), mes.userId.toLong(), mes.text, mes.createdAt)
    }

    internal fun insertLastMes(mes: Message) {
        dbLast.insertItem(mes.id.toLong(), mes.chatId!!.toLong(),mes.userId.toLong(), mes.text, mes.createdAt, currentTime)
    }
}