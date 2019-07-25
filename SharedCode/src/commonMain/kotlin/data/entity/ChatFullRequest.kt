package org.kotlin.mpp.mobile.data.entity

class ChatFullRequest(
    val token: String,
    val chatId: Int,
    val connection: Boolean,
    val cached: Boolean
)