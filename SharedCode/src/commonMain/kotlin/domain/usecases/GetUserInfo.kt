package domain.usecases

import UseCase
import data.entity.UserFull
import data.repository.ChatRepository
import data.repository.UserRepository
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class GetUserInfo(private val userRepository: UserRepository) : UseCase<UserFull, String>() {
    override suspend fun run(token: String): Either<Exception, UserFull> {
        return try {
            val user =  userRepository.getUserInfo(token)
            Success(user)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

