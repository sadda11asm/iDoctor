package org.kotlin.mpp.mobile.domain.usecases

import data.entity.UserFull
import data.repository.UserRepository
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import UseCase


class FetchUserInfo( private val userRepository: UserRepository) : UseCase<UserFull, Int>() {
    override suspend fun run(userId: Int): Either<Exception, UserFull> {
        return try {
            val user = userRepository.selectFromDb(userId)
            Success(user)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}