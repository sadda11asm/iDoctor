package com.example.mppapp.model

import com.example.mppapp.R

class UnreadViewModel(val unreadCount: Int) : ViewModel() {
    override fun type() = R.layout.item_unread_message
}