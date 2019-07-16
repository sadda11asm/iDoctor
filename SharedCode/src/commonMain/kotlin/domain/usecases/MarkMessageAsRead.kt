package domain.usecases

import UseCase
import data.api.MessageApi
import data.entity.MarkMessageRequest
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class MarkMessageAsRead(private val messageApi: MessageApi) : UseCase<UseCase.None, MarkMessageRequest>() {
    override suspend fun run(params: MarkMessageRequest): Either<Exception, None> {
        return try {
            messageApi.markMessageAsRead(params)
            Success(None)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}