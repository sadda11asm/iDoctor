package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.ChatListApi
import data.DoctorApi
import data.entity.Chat
import data.entity.ChatResponse
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetChatList(private val chatListApi: ChatListApi): UseCase<List<Chat>, String>() {
    override suspend fun run(params: String): Either<Exception, List<Chat>> {
        return try {
            val response = chatListApi.getChatList(params)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

