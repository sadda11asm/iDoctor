package com.example.mppapp.ui.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.util.getAccessToken
import kotlinx.android.synthetic.main.activity_chat.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.presentation.chat.ChatView

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter by lazy { ServiceLocator.chatPresenter }

//    private val userId = intent.getIntExtra(EXTRA_USER_ID, 0)
//
//    private val chatId = intent.getIntExtra(EXTRA_CHAT_ID, 0)
//
//    private val avatar = intent.getStringExtra(EXTRA_AVATAR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        presenter.attachView(this)

//        // TODO remove
//        val mockMessages = mutableListOf(
//            Message(1, 2, 3, "new message", "b", "b"),
//            Message(1, 2, 3, "new 2", "b", "b")
//        )
//        recyclerMessages.layoutManager = LinearLayoutManager(this)
//        recyclerMessages.adapter = MessageAdapter(mockMessages, this)
    }

    override fun token() = getAccessToken()

    override fun showChat(chatFullResponse: ChatFullResponse) {
        Log.d("Chat", "ShowChat()")
        setupToolbar(chatFullResponse.data.title)
        setupAdapter(chatFullResponse.data.messages)
    }

    override fun showError(e: Exception) {
        Log.d("Chat", "${e.stackTrace}")
    }

    private fun setupToolbar(title: String?) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle.text = title ?: resources.getString(R.string.anonymous)

        Glide
            .with(this)
            .load("https://i.pinimg.com/originals/a6/3b/80/a63b807cc485fe11b685746134e07607.jpg")
            .error(R.drawable.default_avatar)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageAvatar)
    }

    private fun setupAdapter(messages: MutableList<Message>) {
        recyclerMessages.layoutManager = LinearLayoutManager(this)
        recyclerMessages.adapter = MessageAdapter(messages, this)
    }

    companion object {
        const val EXTRA_CHAT_ID = "extra_chat_id"
        const val EXTRA_AVATAR = "extra_avatar"

        fun open(context: Context, chatId: Int, avatar: String?) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(EXTRA_CHAT_ID, chatId)
            intent.putExtra(EXTRA_AVATAR, avatar)
            context.startActivity(intent)
        }
    }
}
