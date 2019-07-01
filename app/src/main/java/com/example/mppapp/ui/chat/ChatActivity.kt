package com.example.mppapp.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mppapp.R
import kotlinx.android.synthetic.main.activity_chat.*
import org.kotlin.mpp.mobile.data.entity.Message

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //TODO remove mock data
        val messages = listOf(
            Message("Hello buddy", "me", "12:32"),
            Message("Hello buddy", "me", "12:32"),
            Message("Hello buddy", "me", "12:32"),
            Message("Hello buddy", "me", "12:32"),
            Message("Hello buddy", "me", "12:32"),
            Message("Hello buddy", "me", "12:32")
        )

        recyclerMessages.layoutManager = LinearLayoutManager(this)
        recyclerMessages.adapter = MessageAdapter(messages, this)
    }
}
