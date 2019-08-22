package com.example.mppapp.ui.chatlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mppapp.R
import com.example.mppapp.ui.chat.ChatActivity
import com.example.mppapp.util.ItemClickListener
import com.example.mppapp.util.getAccessToken
import com.example.mppapp.util.getName
import com.example.mppapp.util.getNetworkConnection
import data.entity.Chat
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.kotlin.mpp.mobile.ServiceLocator
import org.kotlin.mpp.mobile.data.entity.Message
import org.kotlin.mpp.mobile.presentation.chatlist.ChatListView
import org.kotlin.mpp.mobile.util.log


class ChatListFragment : Fragment(), ChatListView, ItemClickListener<Chat> {

    private val TAG = "ChatListFragment"

    private val presenter by lazy { ServiceLocator.chatListPresenter }

    private val adapter by lazy { ChatAdapter(context!!, this) }

    private val layoutManager by lazy { LinearLayoutManager(context) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerChats.layoutManager = layoutManager
        recyclerChats.adapter = adapter
        setSwipeListener()
    }

    override fun onClick(data: Chat) {
        ChatActivity.open(
            this.context!!,
            data.id,
            data.formattedAvatar,
            data.getFormattedTitle(data.members.size, getName())
        )
    }

    override fun showLoading(loading: Boolean) {
        if (loading) {
            recyclerChats.visibility = View.INVISIBLE
            chatListProgress.visibility = View.VISIBLE

            presenter.onLoadCachedChats(
                getAccessToken(),
                getNetworkConnection(activity)
            )
        } else {
            recyclerChats.visibility = View.VISIBLE
            chatListProgress.visibility = View.INVISIBLE
            chatListSwipe.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        ServiceLocator.setSocketListener(presenter)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
        chatListSwipe.isRefreshing = false
    }

    override fun showChats(chats: MutableList<Chat>) {
        adapter.setData(chats)
    }

    override fun showChat(chat: Chat) {
        adapter.addChat(chat)
    }

    override fun showLoadFailed(e: Exception) {
        Log.d(TAG, "ERROR ${e.message}")
    }

    override fun getToken(): String {
        return getAccessToken() // TODO save token to
    }

    override fun showMessage(mes: Message) {
        adapter.updateMessage(mes)
    }

    private fun setSwipeListener() {
        chatListSwipe.setOnRefreshListener {
            presenter.onLoadChats(
                getAccessToken(),
                getNetworkConnection(activity)
            )
        }
    }
}