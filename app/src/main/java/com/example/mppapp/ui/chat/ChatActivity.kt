package com.example.mppapp.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
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
            Message("Hey ", "me", "12:32"),
            Message("You", "me", "12:32"),
            Message("4", "me", "12:32"),
            Message("5", "me", "12:32"),
            Message("He", "me", "12:32")
        )

        recyclerMessages.layoutManager = LinearLayoutManager(this)
        recyclerMessages.adapter = MessageAdapter(messages, this)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle.text = "Владимир К."

        Glide
            .with(this)
            .load("https://i.pinimg.com/originals/a6/3b/80/a63b807cc485fe11b685746134e07607.jpg")
            .error(R.drawable.default_avatar)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageAvatar)
    }
}
