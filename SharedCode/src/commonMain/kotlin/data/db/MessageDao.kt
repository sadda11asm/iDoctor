package data.db

import data.entity.SendMessageRequest

class MessageDao (database: MyDatabase) {
    private val dbMes = database.messageModelQueries

    internal fun insert(request: SendMessageRequest, id: Int) {
        dbMes.insertNewItem(id.toLong(), request.chatId.toLong(), request.userId.toLong(), request.messageText)
    }
}