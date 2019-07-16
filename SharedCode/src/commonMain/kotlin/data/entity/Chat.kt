package data.entity


import db.ChatShortModel
import db.LastMessageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val id: Int,
    val title: String?,
    @SerialName("created_at") var createdAt: String?,
    @SerialName("updated_at") var updatedAt: String?,
    @SerialName("deleted_at") var deletedAt: String?,
    val users: List<Int>,
    val lastMessage: LastMessage?,
    val avatar: String?
)

@Serializable
data class LastMessage(
    val id: Int,
    val message: String,
    @SerialName("chat_id") var chatId: Int?,
    @SerialName("user_id") var userId: Int?,
    @SerialName("created_at") var createdAt: String?,
    @SerialName("updated_at") var updatedAt: String?
    )

fun LastMessageModel.to(): LastMessage {
    return LastMessage(
        id.toInt(),
        message,
        chat_id.toInt(),
        user_id.toInt(),
        created_at,
        updated_at

    )
}
fun toChat(chatShortModel: ChatShortModel, lastMessage: LastMessage?): Chat {
    return Chat(
        chatShortModel.id.toInt(),
        chatShortModel.title,
        chatShortModel.created_at,
        chatShortModel.updated_at,
        chatShortModel.deleted_at,
        chatShortModel.users!!,
        lastMessage,
        chatShortModel.avatar
    )
}