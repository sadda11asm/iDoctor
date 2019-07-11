package com.example.mppapp.ui.chatlist

import android.content.Context
import android.net.ConnectivityManager
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
import com.orhanobut.hawk.Hawk
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import data.db.MyDatabase
import data.entity.Chat
import data.entity.ChatResponse
import kotlinx.android.synthetic.main.fragment_chat_list.*
import org.kotlin.mpp.mobile.ServiceLocator
import presentation.chatlist.ChatListView

class ChatListFragment : Fragment(), ChatListView, ItemClickListener<Chat> {


    private val TAG = "ChatListFragment"

    private val presenter by lazy { ServiceLocator.chatListPresenter }

    private lateinit var adapter: ChatAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        activity?.title = "Чаты"
    }

    override fun onClick(data: Chat) {
        Log.d(TAG, "onClick(chat)")
//        this.context?.let { ChatActivity.open(it, data.id) }
    }

    override fun showLoading() {
        Log.d(TAG, "showLoading()")

        presenter.onLoadChats(Hawk.get<String>("access_token"), verifyAvailableNetwork()) // TODO replace with utils call
    }


    private fun verifyAvailableNetwork():Boolean{
        val connectivityManager= activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    override fun showChats(chats: List<Chat>) {
        Log.d(TAG, chats.toString())
        adapter = ChatAdapter(chats, context!!, this)
        recyclerChats.layoutManager = LinearLayoutManager(context)
        recyclerChats.adapter = adapter
    }

    override fun showLoadFailed(e: Exception) {
        Log.d(TAG, "ERROR ${e.message}")
    }

}