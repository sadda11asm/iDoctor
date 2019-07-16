package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateChatResponse(
    @SerialName("chat_id")val chatId: Int
)