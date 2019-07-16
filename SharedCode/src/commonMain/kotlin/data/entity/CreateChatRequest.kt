package data.entity

import kotlinx.serialization.Serializable


@Serializable
data class CreateChatRequest(
    val title: String,
    val user_id: Int,
    val anonymous: Boolean,
    val doctor_id: Int?
)