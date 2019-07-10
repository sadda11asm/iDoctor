package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatFullData(
    val title: String?,
    var updated: String,
    @SerialName("isAnonymous") var anonymous: Int,
    val messages: MutableList<Message>
)