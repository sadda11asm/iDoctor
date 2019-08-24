package org.kotlin.mpp.mobile.domain.usecases

import data.repository.UserRepository
import org.kotlin.mpp.mobile.data.entity.PasswordEditRequest
import org.kotlin.mpp.mobile.data.entity.PasswordEditResponse
import UseCase
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success


class EditPassword(private val userRepository: UserRepository): UseCase<PasswordEditResponse, PasswordEditRequest>() {
    override suspend fun run(params: PasswordEditRequest): Either<Exception, PasswordEditResponse> {
        return try {
            val response = userRepository.editPassword(params)
            Success(response)
        } catch (e: Exception) {
            Failure(e)
        }

    }

}