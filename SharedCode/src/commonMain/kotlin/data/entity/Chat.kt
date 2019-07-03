package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val id: Int,
    val title: String,
    @SerialName("created_at") var createdAt: String,
    @SerialName("updated_at") var updatedAt: String,
    @SerialName("deleted_at") var deletedAt: String?,
    val user_id: Int
)