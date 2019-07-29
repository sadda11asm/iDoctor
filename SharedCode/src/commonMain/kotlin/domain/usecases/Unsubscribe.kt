package org.kotlin.mpp.mobile.domain.usecases

import org.kotlin.mpp.mobile.Sockets
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import UseCase

//class Unsubscribe (private val sockets: Sockets): UseCase<UseCase.None, UseCase.None>() {
//    override suspend fun run(params: None): Either<Exception, None> {
//        return try{
//            sockets.unsubscribe()
//            Success(UseCase.None)
//        } catch (e: Exception) {
//            Failure(e)
//        }
//    }
//}