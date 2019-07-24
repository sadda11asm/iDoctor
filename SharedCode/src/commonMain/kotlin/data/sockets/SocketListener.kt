package org.kotlin.mpp.mobile

import org.kotlin.mpp.mobile.data.entity.Message

interface SocketListener {
    fun onMessage(mes: String)
}