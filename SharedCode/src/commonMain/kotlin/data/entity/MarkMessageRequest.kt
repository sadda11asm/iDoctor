package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarkMessageRequest(
    val token: String,
    val chatId: Int,
    @SerialName("message_id") val messageId: Int
)