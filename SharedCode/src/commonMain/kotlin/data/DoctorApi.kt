package data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.Post

class DoctorApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getDoctors(): Post {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = ENCODED_PATH
            }
        }
        val jsonBody = response.readText()
        return Json.parse(Post.serializer(), jsonBody)
    }

    companion object {
        private const val BASE_URL = "jsonplaceholder.typicode.com"
        private const val ENCODED_PATH = "/posts/1"
    }
}