package com.example.mppapp.ui.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.mppapp.R
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import com.example.mppapp.util.getNetworkConnection
import com.example.mppapp.util.getUserId
import kotlinx.android.synthetic.main.activity_chat.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.ChatFullResponse
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.presentation.chat.ChatView
import org.kotlin.mpp.mobile.util.log
import org.kotlin.mpp.mobile.util.constants.BASE_URL

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter by lazy { ServiceLocator.chatPresenter }

    private lateinit var adapter: MessageAdapter

    private val layoutManager = LinearLayoutManager(this)

    private var isMessageSend = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        presenter.attachView(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) { finish() }
        return super.onOptionsItemSelected(item)
    }

    override fun token() = getAccessToken()

    override fun userId() = getUserId()

    override fun chatId() = intent.getIntExtra(EXTRA_CHAT_ID, 0)

    override fun chatSize() = intent.getIntExtra(EXTRA_CHAT_SIZE, 2)

    override fun showMessage(message: Message) {
        adapter.addMessage(message)
    }

    override fun showChat(chatFull: ChatFull) {
        setupToolbar(chatFull.title)
        setupRecycler(chatFull.messages)
        setListeners()
        // TODO refactor
        if (adapter.itemCount > 0)
            presenter.markMessageAsRead(adapter.getMessageId(adapter.itemCount - 1))
    }

    override fun showChatLoadError(e: Exception) {
        Log.d("Chat", " showChatLoadError() ${e.message}")
    }

    private fun setListeners() {
        imageSend.setOnClickListener { sendMessage() }
        recyclerMessages.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            // TODO change position init logic
            val position = if(isMessageSend) adapter.itemCount else adapter.itemCount - 1
            isMessageSend = false
            layoutManager.scrollToPosition(position)
        }
        editMessage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1 && before == 0 && start == 0) {
                    setVisibilities(View.VISIBLE, View.GONE, View.GONE)
                } else if(count == 0 && before != 0 && start == 0) {
                    setVisibilities(View.GONE, View.VISIBLE, View.VISIBLE)
                }
            }
        })
    }

    private fun setVisibilities(send: Int, attachment: Int, camera: Int) {
        imageSend.visibility = send
        imageAttachment.visibility = attachment
        imageCamera.visibility = camera
    }


    private fun setupToolbar(title: String?) {
        if (chatSize() > 2)
            textTitle.text = title
        else {
            val names = title!!.split(',')
            if (names.size < 2) {
                textTitle.text = title
            } else {
                if (names[0] == getName())
                    textTitle.text = names[1]
                else
                    textTitle.text = names[0]
            }
        }
        loadIntoAvatar()
    }

    private fun setupRecycler(messages: MutableList<Message>) {
        val userId = getUserId()
        adapter = MessageAdapter(this, messages, userId)
        recyclerMessages.layoutManager = layoutManager
        recyclerMessages.adapter = adapter
    }

    private fun loadIntoAvatar() {
        val avatar = "$BASE_URL${intent.getStringExtra(EXTRA_AVATAR)}"
        Glide.with(this)
            .load(avatar)
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

    override fun getConnection(): Boolean {
        return getNetworkConnection(this)
    }

    companion object {
        const val EXTRA_CHAT_ID = "extra_chat_id"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_CHAT_SIZE = "extra_chat_size"


        fun open(context: Context, chatId: Int) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(EXTRA_CHAT_ID, chatId)
            context.startActivity(intent)
        }
    }
}
