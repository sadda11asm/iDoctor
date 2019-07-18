package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import data.entity.Chat
import data.repository.ChatRepository
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import org.kotlin.mpp.mobile.util.log

class GetChatList(private val chatRepository: ChatRepository): UseCase<List<Chat>, Pair<String, Boolean>>() {
    override suspend fun run(params: Pair<String, Boolean>): Either<Exception, List<Chat>> {
        return try {
            val response = chatRepository.getChatList(params.first, params.second)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

