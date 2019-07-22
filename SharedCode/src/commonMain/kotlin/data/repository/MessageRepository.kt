package data.repository

import data.api.MessageApi
import data.db.MessageDao
import data.entity.MessageResponse
import data.entity.SendMessageRequest
import data.entity.UserFull
import org.kotlin.mpp.mobile.util.log

class MessageRepository (
    private val messageApi: MessageApi,
    private val messageDao: MessageDao
) {
    suspend fun sendMessage(request: SendMessageRequest): MessageResponse {
        try {
            val response = messageApi.sendMessage(request)
            insertToDb(request, response.messageId)
            return response
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    private fun insertToDb(request: SendMessageRequest, id: Int) {
        log("SEND", request.toString())
        messageDao.insert(request, id)
    }
}