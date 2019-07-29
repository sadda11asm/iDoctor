package org.kotlin.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.websocket.*
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.http.cio.websocket.send
import io.ktor.util.AttributeKey
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.SelectBuilder
import kotlinx.io.core.Closeable
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.entity.ChannelMessage
import org.kotlin.mpp.mobile.data.entity.Data
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.domain.defaultDispatcher
import org.kotlin.mpp.mobile.domain.uiDispatcher
import org.kotlin.mpp.mobile.presentation.chat.ChatPresenter
import org.kotlin.mpp.mobile.util.constants.BASE_URL
import org.kotlin.mpp.mobile.util.constants.TEMP_URL
import org.kotlin.mpp.mobile.util.log
import org.kotlin.mpp.mobile.ServiceLocator.listener
import presentation.chatlist.ChatListPresenter


class Sockets(private val engine: HttpClientEngine) {

//    lateinit var client: HttpClient
//
//    private fun connect() = HttpClient(engine).config {
//        install(WebSockets)
//    }

    val client = HttpClient(engine).config {
        install(WebSockets)
    }

    suspend fun subscribe() {
        CONNECT = true
        client.webSocket(
            method = HttpMethod.Get,
            host = "ws-chat.idoctor.kz",
            request = {
                url.protocol = URLProtocol.WS
                url.port = 80
                log("Sockets", "request")
            }
        ) {
            log("Sockets", "zashel")
            log("Sockets", listener.toString())
            while (CONNECT && !incoming.isClosedForReceive) {
                val frame = incoming.receive()
                log("Sockets", frame.toString())
                if (frame is Frame.Text) {
                    val body = frame.readText()
                    log("Sockets", body)
                    launch(uiDispatcher) {
                        log("Sockets", listener.toString())
                        val channelMes = Json.nonstrict.parse(ChannelMessage.serializer(), body)
                        when (channelMes.event) {
                            MESSAGE_ADDED -> {
                                log("Sockets", MESSAGE_ADDED)
                                listener?.onMessage(channelMes.data?.message!!)
                            }
                            CHAT_CREATED -> {
                                log("Sockets", CHAT_CREATED)
                                listener?.onChatCreated(channelMes.data?.chat!!)
                            }
                            MESSAGE_WAS_READ -> {
                                log("Sockets", MESSAGE_WAS_READ)
                                listener?.onMessageWasRead(channelMes.data?.message!!)
                            }
                        }
                    }
                }
            }
            close()
        }
    }

    fun unsubscribe() {
        log("Sockets", "UNSUBSCRIBE")
        CONNECT = false
    }

    companion object {
        private var CONNECT = true
        const val MESSAGE_ADDED = "message.added"
        const val CHAT_CREATED = "chat.created"
        const val MESSAGE_WAS_READ = "message.read"
    }
}
