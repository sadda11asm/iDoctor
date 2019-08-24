package data.api

import data.entity.UserEditResponse
import data.entity.UserFull
import data.entity.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import org.kotlin.mpp.mobile.data.entity.PasswordEditRequest
import org.kotlin.mpp.mobile.data.entity.PasswordEditResponse
import org.kotlin.mpp.mobile.data.entity.UserEditRequest
import org.kotlin.mpp.mobile.data.entity.ignoreOutgoingContent
import org.kotlin.mpp.mobile.util.constants.*
import org.kotlin.mpp.mobile.util.log


class UserApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().ignoreOutgoingContent()
        }
    }

    suspend fun getUserInfo(token: String): UserResponse {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = API_URL
                encodedPath = API_USER
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
            }
            accept(ContentType.Application.Json)
        }
        val jsonBody = response.readText()
        log("UserApi", jsonBody)
        return Json.nonstrict.parse(UserResponse.serializer(), jsonBody)
    }

    suspend fun editUserInfo(request: UserEditRequest): UserFull {
        val userId = request.user.id
        val token = request.token
        val response = client.patch<HttpResponse>{
            url {
                protocol = URLProtocol.HTTPS
                host = API_URL
                encodedPath = "$API_USER_EDIT/$userId"
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                parameter("firstname", request.user.firstName)
                parameter("lastname", request.user.lastName)
                parameter("patronymic", request.user.patronymic)
                parameter("birthday", request.user.birthday)
                parameter("phone", request.user.phone)
                parameter("email", request.user.email)
            }
            accept(ContentType.Application.Json)
        }
        val jsonBody = response.readText()
        return Json.nonstrict.parse(UserEditResponse.serializer(), jsonBody).user
    }


    suspend fun editPassword(request: PasswordEditRequest): PasswordEditResponse {
        val userId = request.userId
        val token = request.token
        val response = client.patch<HttpResponse>{
            url {
                protocol = URLProtocol.HTTPS
                host = API_URL
                encodedPath = "$API_USER_EDIT/$userId/password"
                header(HEADER_CONTENT, CONTENT_TYPE)
                header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                parameter("current_password", request.currentPassword)
                parameter("password", request.password)
                parameter("password_confirm", request.confirmPassword)
            }
            accept(ContentType.Application.Json)
        }
        val jsonBody = response.readText()
        return Json.nonstrict.parse(PasswordEditResponse.serializer(), jsonBody)
    }
}