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

class DoctorApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }

    }

    suspend fun getDoctors(): DoctorResponse {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = ENCODED_PATH
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, TOKEN)
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
        private const val TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFjMzg3OTcxMWQwNTI1YzhmMDNhNWZiMjM1MWM4ZWIwNWM5NWUwNjY0NmY4ZmFiYTJkYmYyOTc0ZjJkMmIxNjE1MmE2OWUzMTEzNDc0Zjk1In0.eyJhdWQiOiIxMyIsImp0aSI6ImFjMzg3OTcxMWQwNTI1YzhmMDNhNWZiMjM1MWM4ZWIwNWM5NWUwNjY0NmY4ZmFiYTJkYmYyOTc0ZjJkMmIxNjE1MmE2OWUzMTEzNDc0Zjk1IiwiaWF0IjoxNTYwOTMzMjc2LCJuYmYiOjE1NjA5MzMyNzYsImV4cCI6MTU5MjU1NTY3Niwic3ViIjoiMTE2NyIsInNjb3BlcyI6WyIqIl19.dCAbHVO8ycj6h5MU4Ov91YNyyFHIpOAQj3Pps0VrUVtEWcb_TWekhZUsEnEZ3WuevW1V8SfLrZFng37YPRpW5dJ3fuQW3gSJXa5FfVuht29aT45RKKDHlU0i7hCHoLB7olVg53TDlrXdmJPXCldwCID8xIpBLeHgOZcMkTCWjBnvFkftiX7O0xZauv_vJsNTDgeBEQ7FTPpW0E20jmUFYBPMo9zKaq8tX8IvSefdBcLVgPvmYU1yEjBstUxabT7PZC_BKaOs4fiIMLpIWv-hKd8nYlVUXsb3ovXkotLYZNAfmO0TCAwpPabjCMvRi2bm-kkHONRloDXQHJngEGVcIG9aUYNQZFnukIgO8XPbsiagGBa_HvqS1HTzaxxJ4YB7RF9FLKGjKtYGUyJEAvBYd_b0NVQZJO2sTwwjUWttUpS1_CSgcUxhKlWArHXpYaiHFEN98Q7e4sAGdNdqXt93Fv7yDrFnu8yFxw_1VNIr0iaNQwL5EF_Xp2SNMf06R_24SbRcaGkW297vokVG19SJljsMHEMdMZySSqZDb7_HuuDo_ZEnA_NKH6V54fjRAZskEdogLoXacZ33EBFeW72GKoFFqX3mUl67iqbdi9F1vpjoEowifnjXe83u180aroa3Zv6X8vYky2la0qgU2_w48dDk_geONqpBhL9v9zK3a1I"
        private const val CONTENT_TYPE = "application/json"
    }
}