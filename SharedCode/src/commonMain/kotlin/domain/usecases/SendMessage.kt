package domain.usecases

import UseCase
import data.api.MessageApi
import data.entity.MessageResponse
import data.entity.SendMessageRequest
import data.repository.MessageRepository
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class SendMessage(private val messageRepository: MessageRepository) : UseCase<MessageResponse, SendMessageRequest>() {
    override suspend fun run(params: SendMessageRequest): Either<Exception, MessageResponse> {
        return try {
            val response = messageRepository.sendMessage(params)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}