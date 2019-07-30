package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.currentTime

@Serializable
data class Message(
    val id: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("message") val text: String,
    @SerialName("created_at") var createdAt: String? = currentTime,
    @SerialName("chat_id") var chatId: Int? = -1
)