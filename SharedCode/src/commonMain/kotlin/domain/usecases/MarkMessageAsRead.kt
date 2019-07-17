package domain.usecases

import UseCase
import data.api.MessageApi
import data.entity.MarkMessageRequest
import data.entity.MessageResponse
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class MarkMessageAsRead(private val messageApi: MessageApi) : UseCase<MessageResponse, MarkMessageRequest>() {
    override suspend fun run(params: MarkMessageRequest): Either<Exception, MessageResponse> {
        return try {
            val response = messageApi.markMessageAsRead(params)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}