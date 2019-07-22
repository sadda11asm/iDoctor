package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.currentTime

@Serializable
data class Message(
    val id: Int,
    @SerialName("author") val userId: Int,
    @SerialName("message") val text: String,
    @SerialName("created") var createdAt: String? = currentTime
)