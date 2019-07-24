package org.kotlin.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocket
import io.ktor.client.features.websocket.ws
import io.ktor.client.features.websocket.wss
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.http.cio.websocket.send
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.SelectBuilder
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.uiDispatcher
import org.kotlin.mpp.mobile.util.log


class Sockets(engine: HttpClientEngine) {

    val client = HttpClient(engine).config {
        install(WebSockets)
    }

    suspend fun subscribe(listener: SocketListener) {
        CONNECT = true
        client.webSocket(
            method = HttpMethod.Get,
            host = "192.168.43.132",
            request = {
                url.protocol = URLProtocol.WS
                url.port = 3000
                log("Sockets", "request")
            }
        ) {
            log("Sockets", "zashel")
            log("Sockets", CONNECT.toString())
            while (CONNECT && !incoming.isClosedForReceive) {
                val frame = incoming.receive()
                log("Sockets", frame.toString())
                when(frame) {
                    is Frame.Text -> {
                        val body = frame.readText()
                        launch(uiDispatcher) {
                            listener.onMessage(body)
                        }
//                        listener.onMessage(Json.nonstrict.parse(Message.serializer(), body))
                    }
                }
            }
        }
    }

//    private suspend fun sendMessage(listener: )
    fun unsubscribe() {
        log("Sockets", "UNSUBSCRIBE")
        CONNECT = false
    }

    companion object {
        var CONNECT = true
    }
}
