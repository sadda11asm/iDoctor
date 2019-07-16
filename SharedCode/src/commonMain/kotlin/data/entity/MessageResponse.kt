package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerialName("message_id") val messageId: Int
)