package data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.kotlin.mpp.mobile.data.entity.DoctorResponse
import org.kotlin.mpp.mobile.data.entity.ScheduleRequest
import org.kotlin.mpp.mobile.data.entity.ScheduleResponse
import org.kotlin.mpp.mobile.data.entity.ignoreOutgoingContent
import org.kotlin.mpp.mobile.util.constants.*
import org.kotlin.mpp.mobile.util.log

class DoctorApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }
    }

    suspend fun getDoctors(token: String, page: Int): DoctorResponse {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = API_URL
                encodedPath = API_DOCTORS
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                parameter(PARAM_PAGE, page)
                parameter(PARAM_ACCOUNT, "HAS")
            }
            accept(ContentType.Application.Json)
        }
        val jsonBody = response.readText()
        log(value = jsonBody)
        return Json.nonstrict.parse(DoctorResponse.serializer(), jsonBody)
    }

    suspend fun getSchedule(request: ScheduleRequest): ScheduleResponse {
        val response = client.get<HttpResponse> {
            url{
                protocol = URLProtocol.HTTPS
                host = API_URL
                encodedPath = "$API_SCHEDULE/${request.id}/jobs"
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${request.token}")
            }
            accept(ContentType.Application.Json)
        }
        val jsonBody = response.readText()
        log("Schedule", jsonBody)
        return Json.nonstrict.parse(ScheduleResponse.serializer(), jsonBody)
    }
}