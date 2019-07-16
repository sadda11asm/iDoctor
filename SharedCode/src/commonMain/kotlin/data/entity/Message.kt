package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kotlin.mpp.mobile.util.currentTime

@Serializable
data class Message(
    val id: Int,
    @SerialName("chat_id") val chatId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("message") val text: String,
    @SerialName("created_at") var createdAt: String? = currentTime,
    @SerialName("updated_at") var updatedAt: String? = currentTime
)