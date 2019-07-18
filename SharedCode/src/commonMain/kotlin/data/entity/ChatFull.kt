package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatFull(
    val title: String?,
    var updated: String,
    @SerialName("isAnonymous") var anonymous: Int,
    var avatar: String?,
    var lastReadMessage: Int?,
    val messages: MutableList<Message>,
    var id: Int = -1
)

@Serializable
data class LastReadMessage(
    @SerialName("user_id") var userId: Int,
    @SerialName("mes_id") var mesId: Int
)
