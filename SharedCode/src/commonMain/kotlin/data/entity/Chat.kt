package data.entity


import db.ChatShortModel
import db.LastMessageModel
import db.MemberModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val id: Int,
    val title: String?,
    @SerialName("updated") var updated: String?,
    val isAnonymous: Int,
    val members: List<Member>,
    val lastMessage: LastMessage?,
    val avatar: String?
)

@Serializable
data class Member(
    @SerialName("user_id") var userId: Int,
    @SerialName("user_name") var userName: String?,
    @SerialName("lastReadMsg") var lastReadMes: Int,
    val lastAttempt: String,
    @SerialName("unread_count") var unreadCount: Int
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

fun MemberModel.to(): Member {
    return Member(
        user_id.toInt(),
        user_name,
        lastReadMes.toInt(),
        lastAttempt,
        unreadCount.toInt()
    )
}

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
fun toChat(chatShortModel: ChatShortModel, lastMessage: LastMessage?, members: List<Member>): Chat {
    return Chat(
        chatShortModel.id.toInt(),
        chatShortModel.title,
        chatShortModel.updated,
        chatShortModel.isAnonymous?.toInt()!!,
        members,
        lastMessage,
        chatShortModel.avatar
    )
}