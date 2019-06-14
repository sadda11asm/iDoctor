package org.kotlin.mpp.mobile.data.api

import domain.Specialization
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType.Application.Json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list

internal expect val ApplicationDispatcher: CoroutineDispatcher


class SpecializationApi(private val engine: HttpClientEngine) {
    private val client = HttpClient()

    fun fetchSpecializations(callback: (List<Specialization>) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val strResult: String = client.get {
                    url("$baseUrl/skills")
                }
                val result = Json.parse(Specialization.serializer(), strResult)
                callback(result)
            }
        }
    }
    companion object {
        private const val baseUrl = "https://idoctor.kz/api"
    }
}