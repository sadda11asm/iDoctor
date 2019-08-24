package org.kotlin.mpp.mobile.domain.usecases

import data.entity.UserEditResponse
import data.repository.UserRepository
import org.kotlin.mpp.mobile.data.entity.UserEditRequest
import UseCase
import data.entity.UserFull
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success


class EditUserInfo (private val userRepository: UserRepository): UseCase<UserFull, UserEditRequest>() {
    override suspend fun run(params: UserEditRequest): Either<Exception, UserFull> {
        return try {
            val response = userRepository.editUserInfo(params)
            Success(response)
        } catch(e: Exception) {
            Failure(e)
        }
    }

}