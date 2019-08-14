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
import com.example.mppapp.model.DateViewModel
import com.example.mppapp.model.MessageViewModel
import com.example.mppapp.model.UnreadViewModel
import com.example.mppapp.model.ViewModel
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getNetworkConnection
import com.example.mppapp.util.getUserId
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_chat.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.ChatFull
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.presentation.chat.ChatView
import org.kotlin.mpp.mobile.util.log

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter by lazy { ServiceLocator.chatPresenter }

    private val layoutManager = LinearLayoutManager(this)

    private val currentUserId = getUserId()

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

    override fun userId() = currentUserId

    override fun chatId() = intent.getIntExtra(EXTRA_CHAT_ID, 0)

    override fun chatSize() = intent.getIntExtra(EXTRA_CHAT_SIZE, 2)

    override fun isConnectedToNetwork() = getNetworkConnection(this)

    override fun showMessage(message: Message) {
        val isSent = message.userId == currentUserId
        adapter.addMessage(MessageViewModel(message, isSent))
        recyclerMessages.scrollToPosition(layoutManager.itemCount - 1)
    }

    override fun showChat(chatFull: ChatFull) {
        val pair = presenter.getLastReadMessageUnreadPair(chatFull)
        setupRecycler(chatFull.messages, pair)
        setListeners()
        presenter.markMessageAsRead(adapter.getLastMessageId())
    }

    override fun showChatLoadError(e: Exception) {
        Log.d("Chat", " showChatLoadError() ${e.message}")
    }

    private fun setListeners() {
        fabSend.setOnClickListener { sendMessage() }
        fabDown.setOnClickListener { recyclerMessages.scrollToPosition(adapter.itemCount - 1) }
        recyclerMessages.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                recyclerMessages.scrollToPosition(layoutManager.itemCount - 1)
            }
        }
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

    private fun setupRecycler(messages: MutableList<Message>, lastReadMesUnreadPair: Pair<Int, Int>) {
        val mappedMessages = mapMessagesToViewModels(messages, lastReadMesUnreadPair)
        adapter = MessageAdapter(this, mappedMessages)
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

    private fun mapMessagesToViewModels(messages: MutableList<Message>, lastReadMesUnreadPair: Pair<Int, Int>): MutableList<ViewModel> {
        val mappedMessages = mutableListOf<ViewModel>()
        var currentDate = " "
        var isUnreadAdded = false
        for (message in messages) {
            val date = message.createdAt?.substring(0, 10)
            if(currentDate != date) {
                mappedMessages.add(DateViewModel(getFormattedDate(date!!)))
                currentDate = date
            }
            if(message.id > lastReadMesUnreadPair.first && !isUnreadAdded) {
                log("CHATHOLDER", "${message.id} ${lastReadMesUnreadPair.first}")
                mappedMessages.add(UnreadViewModel(lastReadMesUnreadPair.second))
                isUnreadAdded = true
            }
            val isSent = message.userId == currentUserId
            mappedMessages.add(MessageViewModel(message, isSent))
        }
        return mappedMessages
    }

    // TODO refactor getFormattedDate()
    private fun getFormattedDate(date: String): String {
        val year = date.substring(0, 4)
        val month = date.substring(5, 7).toInt()
        val day = date.substring(8, 10).toInt()

        val dates = resources.getStringArray(R.array.dates)
        return "$day ${dates[month - 1]} $year"
    }

    private fun sendMessage() {
        val messageText = editMessage.text.toString()
        editMessage.text = null
        presenter.sendMessage(messageText)
    }

    companion object {
        const val EXTRA_CHAT_ID = "extra_chat_id"
        const val EXTRA_CHAT_SIZE = "extra_chat_size"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_TITLE = "extra_title"

        fun open(context: Context, chatId: Int, avatar: String?, title: String?) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(EXTRA_CHAT_ID, chatId)
            intent.putExtra(EXTRA_AVATAR, avatar)
            intent.putExtra(EXTRA_TITLE, title)
            context.startActivity(intent)
        }
    }
}