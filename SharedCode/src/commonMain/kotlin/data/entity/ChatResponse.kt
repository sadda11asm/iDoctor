package data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ChatResponse(
    val data: List<Chat>
)