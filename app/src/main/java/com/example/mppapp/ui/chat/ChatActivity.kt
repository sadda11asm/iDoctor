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

    private lateinit var adapter: MessageAdapter

    private val layoutManager = LinearLayoutManager(this)

    private var isMessageSend = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        presenter.attachView(this)
    }

    override fun token() = getAccessToken()

    override fun chatId() = intent.getIntExtra(EXTRA_CHAT_ID, 28) // TODO change default value to 0

    override fun showMessage(message: Message) {
        adapter.addItem(message)
    }

    override fun showChat(chatFullResponse: ChatFullResponse) {
        setupToolbar(chatFullResponse.data.title)
        setupRecycler(chatFullResponse.data.messages)
        setListeners()
    }

    override fun showChatLoadError(e: Exception) {
        Log.d("Chat", "${e.message}")
    }

    private fun setListeners() {
        fabSend.setOnClickListener { sendMessage() }
        recyclerMessages.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            // TODO change position init logic
            val position = if(isMessageSend) adapter.itemCount else adapter.itemCount - 1
            isMessageSend = false
            layoutManager.scrollToPosition(position)
        }
    }

    private fun setupToolbar(title: String?) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        textTitle.text = title
        loadIntoAvatar()
    }

    private fun setupRecycler(messages: MutableList<Message>) {
//        val userId = getUserId()
        val userId = 1167 // TODO change hardcoded userId
        adapter = MessageAdapter(this, messages, userId)
        recyclerMessages.addOnScrollListener(object : ScrollStopListener(layoutManager) {
            override fun performAction(position: Int) {
                presenter.markMessageAsRead(position)
            }
        })
        recyclerMessages.layoutManager = layoutManager
        recyclerMessages.adapter = adapter
    }

    private fun loadIntoAvatar() {
//        val avatar = intent.getStringExtra(EXTRA_AVATAR)
        // TODO change hardcoded string url
        Glide.with(this)
            .load("https://i.pinimg.com/originals/a6/3b/80/a63b807cc485fe11b685746134e07607.jpg")
            .error(R.drawable.default_avatar)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageAvatar)
    }

    private fun sendMessage() {
        val messageText = editMessage.text.toString()
        isMessageSend = true
        editMessage.text = null
        presenter.sendMessage(messageText)
    }

    companion object {
        const val EXTRA_CHAT_ID = "extra_chat_id"
        const val EXTRA_AVATAR = "extra_avatar"

        fun open(context: Context, chatId: Int, avatar: String) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(EXTRA_CHAT_ID, chatId)
            intent.putExtra(EXTRA_AVATAR, avatar)
            context.startActivity(intent)
        }
    }
}
