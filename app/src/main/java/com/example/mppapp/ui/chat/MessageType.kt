package com.example.mppapp.ui.chat


enum class MessageType {

    SENT_MESSAGE {
        override fun type() = 0

    },
    RECEIVED_MESSAGE {
        override fun type() = 1
    };

    abstract fun type(): Int
}