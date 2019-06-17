package data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import org.kotlin.mpp.mobile.data.Post

class DoctorApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getDoctors(): Post {

    }

    companion object {
        private const val BASE_URL = "jsonplaceholder.typicode.com"
        private const val ENCODED_PATH = "/posts/1"
    }
}