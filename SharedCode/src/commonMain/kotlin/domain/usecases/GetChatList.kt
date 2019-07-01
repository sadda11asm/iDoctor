package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.ChatListApi
import data.DoctorApi
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetChatList(private val chatListApi: ChatListApi): UseCase<DoctorResponse, String>() {
    override suspend fun run(params: String): Either<Exception, DoctorResponse> {
        return try {
            val response = chatListApi.getChatList(params)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

