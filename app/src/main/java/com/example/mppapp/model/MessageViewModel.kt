package com.example.mppapp.model

import com.example.mppapp.R
import org.kotlin.mpp.mobile.data.entity.Message

class MessageViewModel(val message: Message, private val isSent: Boolean) : ViewModel() {
    override fun type() = if (isSent) R.layout.item_sent_message else R.layout.item_received_message
}