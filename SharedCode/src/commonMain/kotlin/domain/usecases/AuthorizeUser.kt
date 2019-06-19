package org.kotlin.mpp.mobile.domain.usecases

import UseCase
import org.kotlin.mpp.mobile.data.LoginApi
import org.kotlin.mpp.mobile.data.entity.AuthorizationResponse
import org.kotlin.mpp.mobile.data.entity.User
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class AuthorizeUser (private val loginApi: LoginApi): UseCase<AuthorizationResponse, User>() {
    override suspend fun run(params: User):Either<Exception, AuthorizationResponse> {
        return try{
            val response = loginApi.authorizeUser(params.username, params.password)
            Success(response)
        } catch(e: Exception) {
            Failure(e)
        }
    }
}