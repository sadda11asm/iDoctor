package org.kotlin.mpp.mobile.data.entity

import io.ktor.client.features.json.JsonSerializer
import io.ktor.http.content.OutgoingContent


/**
 * Workaround for list serializing issue
 */
class IgnoreOutgoingContentJsonSerializer(private val delegate: JsonSerializer) : JsonSerializer by delegate {
    override fun write(data: Any): OutgoingContent {
        if (data is OutgoingContent) {
            return data
        }
        return delegate.write(data)
    }
}

fun JsonSerializer.ignoreOutgoingContent() = IgnoreOutgoingContentJsonSerializer(this)