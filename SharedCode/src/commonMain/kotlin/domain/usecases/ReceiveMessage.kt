package org.kotlin.mpp.mobile.domain.usecases

import data.repository.MessageRepository
import UseCase
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class ReceiveMessage(private val messageRepository: MessageRepository): UseCase<UseCase.None, Message>() {
    override suspend fun run(params: Message): Either<Exception, None> {
        return try {
            messageRepository.saveMessage(params)
            Success(UseCase.None)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}