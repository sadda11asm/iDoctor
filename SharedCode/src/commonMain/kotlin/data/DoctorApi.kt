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

    suspend fun getDoctors(token: String): DoctorResponse {
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
        private const val BASE_URL = "cabinet.idoctor.kz/api/doctors"
        private const val ENCODED_PATH = ""
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_CONTENT = "Content-Type"
//        private const val TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjFjOWRlN2NkODUzMzUxNTcxYmJiNTNjNTVkMTVkNTY0MWU0NzJhM2E4N2JhOGM2YmU4ZDE1MjllYjI4NTIxNDAxN2ZjOTI0YTRkMzMwMDdlIn0.eyJhdWQiOiIxMyIsImp0aSI6IjFjOWRlN2NkODUzMzUxNTcxYmJiNTNjNTVkMTVkNTY0MWU0NzJhM2E4N2JhOGM2YmU4ZDE1MjllYjI4NTIxNDAxN2ZjOTI0YTRkMzMwMDdlIiwiaWF0IjoxNTYwOTU0NTY4LCJuYmYiOjE1NjA5NTQ1NjgsImV4cCI6MTU5MjU3Njk2OCwic3ViIjoiMTE2NyIsInNjb3BlcyI6WyIqIl19.Z__GCP4A3_vZEv3Q-WU9JdV4Zf2N5QbHMAXNvj9AbiwiMee9ZLRMsVtWNFydwrgr_WlyqwlvUv4LHuUQ6hlJJb2jNb8gHIQJGn1lJmSku2InyOPyHStnyTEG6iQqhi0RVuX99WAkdPrE6y9c5DNR727ZfS59DL1Ako1h4tDwRrMZpn7DG1zMr5ST1-ddazRTZ34VFtZGyYypwuuT4c6dENIBhNZa-UJdZtiXDMl97q2mNZc0L7E4oJ2xD3IzwzbKLQj00Zl2MwTbEPXGtkJoYADgxa1voy3t37D4T_BqqEhsQRDi9AxF59vyIaFkAp1deEahrfiQI2-cTtS1_RV8CqsplTpRAVdlBm9Jns9qKhEK1bx_expRMXHxbNn-IJpU51L7Jp1-yBO-0NhmRSE4lylXbGOHXTGjkufsQuDVOWeK8DdytI0cwAiYNBFQMrfQFQXO-svjNp4Nj7e_nBLTaHM2tnCWw3WEVqJT-SFt_9mMbeQdXmAZuTJx-bfIOgwkmTe8XCKGEIVyc7jV8McNP3248cq9BS5rD8X-Nnj3VDT05IEhrobeADbtz59HgWU8lLE0fWB7QiYy8wui0ilc4LoNCEjNAMXzjMKi8Ee6yS4GpFSCQSAKxHv73wko6GZbkZE4MrAAFJip4ZM5i9lXaPbaX_vD7cZH_ECD64w8ZdQ"
        private const val CONTENT_TYPE = "application/json"
    }
}