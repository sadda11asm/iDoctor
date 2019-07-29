package org.kotlin.mpp.mobile.domain.usecases

import org.kotlin.mpp.mobile.Sockets
import UseCase
import org.kotlin.mpp.mobile.SocketListener
import org.kotlin.mpp.mobile.domain.model.Either
import org.kotlin.mpp.mobile.domain.model.Failure
import org.kotlin.mpp.mobile.domain.model.Success
import org.kotlin.mpp.mobile.util.log

//class Subscribe (private val sockets: Sockets): UseCase<UseCase.None, SocketListener>() {
//    override suspend fun run(listener: SocketListener): Either<Exception, None> {
//        return try{
//            sockets.subscribe(listener, listener.getSource())
//            Success(UseCase.None)
//        } catch (e: Exception) {
//            log("Sockets", e.toString())
//            Failure(e)
//        }
//    }
//}