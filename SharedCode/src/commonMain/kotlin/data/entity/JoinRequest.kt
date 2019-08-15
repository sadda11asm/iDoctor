package org.kotlin.mpp.mobile.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class JoinRequest(
    val chats: MutableList<Int>
)