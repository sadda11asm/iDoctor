package domain.usecases

import data.repository.ChatFullRepository
import UseCase
import data.db.createDatabase
import data.entity.CreateChatParams
import data.entity.CreateChatRequest
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class CreateChat(
    private val chatFullRepository: ChatFullRepository
): UseCase<Int, CreateChatParams>() {
    override suspend fun run(params: CreateChatParams): Either<Exception, Int> {
        return try{
            val response = chatFullRepository.createChat(params.token, params.title, params.user_id, params.anonymous, params.doctor_id)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }
    }

}