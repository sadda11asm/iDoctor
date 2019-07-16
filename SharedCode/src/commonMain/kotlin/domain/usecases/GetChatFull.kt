package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.repository.ChatFullRepository
import org.kotlin.mpp.mobile.data.ChatFullApi
import org.kotlin.mpp.mobile.data.entity.ChatFullRequest
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetChatFull(private val chatFullRepository: ChatFullRepository) : UseCase<ChatFullResponse, ChatFullRequest>() {
    override suspend fun run(params: ChatFullRequest): Either<Exception, ChatFullResponse> {
        return try {
            val response = chatFullRepository.getChatFull(params.token, params.chatId)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}