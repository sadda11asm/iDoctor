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
import com.example.mppapp.R
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import com.example.mppapp.util.getNetworkConnection
import com.example.mppapp.util.getUserId
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_chat.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.presentation.chat.ChatView
import org.kotlin.mpp.mobile.util.log
import org.kotlin.mpp.mobile.util.constants.BASE_URL

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter by lazy { ServiceLocator.chatPresenter }

    private val layoutManager = LinearLayoutManager(this)

    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setupToolbar()
        showAvatar()
        Slidr.attach(this)
    }

    override fun onStart() {
        super.onStart()
        log("Sockets", "START2")
        ServiceLocator.setSocketListener(presenter)
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun token() = getAccessToken()

    override fun userId() = getUserId()

    override fun chatId() = intent.getIntExtra(EXTRA_CHAT_ID, 0)

    override fun chatSize() = intent.getIntExtra(EXTRA_CHAT_SIZE, 2)

    override fun isConnectedToNetwork() = getNetworkConnection(this)

    override fun showMessage(message: Message) {
        adapter.addMessage(message)
        recyclerMessages.scrollToPosition(layoutManager.itemCount - 1)
    }

    override fun showChat(chatFull: ChatFull) {
        setupRecycler(chatFull.messages)
        setListeners()
        // TODO refactor
        if (adapter.itemCount > 0) {
            presenter.markMessageAsRead(adapter.getMessageId(adapter.itemCount - 1))
        }
    }

    override fun showChatLoadError(e: Exception) {
        Log.d("Chat", " showChatLoadError() ${e.message}")
    }

    private fun setListeners() {
        imageSend.setOnClickListener { sendMessage() }
        fabDown.setOnClickListener { recyclerMessages.scrollToPosition(adapter.itemCount - 1) }
        recyclerMessages.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                recyclerMessages.scrollToPosition(layoutManager.itemCount - 1)
            }
        }
        editMessage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            // TODO change logic
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                if (count == 1 && before == 0 && start == 0) {
                    setVisibilities(View.VISIBLE, View.GONE, View.GONE)
                } else if (count == 0 && before != 0 && start == 0) {
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

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        textTitle.text = intent.getStringExtra(EXTRA_TITLE)
    }

    private fun showAvatar() {
        val avatar = intent.getStringExtra(EXTRA_AVATAR)
        Glide.with(this)
            .load(avatar)
            .error(R.drawable.default_avatar)
            .into(imageAvatar)
    }

    private fun setupRecycler(messages: MutableList<Message>) {
        val userId = getUserId()
        adapter = MessageAdapter(this, messages, userId)
        recyclerMessages.layoutManager = layoutManager
        recyclerMessages.adapter = adapter
        recyclerMessages.addOnScrollListener(object : FabScrollListener(layoutManager) {
            override fun changeFabState(isNotLastItem: Boolean) {
                if (isNotLastItem) {
                    fabDown.show()
                } else {
                    fabDown.hide()
                }
            }
        })
    }

    private fun sendMessage() {
        val messageText = editMessage.text.toString()
        editMessage.text = null
        presenter.sendMessage(messageText)
    }

    companion object {
        const val EXTRA_CHAT_ID     = "extra_chat_id"
        const val EXTRA_CHAT_SIZE   = "extra_chat_size"
        const val EXTRA_AVATAR      = "extra_avatar"
        const val EXTRA_TITLE       = "extra_title"

        fun open(context: Context, chatId: Int, avatar: String?, title: String?) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(EXTRA_CHAT_ID, chatId)
            intent.putExtra(EXTRA_AVATAR, avatar)
            intent.putExtra(EXTRA_TITLE, title)
            context.startActivity(intent)
        }
    }
}
