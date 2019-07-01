package data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import io.ktor.http.content.OutgoingContent
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.data.entity.ignoreOutgoingContent

class ChatListApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }

    }

    suspend fun getChatList(token: String): DoctorResponse {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = ENCODED_PATH
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "Bearer $token")
            }
        }
        val jsonBody = response.readText()
        return Json.nonstrict.parse(DoctorResponse.serializer(), jsonBody)
    }

    companion object {
        private const val BASE_URL = "cabinet.idoctor.kz/api"
        private const val ENCODED_PATH = "/doctors"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_CONTENT = "Content-Type"
        private const val CONTENT_TYPE = "application/json"
    }
}