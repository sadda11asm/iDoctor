package org.kotlin.mpp.mobile.domain.usecases

import org.kotlin.mpp.mobile.Sockets
import UseCase
import org.kotlin.mpp.mobile.SocketListener
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success

class Subscribe (private val sockets: Sockets): UseCase<UseCase.None, SocketListener>() {
    override suspend fun run(listener: SocketListener): Either<Exception, None> {
        return try{
            sockets.subscribe(listener)
            Success(UseCase.None)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}