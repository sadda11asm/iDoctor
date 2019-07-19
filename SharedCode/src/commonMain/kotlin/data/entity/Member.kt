package data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Member(
    @SerialName("user_id") val userId: Int,
    @SerialName("user_name") var username: String?,
    @SerialName("lastReadMsg") var lastReadMessage: Int?,
    var lastAttempt: String?,
    @SerialName("unread_count") var unreadCount: Int = 0
)