package org.kotlin.mpp.mobile.data.entity

import data.entity.Chat
import kotlinx.serialization.Serializable


@Serializable
class ChannelMessage (
    var event: String?,
    var data: Data?,
    var socket: String?
)

@Serializable
class Data(
    var message: Message? = null,
    var chat: Chat? = null,
    var socket: String?
)