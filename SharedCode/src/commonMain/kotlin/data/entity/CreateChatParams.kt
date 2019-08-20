package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateChatParams(
    val token: String,
    val title: String,
    @SerialName("user_id") val userId: Int,
    val anonymous: Boolean,
    @SerialName("doctor_id") val doctorId: Int?
)