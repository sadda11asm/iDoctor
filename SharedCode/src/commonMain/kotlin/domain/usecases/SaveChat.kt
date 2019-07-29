package org.kotlin.mpp.mobile.domain.usecases

import data.repository.ChatRepository
import UseCase
import data.entity.Chat
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class SaveChat(private val chatRepository: ChatRepository): UseCase<UseCase.None, Chat>() {
    override suspend fun run(params: Chat): Either<Exception, UseCase.None> {
        return try {
            chatRepository.saveChat(params)
            Success(UseCase.None)
        } catch (e: Exception) {
            Failure(e)
        }
    }

}