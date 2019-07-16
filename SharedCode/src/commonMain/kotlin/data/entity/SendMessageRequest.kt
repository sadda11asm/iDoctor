package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(
    val token: String,
    val chatId: Int,
    @SerialName("message") val messageText: String
)