package data.db

import data.entity.SendMessageRequest
import util.currentTime

class MessageDao (database: MyDatabase) {
    private val dbMes = database.messageModelQueries
    private val dbLast = database.lastMessageModelQueries

    internal fun insert(request: SendMessageRequest, id: Int) {
        dbMes.insertItem(id.toLong(), request.chatId.toLong(), request.userId.toLong(), request.messageText, currentTime)
    }

    internal fun insertLastMes(request: SendMessageRequest, id: Int) {
        dbLast.insertItem(id.toLong(), request.chatId.toLong(),request.userId.toLong(), request.messageText, currentTime, currentTime)
    }
}