package data.api

import data.entity.MarkMessageRequest
import data.entity.MessageResponse
import data.entity.SendMessageRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.entity.ignoreOutgoingContent
import org.kotlin.mpp.mobile.util.constants.*
import org.kotlin.mpp.mobile.util.log

class MessageApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }
    }

    suspend fun sendMessage(sendMessageRequest: SendMessageRequest): MessageResponse {
        val json = defaultSerializer()
        val response = client.post<HttpResponse> {
            url {
                protocol = URLProtocol.HTTP // TODO change to HTTPS (future)
                host = CHAT_URL
                encodedPath = "$CHAT_MESSAGE/${sendMessageRequest.chatId}"
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${sendMessageRequest.token}")
            }
            body = json.write(sendMessageRequest)
        }
        val jsonBody = response.readText()
        log("Message", "$jsonBody")
        return Json.parse(MessageResponse.serializer(), jsonBody)
    }

    suspend fun markMessageAsRead(markMessageRequest: MarkMessageRequest) : MessageResponse {
        val json = defaultSerializer()
        val response = client.post<HttpResponse> {
            url {
                protocol = URLProtocol.HTTP // TODO change to HTTPS (future)
                host = CHAT_URL
                encodedPath = "/${markMessageRequest.chatId}$CHAT_MESSAGE$CHAT_READ"
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${markMessageRequest.token}")
            }
            body = json.write(markMessageRequest)
            log("Chat", "$body")
        }
        val jsonBody = response.readText()
        log("Chat", jsonBody)
        return Json.parse(MessageResponse.serializer(), jsonBody)
    }
}