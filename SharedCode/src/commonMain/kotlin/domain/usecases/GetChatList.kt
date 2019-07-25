package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.entity.Chat
import data.repository.ChatRepository
import org.kotlin.mpp.mobile.data.entity.ChatListRequest
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import org.kotlin.mpp.mobile.util.log

class GetChatList(private val chatRepository: ChatRepository): UseCase<List<Chat>, ChatListRequest>() {
    override suspend fun run(params:ChatListRequest): Either<Exception, List<Chat>> {
        return try {
            val response = chatRepository.getChatList(params.token, params.connection, params.cached)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

