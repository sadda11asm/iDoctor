package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: Int,
    @SerialName("chat_id") val chatId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("message") val text: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") var updatedAt: String
)