package data.repository

import data.api.MessageApi
import data.db.MessageDao
import data.entity.MessageResponse
import data.entity.SendMessageRequest
import data.entity.UserFull
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.util.log

class MessageRepository (
    private val messageApi: MessageApi,
    private val messageDao: MessageDao
) {
    suspend fun sendMessage(request: SendMessageRequest): MessageResponse {
        try {
            val response = messageApi.sendMessage(request)
            saveMessage(Message(response.messageId, request.userId, request.messageText))
            return response
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun saveMessage(mes: Message) {
        log("SEND", mes.toString())
        messageDao.insert(mes)
        messageDao.insertLastMes(mes)
    }
}