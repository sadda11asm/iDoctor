package org.kotlin.mpp.mobile

import data.entity.Chat
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
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
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.drop
import kotlinx.coroutines.selects.SelectBuilder
import kotlinx.io.IOException
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
import org.kotlin.mpp.mobile.data.entity.JoinRequest
import org.kotlin.mpp.mobile.util.isConnected
import presentation.chatlist.ChatListPresenter


class Sockets(private val engine: HttpClientEngine) {

    val client = HttpClient(engine).config {
        install(WebSockets)
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun subscribe(chatList: List<Chat>) {
        if (subscribed>=1) return
        CONNECT = true
        subscribed++
        val json = defaultSerializer()
        try {
            client.webSocket(
                method = HttpMethod.Get,
                host = "ws-chat.idoctor.kz",
                request = {
                    url.protocol = URLProtocol.WS
                    url.port = 80
                    log("Sockets", "request")
                }
            ) {
                val chats = getChatIDs(chatList)
                val request = json.write(JoinRequest(chats)).toString()
                log("Sockets", "request: $request")
                send(request)
                listenSockets(this)
            }
        } catch (e: Exception) {
            log("Sockets", e.toString())
            subscribed = 0
            if (CONNECT)
                subscribe(chatList)
        }
    }

    private fun getChatIDs(chatList: List<Chat>): MutableList<Int> {
        val list = mutableListOf<Int>()
        for (chat in chatList) {
            list.add(chat.id)
        }
        return list
    }

    fun unsubscribe() {
        log("Sockets", "UNSUBSCRIBE")
        CONNECT = false
    }

    private suspend fun listenSockets(session: DefaultClientWebSocketSession) {
        with (session) {
            log("Sockets", "zashel")
            log("Sockets", listener.toString())
            while (CONNECT) {
                try {
                    val frame = incoming.receive()
                    log("Sockets", frame.toString())
                    if (frame is Frame.Text) {
                        val body = frame.readText()
                        log("Sockets", body)
                        launch(uiDispatcher) {
                            log("Sockets", listener.toString())
                            try {
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
                            } catch (e: Exception) {
                                log("Sockets", e.toString())
                            }
                        }
                    }
                } catch (e: Exception) {
                    if (CONNECT) continue
                }
            }
            //if (CONNECT) subscribe()
            close()
            subscribed = 0
        }
    }

    companion object {
        private var CONNECT = true
        private var subscribed = 0
        const val MESSAGE_ADDED = "message.added"
        const val CHAT_CREATED = "chat.created"
        const val MESSAGE_WAS_READ = "message.read"
    }
}
