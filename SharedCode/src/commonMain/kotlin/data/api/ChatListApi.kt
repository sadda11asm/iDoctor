package data.api

import data.entity.Chat
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.kotlin.mpp.mobile.data.entity.ignoreOutgoingContent
import org.kotlin.mpp.mobile.util.constants.TEMP_URL
import org.kotlin.mpp.mobile.util.log

class ChatListApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }

    }

    suspend fun getChatList(token: String): List<Chat> {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = TEMP_URL
                encodedPath = "/"
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "Bearer $token")
            }
        }
        val jsonBody = response.readText()
        log("CHATLIST", jsonBody)
        return Json.nonstrict.parse(Chat.serializer().list, jsonBody)
    }

    companion object {
        private const val BASE_URL = "172.20.21.164:8000/"
        private const val ENCODED_PATH = ""
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_CONTENT = "Content-Type"
        private const val CONTENT_TYPE = "application/json"
    }
}