package org.kotlin.mpp.mobile.data

import data.entity.CreateChatRequest
import data.entity.CreateChatResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse
import org.kotlin.mpp.mobile.data.entity.ignoreOutgoingContent
import org.kotlin.mpp.mobile.util.constants.*
import org.kotlin.mpp.mobile.util.log

class ChatFullApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }
    }

    suspend fun getChatFull(token: String, chatId: Int): ChatFullResponse {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTP // TODO change to HTTPS (future)
                host = CHAT_URL
                encodedPath = "$API_CHAT/$chatId"
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
            }
        }
        val jsonBody = response.readText()
        return Json.nonstrict.parse(ChatFullResponse.serializer(), jsonBody)
    }

    suspend fun createChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int?):Int {
            val json = defaultSerializer()
            val response = client.post<HttpResponse> {
                url{
                    protocol = URLProtocol.HTTP
                    host = TEMP_URL
                    encodedPath = "/chat"
                    header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                }
                body = json.write(CreateChatRequest(title, userId, anonymous, doctorId))
                accept(ContentType.Application.Json)
            }
            val jsonBody = response.readText()
            log("CREATE-CHAT", jsonBody)
            return Json.nonstrict.parse(CreateChatResponse.serializer(), jsonBody).chatId
        }
}