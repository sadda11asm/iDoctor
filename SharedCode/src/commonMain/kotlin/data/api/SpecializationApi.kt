package org.kotlin.mpp.mobile.data.api

import domain.Specialization
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.takeFrom
import io.ktor.client.request.url
import io.ktor.http.takeFrom
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

internal expect val ApplicationDispatcher: CoroutineDispatcher


class SpecializationApi {

    private val apiKey = "FoRxqxRzpB3JStCEGITPPKicE8MTa67q"

    private val client: HttpClient = HttpClient { // 1
        install(JsonFeature) { // 2
            serializer = KotlinxSerializer(Json.nonstrict).apply { // 3
                setMapper(Specialization::class, Specialization.serializer()) // 4
            }
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String) { // 5
        url { // 6
            takeFrom("https://api.giphy.com/") // 7
            encodedPath = path // 8
        }
    }

    suspend fun callSpecializationApi(): Specialization = client.get {
        apiUrl(path = "v1/gifs/search?api_key=$apiKey&q=whoa&limit=25&offset=0&rating=G&lang=en")
    }

    fun fetchSpecializations(callback: (List<Specialization>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: Specialization = callSpecializationApi()
//                val urls = result.data.map {
//                    it.images.original.url
//                }
//                callback(urls)
            }
        }
    }
    companion object {
        private const val baseUrl = "https://idoctor.kz/api"
    }
}