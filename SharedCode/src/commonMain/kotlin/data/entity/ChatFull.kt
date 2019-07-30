package org.kotlin.mpp.mobile.data.entity

import data.entity.Member
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatFull(
    var id: Int,
    val title: String?,
    @SerialName("updated_at") var updated: String,
    @SerialName("isAnonymous") var anonymous: Int,
    var avatar: String?,
    val members: MutableList<Member>,
    val messages: MutableList<Message>
)
